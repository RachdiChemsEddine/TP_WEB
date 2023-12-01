package RACHDI_RAHMANI.TP_WEB.Service;

import RACHDI_RAHMANI.TP_WEB.Dto.UserDto;
import RACHDI_RAHMANI.TP_WEB.Model.User;
import RACHDI_RAHMANI.TP_WEB.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testGetUserById() {
        // Créez un objet User pour les tests
        User user = new User();
        user.setId(1L);
        user.setUsername("john.doe");
        // Ajoutez d'autres propriétés au besoin

        // Configurez le comportement simulé du repository

        // Appelez la méthode du service à tester
        /*UserDto userDTO = userService.getUserByUsername(1L);*/

        // Vérifiez les résultats
        /*assertEquals(user.getUsername(), userDTO.getUsername());*/
        // Ajoutez d'autres vérifications au besoin
    }
}
