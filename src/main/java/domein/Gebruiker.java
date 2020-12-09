package domein;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Gebruiker {

    @Id
    private Long gebruiker_id;

    private String naam;

    private String email;

    private String wachtwoord;

}
