package com.marktplein.resources;

import com.marktplein.dao.Gebruiker_dao;
import com.marktplein.domein.Gebruiker;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/gebruikers")
public class GebruikersResource implements JsonResource {

    @Inject
    Gebruiker_dao dao;

    @POST
    public Gebruiker addGebruiker(Gebruiker g) {
        dao.save(g);
        return g;

    }
}
