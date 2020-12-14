package com.marktplein.dao;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public abstract class Dao<T, I> {


    @PersistenceContext
    protected EntityManager em;

    public T get(I id) {
        return em.find(T(), id);
    }

    public void save(T e) throws NullPointerException {
        em.persist(e);
    }

    public List<T> getAll() {
        return em.createQuery("SELECT t from " + typeSimple() + " t ", T()).getResultList();
    }

    private String typeSimple() {
        return T().getSimpleName();
    }

    @SuppressWarnings("unchecked")
    private Class<T> T() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

}
