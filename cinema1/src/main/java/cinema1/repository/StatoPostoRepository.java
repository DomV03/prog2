package cinema1.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import cinema1.model.StatoPosto;
import jakarta.transaction.Transactional;
import cinema1.model.Posto;
import cinema1.model.Spettacolo;

public interface StatoPostoRepository extends CrudRepository<StatoPosto, Long> {

    Optional<StatoPosto> findByPostoAndSpettacolo(Posto posto, Spettacolo spettacolo);
    
    List<StatoPosto> findBySpettacolo(Spettacolo spettacolo);
    
    Optional<StatoPosto> findBySpettacoloIdAndPostoId(Long spettacoloId, Long postoId);

    @Transactional
    void deleteBySpettacoloId(Long spettacoloId);
    
}
