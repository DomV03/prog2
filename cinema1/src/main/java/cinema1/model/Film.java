package cinema1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Objects;

@Entity
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titolo;
    
    private String immagineCopertinaUrl;
    
    private int etaMinima;

    private String regista;
    
    @Column(length = 255)
    private String attori; 
    
    private String genere;

    private int durataMinuti;
    
    @Column(length = 1000)
    private String trama;

    // Costruttore vuoto (richiesto da JPA)
    public Film() {}

    // Costruttore con parametri
    public Film(String nome, String immagineCopertinaUrl, int etaMinima, String regista, String attori, int durataMinuti, String trama) {
        this.titolo = nome;
        this.immagineCopertinaUrl = immagineCopertinaUrl;
        this.etaMinima = etaMinima;
        this.regista = regista;
        this.attori = attori;
        this.durataMinuti = durataMinuti;
        this.trama = trama;
    }

    // Getter e Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getImmagineCopertinaUrl() {
        return immagineCopertinaUrl;
    }

    public void setImmagineCopertinaUrl(String immagineCopertinaUrl) {
        this.immagineCopertinaUrl = immagineCopertinaUrl;
    }

    public int getEtaMinima() {
        return etaMinima;
    }

    public void setEtaMinima(int etaMinima) {
        this.etaMinima = etaMinima;
    }

    public String getRegista() {
        return regista;
    }

    public void setRegista(String regista) {
        this.regista = regista;
    }

    public String getAttori() {
        return attori;
    }

    public void setAttori(String attori) {
        this.attori = attori;
    }

    public int getDurataMinuti() {
        return durataMinuti;
    }

    public void setDurataMinuti(int durataMinuti) {
        this.durataMinuti = durataMinuti;
    }

    public String getTrama() {
        return trama;
    }

    public void setTrama(String trama) {
        this.trama = trama;
    }

    // equals() e hashCode() basati sull'ID
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return Objects.equals(id, film.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}
}
