package com.seyrancom.consulting.test.unit;

import com.seyrancom.consulting.test.core.AbstractTestRunner;
import org.junit.Test;

/**
 * Created by admin on 3/31/2016.
 */
public class DbTest1 extends AbstractTestRunner {

  /*  @Before
    public void setUp() throws Exception {
    }*/

    @Test
    public void testSimple(){
        /*

List<Object[]> results = this.entityManager.createNativeQuery("SELECT a.id, a.firstName, a.lastName, a.version FROM Author a").getResultList();

results.stream().forEach((record) -> {
        Long id = ((BigInteger) record[0]).longValue();
        String firstName = (String) record[1];
        String lastName = (String) record[2];
        Integer version = (Integer) record[3];
});
        */
    }

    @Test
    public void testNamedQuery(){
        /*

     //Hibernate Named Query
    Query query = session.getNamedQuery("findEmployeeByName");
    query.setString("name", "amit");
        */
    }

    @Test
    public void testParamQuery(){
        /*

String hql = "from Stock s where s.stockCode = ? and s.stockName = ?";
List result = session.createQuery(hql)
.setString(0, "7277")
.setParameter(1, "DIALOG")
.list();

    String hql = "from Stock s where s.stockCode = :stockCode";
List result = session.createQuery(hql)
.setParameter("stockCode", "7277")
.list();


String hql = "from Stock s where s.stockCode = :stockCode";
List result = session.createQuery(hql)
.setString("stockCode", "7277")
.list();


Stock stock = new Stock();
stock.setStockCode("7277");
String hql = "from Stock s where s.stockCode = :stockCode";
List result = session.createQuery(hql)
.setProperties(stock)
.list();

        */
    }

    @Test
    public void testQuery(){
        /*List<Object[]> results = ((Session)this.entityManager.getDelegate()).createSQLQuery("SELECT {b.*}, {a.*} FROM Book b JOIN Author a ON b.author_id = a.id").addEntity("b", Book.class).addEntity("a", Author.class).list();
        results.stream().forEach((record) -> {
            Book book = (Book) record[0];
            Author author = (Author) record[1];
            System.out.println("Author: ID [" + author.getId() + "] firstName [" + author.getFirstName() + "] lastName [" + author.getLastName() + "]");
            System.out.println("Book: ID [" + book.getId() + "] title[" + book.getTitle() + "]");
        });*/
    }

    // http://www.thoughts-on-java.org/result-set-mapping-hibernate-specific-mappings/
    @Test
    public void testScalar(){
        /*List<Object[]> results = ((Session)this.entityManager.getDelegate()).createSQLQuery("SELECT {a.*}, count(b.id) as bookCount FROM Book b JOIN Author a ON b.author_id = a.id GROUP BY a.id, a.firstName, a.lastName, a.version").addEntity(Author.class).addScalar("bookCount", StandardBasicTypes.LONG).list();
results.stream().forEach((record) -> {
    Author author = (Author) record[0];
    Long bookCount = (Long) record[1];
    System.out.println("Author: ID [" + author.getId() + "] firstName [" + author.getFirstName() + "] lastName [" + author.getLastName() + "] number of books [" + bookCount + "]");
});*/
    }
    @Test
    public void testResultTransformer(){
        /*
        List<BookValue> results = ((Session)this.entityManager.getDelegate()).createSQLQuery("SELECT b.id, b.title, b.version, a.firstName || ' ' || a.lastName as authorName FROM Book b JOIN Author a ON b.author_id = a.id")
    .addScalar("id", StandardBasicTypes.LONG).addScalar("title").addScalar("version", StandardBasicTypes.LONG).addScalar("authorName")
    .setResultTransformer(new AliasToBeanResultTransformer(BookValue.class)).list();

results.stream().forEach((book) -> {
    System.out.println("Book: ID [" + book.getId() + "] title [" + book.getTitle() + "] authorName [" + book.getAuthorName() + "]");
});
         */
    }
    @Test
    public void testSimplePaging() {

/*

     Session session = sessionFactory.openSession();
Query query = sess.createQuery("From Foo");
query.setFirstResult(0);
query.setMaxResults(10);
List<Foo> fooList = fooList = query.list();


*/

    }
    @Test
    public void testCriteriaPaging() {

/*

Criteria criteria = session.createCriteria(Foo.class);
criteria.setFirstResult(0);
criteria.setMaxResults(pageSize);
List<Foo> firstPage = criteria.list();


//Hibernate Criteria query API makes it very simple to also get the total count – by using a Projection object:

Criteria criteriaCount = session.createCriteria(Foo.class);
criteriaCount.setProjection(Projections.rowCount());
Long count = (Long) criteriaCount.uniqueResult();



*/

    }
    @Test
    public void givenEntitiesExist_whenRetrievingLastPage_thenCorrectSize() {

/*

        int pageSize = 10;
        String countQ = "Select count (f.id) from Foo f";
        Query countQuery = session.createQuery(countQ);
        Long countResults = (Long) countQuery.uniqueResult();
        int lastPageNumber = (int) ((countResults / pageSize) + 1);

        Query selectQuery = session.createQuery("From Foo");
        selectQuery.setFirstResult((lastPageNumber - 1) * pageSize);
        selectQuery.setMaxResults(pageSize);
        List<Foo> lastPage = selectQuery.list();

        assertThat(lastPage, hasSize(lessThan(pageSize + 1)));


*/

    }
    @Test
    public void testScrollableResults() {

/*

     String hql = "FROM Foo f order by f.name";
Query query = session.createQuery(hql);
int pageSize = 10;

ScrollableResults resultScroll = query.scroll(ScrollMode.FORWARD_ONLY);
resultScroll.first();
resultScroll.scroll(0);
List<Foo> fooPage = Lists.newArrayList();
int i = 0;
while (pageSize > i++) {
    fooPage.add((Foo) resultScroll.get(0));
    if (!resultScroll.next())
        break;
}


*/

    }
}
