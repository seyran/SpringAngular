package com.seyrancom.consulting.core.repository.dao.common;

import com.seyrancom.consulting.config.root.PersistenceConfigBase;
import com.seyrancom.consulting.core.domain.entity.AbstractEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractHibernateDAO<T extends AbstractEntity> extends AbstractDAO<T> {

    /*@Autowired
    private SessionFactory sessionFactory;

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }*/

    /*    @Autowired
    protected SessionFactory sessionFactory;*/

    protected Session getSession() {
        Session session = entityManager.unwrap(Session.class);
        return session;
    };

    protected SessionFactory getSessionFactory() {
        return getSession().getSessionFactory();
    }

    @PersistenceContext(name = PersistenceConfigBase.PERSISTENCE_UNIT)
    //@Autowired
    private EntityManager entityManager;

    /**
     * Evicts all second level cache hibernate entites. This is generally only
     * needed when an external application modifies the databaase.
     */
/*    public void evict2ndLevelCache() {
        try {
            Map<String, ClassMetadata> classesMetadata = sessionFactory.getAllClassMetadata();
            for (String entityName : classesMetadata.keySet()) {
                logger.info("Evicting Entity from 2nd level cache: " + entityName);
                sessionFactory.evictEntity(entityName);
            }
        } catch (Exception e) {
            logger.logp(Level.SEVERE, "SessionController", "evict2ndLevelCache", "Error evicting 2nd level hibernate cache entities: ", e);
        }

        SessionFactory sf = currentSession().getSessionFactory();
cache.evictEntity(Cat.class, catId); //evict a particular Cat
cache.evictEntityRegion(Cat.class);  //evict all Cats
cache.evictCollection("Cat.kittens", catId); //evict a particular collection of kittens
cache.evictCollectionRegion("Cat.kittens"); //evict all kitten collections
    }

    public void evictAll() {
        Cache cache = sf.getCache();
        cache.evictQueryRegions();
        cache.evictDefaultQueryRegion();
        cache.evictCollectionRegions();
        cache.evictEntityRegions();
    }
    */
}
