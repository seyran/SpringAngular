package com.seyrancom.consulting.test.integration;

import com.seyrancom.consulting.test.core.AbstractSpringRunner;
import com.seyrancom.consulting.app.domain.entity.Employee;
import com.seyrancom.consulting.app.repository.custom.BookRepository;
import com.seyrancom.consulting.app.repository.dao.impl.EmployeeDAOImpl;
import com.seyrancom.consulting.app.repository.jpa.EmployeeRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.stat.Statistics;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Cache;
import java.math.BigDecimal;
import java.util.List;


//@ActiveProfiles({"prod"})
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CacheTest1 extends AbstractSpringRunner {

    @Autowired
    @Qualifier("cachedBookRepository")
    private BookRepository bookRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeDAOImpl employeeDAO;

    @Test
    public void testEmployeeRepository()  {
        List<Employee> employeeList1 = employeeRepository.findAllEmployees();
        List<Employee> employeeList2 = employeeRepository.findAll();
    }

    @Test
    public void testSimpleCache() throws Exception {
        logger.info(".... Fetching books");
        logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
        logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
        logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
    }

    /*@Test
    @Rollback(false)
    public void testCreate1()  {
        entityManager_createEmp();
    }

    @Test
    @Rollback(false)
    public void testCreate2()  {
        //Session session = sessionFactory.getCurrentSession();
        //Session session = (Session) entityManager.getDelegate();
        Session session = entityManager.unwrap(org.hibernate.Session.class);
        sessionFactory_createEmp(session);
    }*/

    //@Rollback(false)
    private Long entityManager_createEmp() {
        Employee emp = new Employee();
        emp.setName("John-entityManager");
        emp.setSalary(new BigDecimal(100));
        entityManager.persist(emp);
        //entityManager.flush();
        //entityManager.clear();
        //empId = emp.getId();
        return emp.getId();
    }

    private Long sessionFactory_createEmp(Session session) {
        Employee emp = new Employee();
        emp.setName("John-session");
        emp.setSalary(new BigDecimal(100));
        session.save(emp);
        //session.flush();
        //session.clear();
        return emp.getId();
    }

    @Test
    //@Rollback(false)
    public void testEmployeeDAOImpl() {
        List<Employee> employeeList = employeeDAO.findAll();
        logger.debug("employeeList={}", employeeList);
    }

    @Test
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void testSecondLevelCache() {
        //Long empId = entityManager_createEmp();
        //Long empId = sessionFactory_createEmp(session);
        //Long empId = entityManager_createEmp();

        Statistics stats = getSessionFactory().getStatistics();
        System.out.println("Stats enabled=" + stats.isStatisticsEnabled());
        stats.setStatisticsEnabled(true);
        System.out.println("Stats enabled=" + stats.isStatisticsEnabled());

        List<Employee> employeeList = employeeDAO.findAll();
        if(!employeeList.isEmpty()){
            Long empId = employeeList.get(0).getId();
            empId = entityManager.find(Employee.class, empId).getId();
            logger.debug("testJPA, empId = {}:",empId);
            testJPA(empId);
            logger.debug("testSession, empId = {}:",empId);
            testSession(empId);
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    private void testJPA(Long empId) {
        Statistics stats = getSessionFactory().getStatistics();
        Employee emp;

        //entityManager.refresh(emp);
        printStats(stats, 0);
        emp = entityManager.find(Employee.class, empId);
        logger.debug("emp={}", emp);
        printStats(stats, 1);
        emp = entityManager.find(Employee.class, empId);
        logger.debug("emp={}", emp);

        //clear first level cache, so that second level cache is used

        Cache cache = entityManager.getEntityManagerFactory().getCache();
        System.out.println("cache.contain should return true: " + cache.contains(Employee.class, emp.getId()));
        //entityManager.flush();entityManager.clear();
        entityManager.clear();
        //cache.evict(Employee.class, emp.getId());
        //cache.evictAll();
        System.out.println("cache.contain should return false: " + cache.contains(Employee.class, emp.getId()));
        printStats(stats, 3);

        emp = entityManager.find(Employee.class, empId);
        if (emp != null) {
            logger.debug("emp={}", emp);
        }

        emp = entityManager.find(Employee.class, 3L);
        if (emp != null) {
            logger.debug("emp={}", emp);
        }

        emp = entityManager.find(Employee.class, empId);
        if (emp != null) {
            logger.debug("emp={}", emp);
        }
        printStats(stats, 4);
        //cache.evict(Employee.class);

        Assert.assertTrue("Should have hits", stats.getSecondLevelCacheHitCount() > 0);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    private void testSession(Long empId) {

        //Initialize Sessions
        //SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Statistics stats = getSessionFactory().getStatistics();

        Session session = getSessionFactory().openSession();
        Session otherSession = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Transaction otherTransaction = otherSession.beginTransaction();

        printStats(stats, 0);
        Employee emp;
        //Long empId = (Long) session.save(emp);

        emp = session.load(Employee.class, empId);
        printData(emp, stats, 1);

        emp = session.load(Employee.class, empId);
        printData(emp, stats, 2);

        //clear first level cache, so that second level cache is used
        session.evict(emp);
        emp = session.load(Employee.class, empId);
        if (emp != null) {
            printData(emp, stats, 3);
        }

        Employee emp2 = session.get(Employee.class, 3L);
        if (emp2 != null) {
            printData(emp2, stats, 4);
        }

        emp = otherSession.load(Employee.class, empId);
        if (emp != null) {
            printData(emp, stats, 5);
        }

        //Release resources
        transaction.rollback();
        otherTransaction.rollback();

        Assert.assertTrue("Should have hits", stats.getSecondLevelCacheHitCount() > 0);
    }

    private static void printStats(Statistics stats, int i) {
        System.out.println("***** " + i + " *****");
        System.out.println("Fetch Count="
                + stats.getEntityFetchCount());
        System.out.println("Second Level Hit Count="
                + stats.getSecondLevelCacheHitCount());
        System.out
                .println("Second Level Miss Count="
                        + stats
                        .getSecondLevelCacheMissCount());
        System.out.println("Second Level Put Count="
                + stats.getSecondLevelCachePutCount());
    }

    private static void printData(Employee emp, Statistics stats, int count) {
        System.out.println(count + ":: Name=" + emp.getName());
        //System.out.println(count+":: Name="+emp.getName()+", Zipcode="+emp.getAddress().getZipcode());
        printStats(stats, count);
    }
}
