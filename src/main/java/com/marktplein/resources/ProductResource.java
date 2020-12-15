package com.marktplein.resources;

import com.marktplein.dao.Gebruiker_dao;
import com.marktplein.dao.Product_Dao;
import com.marktplein.domein.Product;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.time.LocalDate;
import java.util.List;

@Path("/producten")
public class ProductResource implements JsonResource {

    @Inject
    Product_Dao dao;

    @Inject
    Gebruiker_dao gebruiker_dao;

    @POST
    public Product saveProduct(Product p) {
        LocalDate lt = LocalDate.now();
        p.setGebruiker(gebruiker_dao.get(p.getGebruiker().getGebruiker_id()));
        System.out.println(p.getGebruiker().getNaam());
        p.setDatumPlaatsing(lt);
        dao.save(p);
        return p;
    }

    @GET
    public List<Product> getAllProducts() {
        return dao.getAll();
    }

}
