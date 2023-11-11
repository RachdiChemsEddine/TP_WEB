package RACHDI_RAHMANI.TP_WEB.Controller;

import RACHDI_RAHMANI.TP_WEB.Dto.EvenementDto;
import RACHDI_RAHMANI.TP_WEB.Mapper.EvenementMapper;
import RACHDI_RAHMANI.TP_WEB.Model.Evenement;
import RACHDI_RAHMANI.TP_WEB.Service.EvenementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/evenements")
public class EvenementController {

    private final EvenementService evenementService;

    @Autowired
    public EvenementController(EvenementService evenementService) {
        this.evenementService = evenementService;
    }

    @GetMapping
    public ResponseEntity<List<EvenementDto>> getAllEvenements() {
        List<EvenementDto> evenementDtos = evenementService.getAllEvenements()
                .stream()
                .map(EvenementMapper.INSTANCE::evenementToEvenementDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(evenementDtos);
    }

    @GetMapping("/{evenementId}")
    public ResponseEntity<EvenementDto> getEvenementById(@PathVariable Long evenementId) {
        EvenementDto evenementDto = EvenementMapper.INSTANCE.evenementToEvenementDto(evenementService.getEvenementById(evenementId));

        return ResponseEntity.ok(evenementDto);
    }

    @PostMapping
    public ResponseEntity<EvenementDto> createEvenement(@RequestBody EvenementDto evenementDto) {
        Evenement newEvenement = EvenementMapper.INSTANCE.evenementDtoToEvenement(evenementDto);
        Evenement createdEvenement = evenementService.createEvenement(newEvenement);

        EvenementDto createdEvenementDto = EvenementMapper.INSTANCE.evenementToEvenementDto(createdEvenement);

        return ResponseEntity.ok(createdEvenementDto);
    }

    @PutMapping("/{evenementId}")
    public ResponseEntity<EvenementDto> updateEvenement(@PathVariable Long evenementId, @RequestBody EvenementDto updatedEvenementDto) {
        Evenement updatedEvenement = EvenementMapper.INSTANCE.evenementDtoToEvenement(updatedEvenementDto);
        EvenementDto resultEvenementDto = EvenementMapper.INSTANCE.evenementToEvenementDto(evenementService.updateEvenement(evenementId, updatedEvenement));

        return ResponseEntity.ok(resultEvenementDto);
    }

    @DeleteMapping("/{evenementId}")
    public ResponseEntity<String> deleteEvenement(@PathVariable Long evenementId) {
        evenementService.deleteEvenement(evenementId);
        return ResponseEntity.ok("Evenement deleted successfully");
    }
}
