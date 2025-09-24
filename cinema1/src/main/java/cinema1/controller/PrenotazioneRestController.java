package cinema1.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import cinema1.model.Credentials;
import cinema1.model.Prenotazione;
import cinema1.model.StatoPosto;
import cinema1.model.User;
import cinema1.service.PrenotazioneService;
import cinema1.service.SpettacoloService;
import cinema1.service.StatoPostoService;
import cinema1.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/prenotazioni")
public class PrenotazioneRestController {

    private final StatoPostoService statoPostoService;
    private final SpettacoloService spettacoloService;
    private final UserService userService;
    private final PrenotazioneService prenotazioneService;

    public PrenotazioneRestController(StatoPostoService statoPostoService, SpettacoloService spettacoloService, UserService userService,PrenotazioneService prenotazioneService) {
        this.statoPostoService = statoPostoService;
        this.spettacoloService = spettacoloService;
        this.userService = userService;
		this.prenotazioneService = prenotazioneService;
    }


    @PostMapping("/nuova")
    public ResponseEntity<Map<String, String>> prenota(@RequestBody Map<String, Object> payload, @AuthenticationPrincipal UserDetails userDetails) {
        
        try {
            if (payload == null || !payload.containsKey("spettacoloId") || !payload.containsKey("postoIds")) {
                 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Dati mancanti nel payload della richiesta."));
            }
            
            Long spettacoloId = Long.valueOf(payload.get("spettacoloId").toString());
            List<Integer> postoIds = (List<Integer>) payload.get("postoIds");

            if (postoIds == null || postoIds.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Nessun posto selezionato."));
            }

            // Ottiene l'utente autenticato direttamente dalle credenziali
            Credentials credentials = (Credentials) userDetails;
            User utente = credentials.getUser();

            if (utente == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Utente non autenticato o non valido."));
            }

            for (Integer postoIdInt : postoIds) {
                Long postoId = Long.valueOf(postoIdInt);
                
                Optional<StatoPosto> statoPostoOpt = statoPostoService.findBySpettacoloAndPostoId(spettacoloId, postoId);
                
                if (statoPostoOpt.isPresent()) {
                    StatoPosto statoPosto = statoPostoOpt.get();
                    if (statoPosto.getOccupato()) {
                        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message", "Il posto è già occupato."));
                    }
                    statoPosto.setOccupato(true);
                    statoPostoService.save(statoPosto);
                    
                    // Crea e salva la nuova entità Prenotazione
                    Prenotazione nuovaPrenotazione = new Prenotazione();
                    nuovaPrenotazione.setCredentials(credentials);
                    nuovaPrenotazione.setSpettacolo(statoPosto.getSpettacolo());
                    nuovaPrenotazione.setPosto(statoPosto.getPosto());
                    prenotazioneService.save(nuovaPrenotazione);

                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "Posto o spettacolo non trovato."));
                }
            }

            return ResponseEntity.ok().body(Map.of("message", "Prenotazione effettuata con successo!"));

        } catch (Exception e) {
            e.printStackTrace(); // Aggiunto per il debug
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "Errore durante la prenotazione: " + e.getMessage()));
        }
    }
    
    @GetMapping("/mie-prenotazioni")
    public ResponseEntity<List<Prenotazione>> getMiePrenotazioni(@AuthenticationPrincipal UserDetails userDetails) {
        // Ottieni le credenziali dell'utente loggato
        Credentials credentials = (Credentials) userDetails;

        // Recupera le prenotazioni dal servizio, che le cercherà per l'utente loggato
        List<Prenotazione> miePrenotazioni = prenotazioneService.findByCredentials(credentials);
        
        // Restituisce la lista di prenotazioni
        return ResponseEntity.ok(miePrenotazioni);
    }
}