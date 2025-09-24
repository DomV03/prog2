package cinema1.repository;

import java.util.List;
import cinema1.model.Prenotazione;
import cinema1.model.Spettacolo;
import jakarta.transaction.Transactional;
import cinema1.model.Credentials;
import cinema1.model.Posto;
import org.springframework.data.repository.CrudRepository;

public interface PrenotazioneRepository extends CrudRepository<Prenotazione,Long> {

	 List<Prenotazione> findByPostoAndSpettacolo(Posto posto, Spettacolo spettacolo);
	    
	   
	    List<Prenotazione> findBySpettacolo(Spettacolo spettacolo);


		List<Prenotazione> findByCredentials(Credentials credentials);


		@Transactional
        void deleteBySpettacoloId(Long spettacoloId);
	
}
