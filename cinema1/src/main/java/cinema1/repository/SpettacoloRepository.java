package cinema1.repository;

import cinema1.model.Film;
import cinema1.model.Sala;
import cinema1.model.Spettacolo;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface SpettacoloRepository extends CrudRepository<Spettacolo, Long> {

    boolean existsByFilmAndOrario(Film film, LocalDateTime orario);

    List<Spettacolo> findByFilm(Film film);

    List<Spettacolo> findBySala(Sala sala);

    List<Spettacolo> findByOrarioBetween(LocalDateTime inizio, LocalDateTime fine);

    Optional<Spettacolo> findByFilmAndSala(Film film, Sala sala);
    
    List<Spettacolo> findByFilmId(Long filmId);
}