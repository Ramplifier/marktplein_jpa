package resources;

import domein.Gebruiker;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/gebruikers")
public class GebruikersResource {

    @POST
    public Gebruiker addGebruiker(Gebruiker g) {
        return g;

    }
}
