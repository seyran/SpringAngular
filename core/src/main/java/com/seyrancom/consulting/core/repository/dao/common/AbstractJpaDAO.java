package com.seyrancom.consulting.core.repository.dao.common;

import com.seyrancom.consulting.config.root.PersistenceConfigBase;
import com.seyrancom.consulting.core.domain.entity.AbstractEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractJpaDAO<T extends AbstractEntity> extends AbstractDAO<T> {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Value("${hibernate.jdbc.batch_size}")
    private String batchSize;

    @PersistenceContext(name = PersistenceConfigBase.PERSISTENCE_UNIT)
    //@Autowired
    private EntityManager entityManager;

    public void create(final T entity) {
        entityManager.persist(entity);
    }

    public void create(final Collection<T> entityList) {
        for (T entity : entityList) {
            create(entity);
        }
    }

    public T update(final T entity) {
        return entityManager.merge(entity);
    }

    public Collection<T> saveCollection(Collection<T> entities) {
        final List<T> savedEntities = new ArrayList<>(entities.size());
        int i = 0;
        for (T entity : entities) {
            savedEntities.add(save(entity));
            i++;
            if (i % Integer.valueOf(batchSize) == 0) {
                // Flush a batch of inserts and release memory.
                entityManager.flush();
                //entityManager.clear();
                // TODO: improve clear cache
                entityManager.getEntityManagerFactory().getCache().evict(entity.getClass());
                //entityManager.getEntityManagerFactory().getCache().evictAll();
            }
        }
        return savedEntities;
    }

    public T save(final T entity) {
        if (entity.getId() == 0L) {
            entityManager.persist(entity);
            return entity;
        } else {
            return entityManager.merge(entity);
        }
    }

    public void delete(final T entity) {
        entityManager.remove(entity);
    }

    public void deleteById(final Long id) {
        T entity = find(id);
        delete(entity);
    }

    public List<T> findAll() {
        return findAll(ENTITY_CLASS);
    }

    public List<T> findAll(Class<T> entityClass) {
        TypedQuery<T> lQuery = entityManager.createQuery("FROM " + entityClass.getSimpleName().toString(), entityClass);
        List<T> list = lQuery.getResultList();
        //return entityManager.createQuery(" FROM " + entityClass.getName()).getResultList();
        return list;
    }

    public List<T> findAll(String query) {
        TypedQuery<T> lQuery = entityManager.createQuery(query, ENTITY_CLASS);
        List<T> list = lQuery.getResultList();
        return list;
    }

    public T find(Serializable id) {
        return entityManager.find(ENTITY_CLASS, id);
    }

    /**
     * Evicts all second level cache hibernate entites. This is generally only
     * needed when an external application modifies the databaase.
     */
    public void evictAll() {
        entityManager.getEntityManagerFactory().getCache().evictAll();
    }

    public String getTableName(Class<T> entityClass) {
     /*
     * Check if the specified class is present in the metamodel.
     * Throws IllegalArgumentException if not.
     */
        Metamodel meta = entityManager.getMetamodel();
        EntityType<T> entityType = meta.entity(entityClass);

        //Check whether @Table annotation is present on the class.
        Table table = entityClass.getAnnotation(Table.class);

        String tableName = (table == null)
                ? entityType.getName().toLowerCase()
                : table.name();
        return tableName;
    }
}
