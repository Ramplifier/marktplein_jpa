package com.marktplein.domein;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name = Gebruiker.Find_By_Email_Wachtwoord , query = "SELECT g FROM Gebruiker g WHERE g.email = :email AND g.wachtwoord = :wachtwoord")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Gebruiker {

    public static final String Find_By_Email_Wachtwoord = "Gebruiker.findByEmailAndPassword";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gebruiker_id;

    @Column(nullable = false)
    private String naam;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String wachtwoord;

    @Embedded
    private Adres adres;

    @Embedded
    private Bezorgopties bezorgopties;

    private boolean isAkkoord;

    @Column(columnDefinition="tinyint(1) default 1")
    private boolean isActief;


}
