package RACHDI_RAHMANI.TP_WEB.Controller;

import RACHDI_RAHMANI.TP_WEB.Model.User;
import RACHDI_RAHMANI.TP_WEB.Model.RegistrationRequest;
import RACHDI_RAHMANI.TP_WEB.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final HttpSession httpSession;

    @Autowired
    public UserController(UserService userService, HttpSession httpSession) {
        this.userService = userService;
        this.httpSession = httpSession;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegistrationRequest registrationRequest) {
        // Récupérez les détails de la demande d'inscription (par exemple, nom d'utilisateur et mot de passe)
        String username = registrationRequest.getUsername();
        String password = registrationRequest.getPassword();
        String nom = registrationRequest.getNom();
        String prenom = registrationRequest.getPrenom();

        // Créez un nouvel utilisateur
        userService.createUser(username, password, nom, prenom);

        // Exemple d'utilisation de la session
        httpSession.setAttribute("username", username);

        return ResponseEntity.ok("Utilisateur enregistré avec succès");
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUserDetails(@PathVariable String username) {
        // Récupérez les détails de l'utilisateur
        if (userService.findUser(username) == null){
            return ResponseEntity.notFound().build();
        }
        User userDetails = userService.findUser(username);

        return ResponseEntity.ok(userDetails);
    }

    @PutMapping("/{username}")
    public ResponseEntity<String> updateUserDetails(@PathVariable String username, @RequestParam String password) {
        // Mettre à jour les détails de l'utilisateur
        if (userService.findUser(username) == null){
            return ResponseEntity.badRequest().body("Utilisateur non trouvé");
        }
        userService.updateUser(username, password);

        // Exemple d'utilisation de la session
        httpSession.setAttribute("username", username);

        return ResponseEntity.ok("Détails utilisateur mis à jour avec succès");
    }
}
