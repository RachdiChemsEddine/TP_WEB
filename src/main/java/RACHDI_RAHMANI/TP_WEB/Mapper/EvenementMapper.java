package RACHDI_RAHMANI.TP_WEB.Mapper;


import RACHDI_RAHMANI.TP_WEB.Dto.EvenementDto;
import RACHDI_RAHMANI.TP_WEB.Model.Evenement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EvenementMapper {
    EvenementMapper INSTANCE = Mappers.getMapper(EvenementMapper.class);

    EvenementDto evenementToEvenementDto(Evenement evenement);

    Evenement evenementDtoToEvenement(EvenementDto evenementDto);

    // Ajoutez d'autres méthodes de mappage au besoin
}
