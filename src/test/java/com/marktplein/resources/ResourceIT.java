package com.marktplein.resources;

import com.marktplein.App;
import com.marktplein.dao.Gebruiker_dao;
import com.marktplein.dao.Product_Dao;
import com.marktplein.domein.Gebruiker;
import com.marktplein.domein.Product;
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
public class ResourceIT {

    @ArquillianResource
    private URL deploymentURL;

    private String gebruikersResource;
    private String productResource;
    private String gebruikersUri = "api/gebruikers";
    private String productUri = "api/producten";


    @Inject
    Gebruiker_dao dao;

    @Inject
    Product_Dao p_dao;

    @Before
    public void setup() {
        gebruikersResource = deploymentURL + gebruikersUri;
        productResource = deploymentURL + productUri;

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
    public void saveGebruikerTest() {
        Gebruiker g = Gebruiker.builder()
                .naam("Stijn")
                .email("sjwarkel@gmail.com")
                .wachtwoord("WachtWoord8")
                .isAkkoord(true)
                .build();
        Gebruiker g2 = Gebruiker.builder()
                .naam("Piet")
                .email("Pepernoot@gmail.com")
                .wachtwoord("SinterKlaas")
                .isAkkoord(true)
                .build();

        dao.save(g);
        dao.save(g2);
        Client http = ClientBuilder.newClient();
        String message = http
                .target(gebruikersResource + "/2")
                .request().get(String.class);

        assertThat(message, containsString("Piet"));
        assertThat(message, containsString("Pepernoot@gmail.com"));
    }

    @Test
    public void getProductenTest() {
        Product p = Product.builder()
                .naam("Bal")
                .build();
        Product p2 = Product.builder()
                .naam("Pizzaoven")
                .build();
        p_dao.save(p);
        p_dao.save(p2);
        Client http = ClientBuilder.newClient();
        String message = http
                .target(productResource)
                .request().get(String.class);

        assertThat(message, containsString("Pizzaoven"));
    }



    @Test
    public void getAllProductenTest() {
        Product p = Product.builder()
                .naam("Bal")
                .build();
        Product p2 = Product.builder()
                .naam("Pizzaoven")
                .build();
        p_dao.save(p);
        p_dao.save(p2);
        Client http = ClientBuilder.newClient();
        String message = http
                .target(productResource)
                .request().get(String.class);

        assertThat(message, containsString("Bal"));
        assertThat(message, containsString("Pizzaoven"));
    }


}
