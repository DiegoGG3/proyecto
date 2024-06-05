package app.block5crudvalidation.Campeonato.Infraestructure.Controller;


import app.block5crudvalidation.Campeonato.Application.Services.CampeonatoService;
import app.block5crudvalidation.Campeonato.Domain.Entities.Campeonato;
import app.block5crudvalidation.Campeonato.Domain.Mapper.CampeonatoInputMapper;
import app.block5crudvalidation.Campeonato.Domain.Mapper.CampeonatoOutputMapper;
import app.block5crudvalidation.Campeonato.Infraestructure.DTO.CampeonatoInputDTO;
import app.block5crudvalidation.Campeonato.Infraestructure.DTO.CampeonatoOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/campeonatos")
public class CampeonatoController {

    private final CampeonatoInputMapper campeonatoInputMapper;
    private final CampeonatoOutputMapper campeonatoOutputMapper;
    private final CampeonatoService campeonatoService;

    @GetMapping
    public ResponseEntity<?> getAllCampeonatos() {
        List<Campeonato> result = campeonatoService.findAll();
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            List<CampeonatoOutputDTO> dtoList = result.stream()
                    .map(campeonatoOutputMapper::OutputCampeonatoToCampeonatoDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(dtoList);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CampeonatoOutputDTO> getCampeonatoById(@PathVariable Long id) {
        Campeonato campeonato = campeonatoService.findById(id);
        return ResponseEntity.ok(campeonatoOutputMapper.OutputCampeonatoToCampeonatoDto(campeonato));
    }

    @PostMapping
    public CampeonatoInputDTO createAll(@RequestBody Campeonato campeonatos) {
        campeonatoService.save(campeonatos);
        return campeonatoInputMapper.InputCampeonatoToCampeonatoDto(campeonatos);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Campeonato> updateCampeonato(@PathVariable Long id, @RequestBody Campeonato campeonatoDetails) {
        Campeonato campeonato = campeonatoService.findById(id);
        campeonato.setNombre(campeonatoDetails.getNombre());
        campeonato.setFormato(campeonatoDetails.isFormato());
        campeonato.setFechaInicio(campeonatoDetails.getFechaInicio());
        campeonato.setGanador(campeonatoDetails.getGanador());
        campeonato.setFoto(campeonatoDetails.getFoto());
        campeonato.setPais(campeonatoDetails.getPais());

        Campeonato updatedCampeonato = campeonatoService.save(campeonato);
        return ResponseEntity.ok(updatedCampeonato);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Campeonato> deleteCampeonato(@PathVariable Long id) {
        Campeonato campeonato = campeonatoService.findById(id);
        campeonatoService.deleteById(id);
        return ResponseEntity.ok(campeonato);
    }
}
