package cinema1.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cinema1.model.Film;

public interface FilmRepository extends CrudRepository<Film, Long> {

	public List <Film> findByGenere(String genere);

	public boolean existsByTitoloAndRegista(String Titolo, String regista);

}
