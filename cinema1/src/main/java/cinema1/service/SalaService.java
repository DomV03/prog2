package cinema1.service;

import cinema1.model.Sala;
import cinema1.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class SalaService {

	@Autowired
    private SalaRepository salaRepository;

    public Optional<Sala> findById(Long id) {
        return salaRepository.findById(id);
    }

    public Iterable<Sala> findAll() {
        return salaRepository.findAll();
    }

    public void save(Sala sala) {
        salaRepository.save(sala);
    }

    public void deleteById(Long id) {
        salaRepository.deleteById(id);
    }

    public List<Sala> findByCodice(String codice) {
        return salaRepository.findByCodice(codice);
    }

    public List<Sala> findByProiettore4K(boolean proiettore) {
        return salaRepository.findByProiettore4K(proiettore);
    }

    public List<Sala> findByAudioDolby(boolean audio) {
        return salaRepository.findByAudioDolby(audio);
    }
	
}
