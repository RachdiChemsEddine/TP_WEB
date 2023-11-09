package RACHDI_RAHMANI.TP_WEB.Mapper;

import RACHDI_RAHMANI.TP_WEB.Dto.UserDto;
import RACHDI_RAHMANI.TP_WEB.Model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "someField", ignore = true) // Exemple d'ignorance d'un champ lors du mapping
    UserDto userToUserDto(User user);

    User userDtoToUser(UserDto userDto);
}
