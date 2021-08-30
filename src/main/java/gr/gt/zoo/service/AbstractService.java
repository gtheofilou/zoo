package gr.gt.zoo.service;

import gr.gt.zoo.dao.AbstractDao;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

/**
 * A basic service layer based on the {@link AbstractDao}.
 * It also makes all methods transactional.
 *
 * @param <T>  Entity Class
 * @param <PK> Primary Key
 */
@Service
@Transactional
public abstract class AbstractService<T, PK extends Serializable> {

    private AbstractDao<T, PK> abstractDao;

    public AbstractService(AbstractDao<T, PK> abstractDao) {
        this.abstractDao = abstractDao;
    }

    public T find(PK id) {
        return (T) abstractDao.find(id);
    }

    public List<T> findAll() {
        return abstractDao.findAll();
    }

    public void persist(T entity) {
        abstractDao.persist(entity);
    }

    public void merge(T entity) {
        abstractDao.merge(entity);
    }

    public void delete(T entity) {
        abstractDao.delete(entity);
    }

}
