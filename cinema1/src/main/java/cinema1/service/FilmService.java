package cinema1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cinema1.model.Film;
import cinema1.repository.FilmRepository;

@Service
public class FilmService {
	
	@Autowired
    private SpettacoloService spettacoloService;
	
	@Autowired
	private FilmRepository filmRepository;

	public Film findById(Long id) {
           Optional<Film> result = filmRepository.findById(id);
        
        return result.orElse(null);
	}

	public List<Film> findAll() {
		return (List<Film>) filmRepository.findAll();
	}

	public Film save(Film film) {
        return filmRepository.save(film);
    }
	
	public Iterable<Film> findByGenere(String genere) {
		return filmRepository.findByGenere(genere);
	}
	
	public boolean existsByNomeAndRegista(String titolo, String regista) {
		return filmRepository.existsByTitoloAndRegista(titolo, regista);
	}
	
	public void deleteById(Long id) {
        spettacoloService.deleteByFilmId(id);
        filmRepository.deleteById(id);
    }
}
