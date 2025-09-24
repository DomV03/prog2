package cinema1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Spettacolo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relazione con il Film proiettato
    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;

    // Relazione con la Sala in cui viene proiettato
    @ManyToOne
    @JoinColumn(name = "sala_id")
    private Sala sala;
    
    @JsonIgnore 
    @OneToMany(mappedBy = "spettacolo")
    private List<StatoPosto> statoPosti;

    // Data e ora dello spettacolo
    private LocalDateTime orario;

    // Costruttore vuoto (richiesto da JPA)
    public Spettacolo() {}

    // Costruttore con parametri
    public Spettacolo(Film film, Sala sala, LocalDateTime orario) {
        this.film = film;
        this.sala = sala;
        this.orario = orario;
    }

    // Getter e Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public LocalDateTime getOrario() {
        return orario;
    }

    public void setOrario(LocalDateTime orario) {
        this.orario = orario;
    }

    // Metodi equals() e hashCode() basati sull'ID
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spettacolo that = (Spettacolo) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}