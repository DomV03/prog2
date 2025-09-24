package cinema1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cinema1.model.Credentials;
import cinema1.model.Prenotazione;
import cinema1.model.Spettacolo;
import cinema1.model.Posto;
import cinema1.repository.PrenotazioneRepository;

@Service
public class PrenotazioneService {

	@Autowired
	private PrenotazioneRepository prenotazioneRepository;
	
	public Optional<Prenotazione> findById(Long id) {
		return prenotazioneRepository.findById(id);
	}

	public Iterable<Prenotazione> findAll() {
		return prenotazioneRepository.findAll();
	}
	
	public List<Prenotazione> findBySpettacolo(Spettacolo spettacolo) {
		return prenotazioneRepository.findBySpettacolo(spettacolo);
	}

	public void save(Prenotazione prenotazione) {
		prenotazioneRepository.save(prenotazione);
	}
	
	public void deleteById(Long id) {
		prenotazioneRepository.deleteById(id);
	}

	/**
	 * Questo metodo gestisce la logica di prenotazione di un posto.
	 * 1. Verifica se il posto è disponibile per lo spettacolo.
	 * 2. In caso affermativo, crea la prenotazione e aggiorna lo stato del posto.
	 * @return La prenotazione salvata se l'operazione ha successo, altrimenti Optional.empty().
	 */
	public Optional<Prenotazione> effettuaPrenotazione(Credentials user, Spettacolo spettacolo, Posto posto) {
		// Controlla se il posto è già prenotato per questo spettacolo
		boolean isPrenotato = prenotazioneRepository.findByPostoAndSpettacolo(posto, spettacolo).size() > 0;

		if (isPrenotato) {
			return Optional.empty(); // Posto non disponibile
		}

		// Crea una nuova prenotazione
		Prenotazione nuovaPrenotazione = new Prenotazione(user, spettacolo, posto);
		prenotazioneRepository.save(nuovaPrenotazione);

		// Non è necessario aggiornare una tabella separata 'StatoPosto'
		// se si usa la Prenotazione stessa per verificare la disponibilità.
		
		return Optional.of(nuovaPrenotazione);
	}

	public List<Prenotazione> findByCredentials(Credentials credentials) {
		return prenotazioneRepository.findByCredentials(credentials);
	}

    public void deleteBySpettacoloId(Long spettacoloId) {
        prenotazioneRepository.deleteBySpettacoloId(spettacoloId);
    }


}
