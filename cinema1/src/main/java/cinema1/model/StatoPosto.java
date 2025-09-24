package cinema1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="statoposto")
public class StatoPosto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "spettacolo_id", nullable = false)
    @JsonIgnore
    private Spettacolo spettacolo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "posto_id", nullable = false)
    @JsonIgnore
    private Posto posto;

    
    private boolean occupato; 

    public StatoPosto() {}

    public StatoPosto(Spettacolo spettacolo, Posto posto, boolean occupato) {
        this.spettacolo = spettacolo;
        this.posto = posto;
        this.occupato = occupato;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean getOccupato() {
        return occupato;
    }

    public void setOccupato(boolean occupato) {
        this.occupato = occupato;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatoPosto that = (StatoPosto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
