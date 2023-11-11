package RACHDI_RAHMANI.TP_WEB.Mapper;


import RACHDI_RAHMANI.TP_WEB.Dto.SerieDto;
import RACHDI_RAHMANI.TP_WEB.Model.Serie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SerieMapper {

    SerieMapper INSTANCE = Mappers.getMapper(SerieMapper.class);

    SerieDto SerieToSerieDto(Serie serie);

    Serie SerieDtoToSerie(SerieDto serieDto);
}
