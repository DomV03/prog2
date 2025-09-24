package cinema1.service;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cinema1.model.Posto;
import cinema1.model.Sala;
import cinema1.repository.PostoRepository;

@Service
public class PostoService {

    @Autowired
    private PostoRepository postoRepository;

    public Optional<Posto> findById(Long id) {
        return postoRepository.findById(id);
    }

    public Iterable<Posto> findAll() {
        return postoRepository.findAll();
    }

    public void save(Posto posto) {
        postoRepository.save(posto);
    }

    public void deleteById(Long id) {
        postoRepository.deleteById(id);
    }

    public Optional<Posto> findByCodiceAndSala(String codice, Sala sala) {
        return postoRepository.findByCodiceAndSala(codice, sala);
    }

    public List<Posto> findBySala(Sala sala) {
        return postoRepository.findBySala(sala);
    }
}
