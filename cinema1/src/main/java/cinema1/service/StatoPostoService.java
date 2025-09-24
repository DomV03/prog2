package cinema1.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cinema1.model.StatoPosto;
import cinema1.model.Spettacolo;
import cinema1.model.Posto;
import cinema1.repository.StatoPostoRepository;

@Service
public class StatoPostoService {

    @Autowired
    private StatoPostoRepository statoPostoRepository;
    

    public Optional<StatoPosto> findById(Long id) {
        return statoPostoRepository.findById(id);
    }

    public Iterable<StatoPosto> findAll() {
        return statoPostoRepository.findAll();
    }

    public void save(StatoPosto statoPosto) {
        statoPostoRepository.save(statoPosto);
    }
    
    public void deleteById(Long id) {
        statoPostoRepository.deleteById(id);
    }

    public boolean isPostoOccupato(Posto posto, Spettacolo spettacolo) {
        Optional<StatoPosto> stato = statoPostoRepository.findByPostoAndSpettacolo(posto, spettacolo);
        return stato.isPresent() && stato.get().getOccupato();
    }

    public List<Posto> findPostiDisponibili(Spettacolo spettacolo) {
        List<StatoPosto> statiPosti = statoPostoRepository.findBySpettacolo(spettacolo);
        return statiPosti.stream()
                         .filter(stato -> !stato.getOccupato())
                         .map(StatoPosto::getPosto)
                         .collect(Collectors.toList());
    }

    public void occupaPosto(Posto posto, Spettacolo spettacolo) {
        Optional<StatoPosto> stato = statoPostoRepository.findByPostoAndSpettacolo(posto, spettacolo);
        if (stato.isPresent()) {
            StatoPosto statoDaAggiornare = stato.get();
            statoDaAggiornare.setOccupato(true);
            statoPostoRepository.save(statoDaAggiornare);
        }
    }

    public void liberaPosto(Posto posto, Spettacolo spettacolo) {
        Optional<StatoPosto> stato = statoPostoRepository.findByPostoAndSpettacolo(posto, spettacolo);
        if (stato.isPresent()) {
            StatoPosto statoDaAggiornare = stato.get();
            statoDaAggiornare.setOccupato(false);
            statoPostoRepository.save(statoDaAggiornare);
        }
    }

	public List<StatoPosto> findBySpettacolo(Spettacolo spettacolo) {
		return statoPostoRepository.findBySpettacolo(spettacolo);
	}

	public Optional<StatoPosto> findBySpettacoloAndPostoId(Long spettacoloId, Long postoId) {
	    return statoPostoRepository.findBySpettacoloIdAndPostoId(spettacoloId, postoId);
	}

	public void deleteBySpettacoloId(Long id) {
		 statoPostoRepository.deleteBySpettacoloId(id);
	}
}