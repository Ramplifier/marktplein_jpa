package com.marktplein.dao;

import com.marktplein.domein.Gebruiker;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.persistence.TypedQuery;

@Stateless
@NoArgsConstructor

public class Gebruiker_dao extends Dao<Gebruiker,Long> {

    public Gebruiker getByEmail(String email) {

        TypedQuery<Gebruiker> query = em.createQuery("select g from Gebruiker g where g.email like :firstarg", Gebruiker.class);
        query.setParameter("firstarg", email);
        return query.getSingleResult();
    }

}
