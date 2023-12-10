package RACHDI_RAHMANI.TP_WEB.Service;

import RACHDI_RAHMANI.TP_WEB.Model.User;
import RACHDI_RAHMANI.TP_WEB.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateUser() {
        User user = new User("john_doe", "password", "John", "Doe");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User createdUser = userService.createUser("john_doe", "password", "John", "Doe");

        assertNotNull(createdUser);
        assertEquals("john_doe", createdUser.getUsername());
        assertEquals("John", createdUser.getNom());
        assertEquals("Doe", createdUser.getPrenom());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testFindUser() {
        User user = new User("Chems", "password", "Chems", "eddine");
        when(userRepository.findByUsername("Chems")).thenReturn(user);

        User foundUser = userService.findUser("Chems");

        assertNotNull(foundUser);
        assertEquals("Chems", foundUser.getUsername());
        assertEquals("Chems", foundUser.getNom());
        assertEquals("eddine", foundUser.getPrenom());
    }

    @Test
    void testUpdateUser() {
        User user = new User("john_doe", "password", "John", "Doe");
        User updatedUser = new User("john_doe", "1234", "John", "Doe");
        when(userRepository.findByUsername("john_doe")).thenReturn(user);

        userService.updateUser("john_doe", updatedUser);

        verify(userRepository, times(1)).save(user);
        assertEquals("1234", user.getPassword());
    }

    @Test
    void testGetAllUsers() {
        List<User> userList = new ArrayList<>();
        userList.add(new User("john_doe", "password", "John", "Doe"));
        userList.add(new User("jane_smith", "password", "Jane", "Smith"));
        when(userRepository.findAll()).thenReturn(userList);

        List<User> allUsers = userService.getAllUsers();

        assertNotNull(allUsers);
        assertEquals(2, allUsers.size());
        assertEquals("john_doe", allUsers.get(0).getUsername());
        assertEquals("Jane", allUsers.get(1).getNom());
    }
}