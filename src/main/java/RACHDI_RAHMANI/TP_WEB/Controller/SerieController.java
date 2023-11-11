package RACHDI_RAHMANI.TP_WEB.Controller;

import RACHDI_RAHMANI.TP_WEB.Dto.SerieDto;
import RACHDI_RAHMANI.TP_WEB.Model.Serie;
import RACHDI_RAHMANI.TP_WEB.Service.SerieService;
import RACHDI_RAHMANI.TP_WEB.Mapper.SerieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/serie")
public class SerieController {

    private final SerieService serieService;

    @Autowired
    public SerieController(SerieService serieService) {
        this.serieService = serieService;
    }

    @GetMapping
    public ResponseEntity<List<SerieDto>> getAllSerie() {
        List<SerieDto> SerieDtos = serieService.getAllSerie()
                .stream()
                .map(SerieMapper.INSTANCE::SerieToSerieDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(SerieDtos);
    }

    @GetMapping("/{SerieId}")
    public ResponseEntity<SerieDto> getSerieById(@PathVariable Long SerieId) {
        SerieDto SerieDto = SerieMapper.INSTANCE.SerieToSerieDto(serieService.getSerieById(SerieId));

        return ResponseEntity.ok(SerieDto);
    }

    @PostMapping
    public ResponseEntity<SerieDto> createSerie(@RequestBody SerieDto SerieDto) {
        Serie newSerie = SerieMapper.INSTANCE.SerieDtoToSerie(SerieDto);
        Serie createdSerie = serieService.createSerie(newSerie);

        SerieDto createdSerieDto = SerieMapper.INSTANCE.SerieToSerieDto(createdSerie);

        return ResponseEntity.ok(createdSerieDto);
    }

    @PutMapping("/{serieId}")
    public ResponseEntity<SerieDto> updateSerie(@PathVariable Long SerieId, @RequestBody SerieDto updatedSerieDto) {
        Serie updatedSerie = SerieMapper.INSTANCE.SerieDtoToSerie(updatedSerieDto);
        SerieDto resultSerieDto = SerieMapper.INSTANCE.SerieToSerieDto(serieService.updateSerie(SerieId, updatedSerie));

        return ResponseEntity.ok(resultSerieDto);
    }

    @DeleteMapping("/{SerieId}")
    public ResponseEntity<String> deleteSerie(@PathVariable Long SerieId) {
        serieService.deleteSerie(SerieId);
        return ResponseEntity.ok("Serie deleted successfully");
    }
}
