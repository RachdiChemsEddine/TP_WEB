package RACHDI_RAHMANI.TP_WEB.Controller;


import RACHDI_RAHMANI.TP_WEB.Model.User;
import RACHDI_RAHMANI.TP_WEB.Model.RegistrationRequest;
import RACHDI_RAHMANI.TP_WEB.Service.UserService;
import RACHDI_RAHMANI.TP_WEB.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private HttpSession httpSession;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testLoginUserSuccess() {
        String username = "john_doe";
        String password = "password";
        User user = new User(username, password, "John", "Doe");
        when(userService.findUser(username)).thenReturn(user);
        when(httpSession.getAttribute("username")).thenReturn(null);

        ResponseEntity<String> response = userController.loginUser(username, password);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Authentication successful", response.getBody());
        verify(httpSession, times(1)).setAttribute("username", username);
    }


    @Test
    void testLoginUserFailure() {
        String username = "john_doe";
        String password = "incorrect_password";
        when(userService.findUser(username)).thenReturn(null);

        ResponseEntity<String> response = userController.loginUser(username, password);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Authentication failed", response.getBody());
        verify(httpSession, never()).setAttribute(eq("username"), anyString());
    }

    @Test
    void testGetSessionInfoWithUsernameInSession() {
        String username = "john_doe";
        when(httpSession.getAttribute("username")).thenReturn(username);

        ResponseEntity<String> response = userController.getSessionInfo();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Username in session: " + username, response.getBody());
    }

    @Test
    void testGetSessionInfoWithNoUsernameInSession() {
        when(httpSession.getAttribute("username")).thenReturn(null);

        ResponseEntity<String> response = userController.getSessionInfo();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("No username in session", response.getBody());
    }

    @Test
    void testRegisterUserSuccess() {
        String username = "john_doe";
        String password = "password";
        String nom = "John";
        String prenom = "Doe";
        RegistrationRequest registrationRequest = new RegistrationRequest(username, password, nom, prenom);

        when(userRepository.findByUsername(username)).thenReturn(null);

        ResponseEntity<User> response = userController.registerUser(registrationRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(userService, times(1)).createUser(username, password, nom, prenom);
        verify(httpSession, times(1)).setAttribute("username", username);
    }

    @Test
    void testRegisterUserAlreadyExists() {
        String username = "john_doe";
        String password = "password";
        String nom = "John";
        String prenom = "Doe";
        RegistrationRequest registrationRequest = new RegistrationRequest(username, password, nom, prenom);

        when(userRepository.findByUsername(username)).thenReturn(new User(username, password, nom, prenom));

        ResponseEntity<User> response = userController.registerUser(registrationRequest);

        assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
        verify(userService, never()).createUser(username, password, nom, prenom);
        verify(httpSession, never()).setAttribute("username", username);
    }

    @Test
    void testGetUserDetailsFound() {
        String username = "john_doe";
        User user = new User(username, "password", "John", "Doe");

        when(userService.findUser(username)).thenReturn(user);

        ResponseEntity<User> response = userController.getUserDetails(username);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    void testGetUserDetailsNotFound() {
        String username = "john_doe";
        when(userService.findUser(username)).thenReturn(null);

        ResponseEntity<User> response = userController.getUserDetails(username);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testUpdateUserDetailsSuccess() {
        String username = "john_doe";
        User updatedUser = new User(username, "new_password", "John", "Doe");

        when(userService.findUser(username)).thenReturn(updatedUser);

        ResponseEntity<String> response = userController.updateUserDetails(username, updatedUser);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(userService, times(1)).updateUser(username, updatedUser);
        verify(httpSession, times(1)).setAttribute("username", username);
    }

    @Test
    void testUpdateUserDetailsUserNotFound() {
        String username = "john_doe";
        User updatedUser = new User(username, "new_password", "John", "Doe");

        when(userService.findUser(username)).thenReturn(null);

        ResponseEntity<String> response = userController.updateUserDetails(username, updatedUser);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Utilisateur non trouvé", response.getBody());
        verify(userService, never()).updateUser(username, updatedUser);
        verify(httpSession, never()).setAttribute("username", username);
    }

    @Test
    void testLogoutUserWithUsernameInSession() {
        String username = "john_doe";
        when(httpSession.getAttribute("username")).thenReturn(username);

        ResponseEntity<String> response = userController.logoutUser(httpSession);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Déconnexion réussie pour l'utilisateur : " + username, response.getBody());
        verify(httpSession, times(1)).removeAttribute("username");
    }

    @Test
    void testLogoutUserWithNoUsernameInSession() {
        when(httpSession.getAttribute("username")).thenReturn(null);

        ResponseEntity<String> response = userController.logoutUser(httpSession);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Aucun utilisateur connecté", response.getBody());
        verify(httpSession, never()).removeAttribute("username");
    }


}