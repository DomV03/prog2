package cinema1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import java.util.Objects;

@Entity
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codice;
    
    private int capienza;
    
    private boolean proiettore4K;
    
    private boolean audioDolby;
    
    @OneToMany(mappedBy = "sala")
    @JsonIgnore
    private List<Posto> posti;

    // Costruttore vuoto (richiesto da JPA)
    public Sala() {}

    // Costruttore con parametri
    public Sala(String codice, int capienza, boolean proiettore4K, boolean audioDolby) {
        this.codice = codice;
        this.capienza = capienza;
        this.proiettore4K = proiettore4K;
        this.audioDolby = audioDolby;
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

    public int getCapienza() {
        return capienza;
    }

    public void setCapienza(int capienza) {
        this.capienza = capienza;
    }

    public boolean isProiettore4K() {
        return proiettore4K;
    }

    public void setProiettore4K(boolean proiettore4K) {
        this.proiettore4K = proiettore4K;
    }

    public boolean isAudioDolby() {
        return audioDolby;
    }

    public void setAudioDolby(boolean audioDolby) {
        this.audioDolby = audioDolby;
    }
    
    public List<Posto> getPosti() {
        return posti;
    }

    // Aggiunto il setter per la lista dei posti
    public void setPosti(List<Posto> posti) {
        this.posti = posti;
    }

    // Metodi equals() e hashCode() basati sull'ID
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sala sala = (Sala) o;
        return Objects.equals(id, sala.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
