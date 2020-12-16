package com.marktplein.domein;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.math.BigDecimal;

@Data
@MappedSuperclass
@SuperBuilder
public abstract class Artikel {

    private String naam;

    private String Beschrijving;

    private String categorie;

    private BigDecimal prijs;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "gebruiker_id")
    private Gebruiker gebruiker;

    public Artikel() {

    }

}
