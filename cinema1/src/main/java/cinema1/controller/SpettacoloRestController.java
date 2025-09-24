package cinema1.controller;

import org.springframework.web.bind.annotation.RestController;

import cinema1.model.Spettacolo;
import cinema1.model.StatoPosto;
import cinema1.service.SpettacoloService;
import cinema1.service.StatoPostoService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import cinema1.model.StatoPostoDTO;

@RestController
@RequestMapping("/api/spettacoli")
public class SpettacoloRestController {

    private final SpettacoloService spettacoloService;
    private final StatoPostoService statoPostoService;

    public SpettacoloRestController(SpettacoloService spettacoloService, StatoPostoService statoPostoService) {
        this.spettacoloService = spettacoloService;
        this.statoPostoService = statoPostoService;
    }

    @GetMapping("/{spettacoloId}/posti")
    public ResponseEntity<List<StatoPostoDTO>> getPostiBySpettacolo(@PathVariable Long spettacoloId) {
        Optional<Spettacolo> spettacoloOpt = spettacoloService.findById(spettacoloId);

        if (spettacoloOpt.isPresent()) {
            List<StatoPosto> statiPosti = statoPostoService.findBySpettacolo(spettacoloOpt.get());

            // Converte la lista di entità in una lista di DTO
            List<StatoPostoDTO> dtoList = statiPosti.stream()
                                                 .map(StatoPostoDTO::new)
                                                 .collect(Collectors.toList());
            return ResponseEntity.ok(dtoList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
}
