package RACHDI_RAHMANI.TP_WEB.Controller;

import RACHDI_RAHMANI.TP_WEB.Model.User;
import RACHDI_RAHMANI.TP_WEB.Model.RegistrationRequest;
import RACHDI_RAHMANI.TP_WEB.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/resgister")
    public ResponseEntity<String> registerUser(@RequestBody RegistrationRequest registrationRequest) {
        // Récupérez les détails de la demande d'inscription (par exemple, nom d'utilisateur et mot de passe)
        String username = registrationRequest.getUsername();
        String password = registrationRequest.getPassword();

        // Créez un nouvel utilisateur
        userService.createUser(username, password);

        return ResponseEntity.ok("Utilisateur enregistré avec succès");
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUserDetails(@PathVariable String username) {
        // Récupérez les détails de l'utilisateur
        User userDetails = userService.findUser(username);

        return ResponseEntity.ok(userDetails);
    }
}
