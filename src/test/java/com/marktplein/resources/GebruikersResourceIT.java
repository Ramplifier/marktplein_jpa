package com.marktplein.resources;

import com.marktplein.App;
import com.marktplein.dao.Gebruiker_dao;
import com.marktplein.domein.Gebruiker;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.net.URL;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;


@RunWith(Arquillian.class)
public class GebruikersResourceIT {

    @ArquillianResource
    private URL deploymentURL;

    private String gebruikersResource;
    private String gebruikersUri = "api/gebruikers";


    @Inject
    Gebruiker_dao dao;

    @Before
    public void setup() {
        gebruikersResource = deploymentURL + gebruikersUri;

    }


    @Deployment
    public static Archive<?> createDeployment() {
        WebArchive archive = ShrinkWrap.create(WebArchive.class, "MarktPleinApp.war")
                .addPackages(true, App.class.getPackage())
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("test-beans.xml", "beans.xml");
        System.out.println(archive.toString(true));
        return archive;
    }

    @Test
    public void getGebruikerTest() {
        Gebruiker g = Gebruiker.builder()
                .naam("Stijn")
                .email("sjwarkel@gmail.com")
                .wachtwoord("WachtWoord8")
                .isAkkoord(true)
                .build();

        dao.save(g);
        Client http = ClientBuilder.newClient();
        String message = http
                .target(gebruikersResource + "/1")
                .request().get(String.class);

        assertThat(message, containsString("sjwarkel"));
    }

}
