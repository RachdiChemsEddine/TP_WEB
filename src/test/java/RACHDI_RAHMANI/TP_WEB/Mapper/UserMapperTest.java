package RACHDI_RAHMANI.TP_WEB.Mapper;
import RACHDI_RAHMANI.TP_WEB.Dto.UserDto;
import RACHDI_RAHMANI.TP_WEB.Model.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserMapperTest {
    private final UserMapper userMapper = UserMapper.INSTANCE;

    @Test
    public void testUserToUserDto() {
        // Créez un objet User pour les tests
        User user = new User();
        user.setUsername("john doe");
        // Ajoutez d'autres propriétés au besoin

        // Utilisez le mapper pour convertir l'objet User en UserDto
        UserDto userDto = userMapper.userToUserDto(user);

        // Vérifiez les résultats
        assertEquals(user.getUsername(), userDto.getUsername());
        // Ajoutez d'autres vérifications au besoin
    }

    @Test
    public void testUserDtoToUser() {
        // Créez un objet UserDto pour les tests
        UserDto userDto = new UserDto();
        userDto.setUsername("john doe");
        // Ajoutez d'autres propriétés au besoin

        // Utilisez le mapper pour convertir l'objet UserDto en User
        User user = userMapper.userDtoToUser(userDto);

        // Vérifiez les résultats
        assertEquals(userDto.getUsername(), user.getUsername());
    }
}
