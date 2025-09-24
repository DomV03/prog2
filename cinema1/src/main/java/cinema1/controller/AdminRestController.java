package cinema1.controller;

import cinema1.model.Film;
import cinema1.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminRestController {

    @Autowired
    private FilmService filmService;

    // Endpoint per ottenere tutti i film
    @GetMapping("/films")
    public ResponseEntity<List<Film>> getAllFilms() {
        List<Film> films = (List<Film>) filmService.findAll();
        return ResponseEntity.ok(films);
    }

    // Endpoint per aggiungere un nuovo film
    @PostMapping("/films")
    public ResponseEntity<Film> addFilm(@RequestBody Film film) {
        Film newFilm = filmService.save(film);
        return ResponseEntity.ok(newFilm);
    }

    // Endpoint per modificare un film esistente
    @PutMapping("/films/{id}")
    public ResponseEntity<Film> updateFilm(@PathVariable Long id, @RequestBody Film film) {
        film.setId(id); // Assicurati che l'ID del film sia corretto
        Film updatedFilm = filmService.save(film);
        return ResponseEntity.ok(updatedFilm);
    }

    // Endpoint per eliminare un film
    @DeleteMapping("/films/{id}")
    public ResponseEntity<Void> deleteFilm(@PathVariable Long id) {
        filmService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
