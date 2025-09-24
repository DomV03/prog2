package cinema1.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cinema1.model.Sala;

public interface SalaRepository extends CrudRepository<Sala,Long>{
	
	public List<Sala> findByCodice(String codice);

	public List<Sala> findByProiettore4K(Boolean proiettore);
	
	public List<Sala> findByAudioDolby(Boolean audio);

}
