package RACHDI_RAHMANI.TP_WEB;

import RACHDI_RAHMANI.TP_WEB.Authentification.AuthentificationFilter;
import RACHDI_RAHMANI.TP_WEB.Service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TpWebApplication {
	public static void main(String[] args) {
		SpringApplication.run(TpWebApplication.class, args
		);
	}
	@Bean
	public AuthentificationFilter authenticationFilter(UserService userService) {
		return new AuthentificationFilter(userService);
	}
}
