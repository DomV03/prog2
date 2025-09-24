package cinema1.controller;

import cinema1.model.Film;
import cinema1.model.Spettacolo;
import cinema1.service.FilmService;
import cinema1.service.SpettacoloService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class FilmController {

	@Autowired
    private SpettacoloService spettacoloService;
	
	@Autowired
	private FilmService filmService;
	

    @GetMapping("/")
    public String mostraHome(Model model) {
        
        // 1. Recupera tutti gli spettacoli dal database
        Iterable<Spettacolo> tuttiGliSpettacoli = spettacoloService.findAll();
        
        // 2. Raggruppa gli spettacoli per l'ID del film (LA CHIAVE È L'ID)
        Map<Long, List<Spettacolo>> spettacoliPerFilm = StreamSupport.stream(tuttiGliSpettacoli.spliterator(), false)
            .collect(Collectors.groupingBy(spettacolo -> spettacolo.getFilm().getId()));
            
        // 3. Recupera tutti i film in programmazione
        List<Film> filmInProgrammazione = filmService.findAll(); // O un altro metodo per i film attivi

        // 4. Aggiunge i dati al modello per renderli disponibili all'HTML
        model.addAttribute("filmInProgrammazione", filmInProgrammazione);
        model.addAttribute("spettacoliPerFilm", spettacoliPerFilm);
        
        // 5. Restituisce il nome del template HTML da renderizzare
        return "home";
    }
    
    @GetMapping("/film/{id}")
    public String getFilm(@PathVariable("id") Long id, Model model) {
        
        // 1. Recupera il film dal database
        Film film = this.filmService.findById(id);

        if (film == null) {
            // Reindirizza a una pagina di errore se il film non viene trovato
            return "redirect:/home?error=film-non-trovato";
        }
        
        // 2. Recupera la lista degli spettacoli programmati per questo film
        List<Spettacolo> spettacoli = this.spettacoloService.findByFilm(film);

        // 3. Aggiunge gli oggetti al modello per la visualizzazione nel template
        model.addAttribute("film", film);
        model.addAttribute("spettacoli", spettacoli);
        
        // Puoi aggiungere anche qui i dettagli dell'utente se servono per il layout
        // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // ... (Logica di sicurezza)

        // 4. Restituisce il nome del template
        return "film";
    }
    
    @GetMapping("/film/{filmId}/{spettacoloId}")
    public String getFilmAndSelectedSpettacolo(@PathVariable("filmId") Long filmId, @PathVariable("spettacoloId") Long spettacoloId, Model model) {
        return getFilmAndSpettacoli(filmId, spettacoloId, model);
    }
    
    private String getFilmAndSpettacoli(Long filmId, Long spettacoloId, Model model) {
        Film film = this.filmService.findById(filmId);

        if (film == null) {
            return "redirect:/home?error=film-non-trovato";
        }
        
        List<Spettacolo> spettacoli = this.spettacoloService.findByFilm(film);

        model.addAttribute("film", film);
        model.addAttribute("spettacoli", spettacoli);
        
        // Se un ID di spettacolo è stato fornito, lo aggiungiamo al modello
        if (spettacoloId != null) {
            model.addAttribute("spettacoloId", spettacoloId);
        }

        return "film";
    }
    
    
    @GetMapping("/mie-prenotazioni")
    public String showMiePrenotazioniPage() {
        return "mie-prenotazioni";
    }
    
    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String showAdminPage() {
        return "admin"; // Questo restituirà la pagina admin.html
    }

	
}
