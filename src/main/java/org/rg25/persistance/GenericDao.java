package org.rg25.persistance;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

/**
 * Represents a generic dao
 * @param <T> type to be used
 */
public class GenericDao<T> {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private Class<T> type;

    /**
     * Constructs a dao
     * @param type class to be used for this dao
     */
    public GenericDao(Class<T> type) {
        this.type = type;
    }

    /**
     * Gets by id
     * @param id id to fetch
     * @return resulting row
     * @param <T> type
     */
    public <T>T getById(int id) {
        Session session = getSession();
        T entity = (T)session.get(type, id);
        session.close();
        return entity;
    }

    /**
     * Deletes a row
     * @param entity object to delete
     */
    public void delete(T entity) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
        session.close();
    }

    /**
     * Updates a row
     * @param entity object to update
     */
    public void update(T entity) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.merge(entity);
        transaction.commit();
    }

    /**
     * Inserts a row
     * @param entity object to insert
     * @return id of corresponding row
     */
    public int insert(T entity) {
        Session session = getSession();
        int id;
        Transaction transaction = session.beginTransaction();
        id = (int)session.save(entity);
        transaction.commit();
        session.close();
        return id;
    }

    /**
     * Fetches rows based on a matching property
     * @param property column name
     * @param value value to match
     * @return resulting rows
     */
    public List<T> getByProperty(String property, Object value) {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        query.select(root).where(builder.equal(root.get(property), value));
        session.close();
        return session.createSelectionQuery(query).getResultList();
    }

    /**
     * Fetches all matching rows, to a limit
     * @param limit row limit
     * @return
     */
    public List<T> getByPropertyUpTo(String property, Object value, int limit) {
//        Session session = getSession();
//        CriteriaBuilder builder = session.getCriteriaBuilder();
//        CriteriaQuery<T> query = builder.createQuery(type);
//        Root<T> root =  query.from(type);
//        List<T> list = session.createQuery(query).setMaxResults(limit).getResultList();
//        session.close();
//        return list;

        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);

        query.select(root).where(builder.equal(root.get(property), value));
        List<T> list = session.createQuery(query).setMaxResults(limit).getResultList();
        session.close();
        return list;
    }

    /**
     * Fetches rows based on a like property
     * @param property column name
     * @param value value to search for
     * @return found rows
     */
    public List<T> getByPropertyLike(String property, Object value) {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        Predicate predicate = builder.like(root.get(property), "%" + value + "%");

        query.where(predicate);
        return session.createQuery(query).getResultList();
    }

    /**
     * Fetches all rows
     * @return all rows
     */
    public List<T> getAll() {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root =  query.from(type);
        List<T> list = session.createQuery(query).getResultList();
        session.close();
        return list;
    }


    /**
     * Gets the session
     * @return session
     */
    private Session getSession() {
        return SessionFactoryProvider.getSessionFactory().openSession();
    }

}
