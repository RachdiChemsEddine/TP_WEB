package RACHDI_RAHMANI.TP_WEB.Service;

import RACHDI_RAHMANI.TP_WEB.Model.User;
import RACHDI_RAHMANI.TP_WEB.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


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

    public User createUser(String username, String password){
        if (userRepository.findByUsername(username) != null){
            throw new RuntimeException("User already exists");
        }
        /*String hashedPassword = hashPassword(password);*/
        User user = new User(username, password);
        return userRepository.save(user);
    }

    public User findUser(String username){
        if (userRepository.findByUsername(username) == null){
            throw new RuntimeException("User not found");
        }
        return userRepository.findByUsername(username);
    }
}
