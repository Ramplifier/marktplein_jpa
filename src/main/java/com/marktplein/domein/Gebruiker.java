package com.marktplein.domein;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Gebruiker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gebruiker_id;

    @Column(nullable = false)
    private String naam;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String wachtwoord;

    private Adres adres;

    private Bezorgopties bezorgopties;

    private boolean isAkkoord;

    private boolean isActief;


}
