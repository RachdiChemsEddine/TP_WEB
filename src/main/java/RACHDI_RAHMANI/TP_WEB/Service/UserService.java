package RACHDI_RAHMANI.TP_WEB.Service;

import RACHDI_RAHMANI.TP_WEB.Model.User;
import RACHDI_RAHMANI.TP_WEB.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String username, String password, String nom, String prenom){
        User user = new User(username, password, nom, prenom);
        return userRepository.save(user);
    }

    public User findUser(String username){
        if (userRepository.findByUsername(username) == null){
            throw new RuntimeException("User "+username+" not found");
        }
        return userRepository.findByUsername(username);
    }

    public void updateUser(String username, User updatedUser){
        User user = userRepository.findByUsername(username);
        if (updatedUser.getUsername() != null){
            user.setUsername(updatedUser.getUsername());
        }
        if (updatedUser.getNom() != null){
            user.setNom(updatedUser.getNom());
        }
        if (updatedUser.getPrenom() != null){
            user.setPrenom(updatedUser.getPrenom());
        }
        if (updatedUser.getPassword() != null){
            user.setPassword(updatedUser.getPassword());
        };
        if (updatedUser.getOwnSeries() != null){
            user.setOwnSeries(updatedUser.getOwnSeries());
        }
        if (updatedUser.getSharedSeries() != null){
            user.setSharedSeries(updatedUser.getSharedSeries());
        }
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
