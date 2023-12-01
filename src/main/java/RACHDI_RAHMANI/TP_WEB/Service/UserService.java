package RACHDI_RAHMANI.TP_WEB.Service;

import RACHDI_RAHMANI.TP_WEB.Dto.UserDto;
import RACHDI_RAHMANI.TP_WEB.Model.User;
import RACHDI_RAHMANI.TP_WEB.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /*private String hashPassword(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }*/

    public User createUser(String username, String password, String nom, String prenom){
        if (userRepository.findByUsername(username) != null){
            throw new RuntimeException("User already exists");
        }
        /*String hashedPassword = hashPassword(password);*/
        User user = new User(username, password, nom, prenom);
        return userRepository.save(user);
    }

    public User findUser(String username){
        if (userRepository.findByUsername(username) == null){
            return null;
        }
        return userRepository.findByUsername(username);
    }

    public void updateUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        user.setPassword(password);
        userRepository.save(user);
    }
}
