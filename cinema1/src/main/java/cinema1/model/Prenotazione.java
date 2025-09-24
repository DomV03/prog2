package cinema1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.Objects;

@Entity
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relazione con l'Utente che prenota
    @ManyToOne
    @JoinColumn(name = "credentials_id")
    private Credentials credentials;

    // Relazione con lo Spettacolo
    // La prenotazione si riferisce a uno specifico spettacolo,
    // che a sua volta contiene già Film, Sala e orario.
    @ManyToOne
    @JoinColumn(name = "spettacolo_id")
    private Spettacolo spettacolo;
    
    // Relazione con il Posto specifico prenotato
    @ManyToOne
    @JoinColumn(name = "posto_id")
    private Posto posto;

    // Costruttore vuoto (richiesto da JPA)
    public Prenotazione() {}

    // Costruttore con parametri
    public Prenotazione(Credentials credentials, Spettacolo spettacolo, Posto posto) {
        this.credentials = credentials;
        this.spettacolo = spettacolo;
        this.posto = posto;
    }

    // Getter e Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public Spettacolo getSpettacolo() {
        return spettacolo;
    }

    public void setSpettacolo(Spettacolo spettacolo) {
        this.spettacolo = spettacolo;
    }
    
    public Posto getPosto() {
        return posto;
    }

    public void setPosto(Posto posto) {
        this.posto = posto;
    }

    // Metodi equals() e hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prenotazione that = (Prenotazione) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}