package com.marktplein.dao;

import com.marktplein.domein.Gebruiker;

import javax.ejb.Stateless;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;

@Stateless
public class Gebruiker_dao extends Dao<Gebruiker,Long> {

    public Gebruiker getByEmail(String email) {

        TypedQuery<Gebruiker> query = em.createQuery("select g from Gebruiker g where g.email like :firstarg", Gebruiker.class);
        query.setParameter("firstarg", email);
        return query.getSingleResult();
    }

    public Gebruiker authenticate(String email, String wachtwoord) {
        TypedQuery<Gebruiker> query = em.createNamedQuery(Gebruiker.Find_By_Email_Wachtwoord,Gebruiker.class);
        query.setParameter("email",email);
        query.setParameter("wachtwoord",wachtwoord);
        Gebruiker gebruiker = query.getSingleResult();

        if(gebruiker == null) throw new SecurityException("Ongeldig email/wachtwoord");

        return gebruiker;

    }
}
