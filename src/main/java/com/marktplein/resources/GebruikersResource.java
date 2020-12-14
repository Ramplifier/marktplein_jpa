package com.marktplein.resources;

import com.marktplein.dao.Gebruiker_dao;
import com.marktplein.domein.Gebruiker;
import com.marktplein.requests.RequestLogin;

import javax.inject.Inject;
import javax.ws.rs.*;

@Path("/gebruikers")
public class GebruikersResource implements JsonResource {

    @Inject
    Gebruiker_dao dao;

    @POST
    public Gebruiker addGebruiker(Gebruiker g) {
        dao.save(g);
        return g;
    }

    @POST
    @Path("/login")
    public Gebruiker login(RequestLogin g) {
        System.out.println("Authenticatie is aangeroepen");
        try {
            String email = g.getEmail();
            String wachtwoord = g.getWachtwoord();
            return dao.authenticate(email, wachtwoord);
        } catch (Exception e) {
            throw new NotAuthorizedException("Gebruiker " + g + "is niet geautoriseerd.");
        }
    }


    @GET
    @Path("/{id}")
    public Gebruiker getGebruiker(@PathParam("id") Long id) {
        return dao.get(id);
    }


}
