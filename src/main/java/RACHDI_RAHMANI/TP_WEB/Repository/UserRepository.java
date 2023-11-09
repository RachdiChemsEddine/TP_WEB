package RACHDI_RAHMANI.TP_WEB.Repository;
import RACHDI_RAHMANI.TP_WEB.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    void deleteByUsername(String username);
}
