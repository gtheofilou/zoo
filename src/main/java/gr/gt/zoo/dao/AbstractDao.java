package gr.gt.zoo.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;


/**
 * Basic Data Access Object that provides convenient utility method
 *
 * @param <T>  Entity Class
 * @param <PK> Primary Key
 */
public abstract class AbstractDao<T, PK extends Serializable> {

    @PersistenceContext
    EntityManager entityManager;

    private Class<T> persistentClass;

    public AbstractDao() {
        this.persistentClass = getGenericTypeClass();
    }

    public T find(PK id) {
        return (T) entityManager.find(persistentClass, id);
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return entityManager.createQuery("from " + persistentClass.getName()).getResultList();
    }

    public void persist(T entity) {
        entityManager.persist(entity);
    }

    public void merge(T entity) {
        entityManager.merge(entity);
    }

    public void delete(T entity) {
        entityManager.remove(entity);
    }

    @SuppressWarnings("unchecked")
    private Class<T> getGenericTypeClass() {
        try {
            String className = ((ParameterizedType) getClass().getGenericSuperclass())
                    .getActualTypeArguments()[0].getTypeName();
            Class<?> clazz = Class.forName(className);
            return (Class<T>) clazz;
        } catch (Exception e) {
            throw new IllegalStateException("Class is not parametrized");
        }
    }

}
