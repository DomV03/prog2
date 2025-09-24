package cinema1.service;

import cinema1.model.Film;
import cinema1.model.Sala;
import cinema1.model.Spettacolo;
import cinema1.repository.SpettacoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SpettacoloService {

	@Autowired
	private StatoPostoService statoPostoService;
	
	@Autowired
    private PrenotazioneService prenotazioneService;
	
    @Autowired
    private SpettacoloRepository spettacoloRepository;

    public Optional<Spettacolo> findById(Long id) {
        return spettacoloRepository.findById(id);
    }

    public Iterable<Spettacolo> findAll() {
        return spettacoloRepository.findAll();
    }

    public void save(Spettacolo spettacolo) {
        spettacoloRepository.save(spettacolo);
    }

    public void deleteById(Long id) {
        spettacoloRepository.deleteById(id);
    }

    public boolean existsByFilmAndOrario(Film film, LocalDateTime orario) {
        return spettacoloRepository.existsByFilmAndOrario(film, orario);
    }

    public List<Spettacolo> findByFilm(Film film) {
        return spettacoloRepository.findByFilm(film);
    }

    public List<Spettacolo> findBySala(Sala sala) {
        return spettacoloRepository.findBySala(sala);
    }

    public List<Spettacolo> findByOrarioBetween(LocalDateTime inizio, LocalDateTime fine) {
        return spettacoloRepository.findByOrarioBetween(inizio, fine);
    }

    public void deleteByFilmId(Long filmId) {
        List<Spettacolo> spettacoliToDelete = spettacoloRepository.findByFilmId(filmId);

        for (Spettacolo spettacolo : spettacoliToDelete) {
        	prenotazioneService.deleteBySpettacoloId(spettacolo.getId());
            // Elimina prima tutti gli stati dei posti di questo spettacolo
            statoPostoService.deleteBySpettacoloId(spettacolo.getId());
            // Poi elimina lo spettacolo
            spettacoloRepository.delete(spettacolo);
        }
    }
}
