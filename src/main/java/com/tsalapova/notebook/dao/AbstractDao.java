package com.tsalapova.notebook.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

/**
 * @author TsalapovaMD
 * @version 1.0, 3/14/2018
 */
@Repository
public abstract class AbstractDao<PK extends Serializable, T> {
    private final Class<T> persistentClass;
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    public AbstractDao(){
        this.persistentClass =(Class<T>) ((ParameterizedType) this.getClass()
                .getGenericSuperclass()).getActualTypeArguments()[1];
    }

    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @SuppressWarnings("unchecked")
    public T getByKey(PK key) {
        return (T) getSession().get(persistentClass, key);
    }

    public T add(T entity){
        getSession().save(entity);
        return entity;
    }

    public void persist(T entity) {
        getSession().persist(entity);
    }

    public T update(T entity){
        getSession().update(entity);
        return entity;
    }

    public void delete(T entity) {
        getSession().delete(entity);
    }

    protected Criteria createEntityCriteria(){
        return getSession().createCriteria(persistentClass);
    }

    protected CriteriaQuery<T> createCriteriaQuery(){
        return getSession().getCriteriaBuilder().createQuery(persistentClass);
    }
}
