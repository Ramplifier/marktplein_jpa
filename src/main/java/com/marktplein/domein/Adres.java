package com.marktplein.domein;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class Adres {

    private String woonplaats;

    private String Straatnaam;

    private int huisnummer;

    private String toevoeging;

    private String postcode;

}
