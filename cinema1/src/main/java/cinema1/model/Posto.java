package cinema1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.Objects;

@Entity
public class Posto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codice; // Esempio: "A1", "B5", "C3"
    
    @ManyToOne
    @JoinColumn(name = "sala_id")
    private Sala sala;
    
    // Costruttore vuoto (richiesto da JPA)
    public Posto() {}

    // Costruttore con parametri
    public Posto(String codice, Sala sala) {
        this.codice = codice;
        this.sala = sala;
    }

    // Getter e Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    // equals() e hashCode()
    // Vengono usati per identificare in modo univoco l'oggetto.
    // L'implementazione qui è basata sull'ID, che è la chiave primaria.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Posto posto = (Posto) o;
        return Objects.equals(id, posto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
