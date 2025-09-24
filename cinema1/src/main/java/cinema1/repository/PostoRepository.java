package cinema1.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import cinema1.model.Posto;
import cinema1.model.Sala;

public interface PostoRepository extends CrudRepository<Posto,Long>{

	 Optional<Posto> findByCodiceAndSala(String codice, Sala sala);
	    
	  
	 List<Posto> findBySala(Sala sala);
	
}
