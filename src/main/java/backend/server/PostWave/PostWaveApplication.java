package backend.server.PostWave;

import backend.server.PostWave.dto.RegisterRequest;
import backend.server.PostWave.model.enums.Role;
import backend.server.PostWave.service.auth.AuthService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PostWaveApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostWaveApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner commandLineRunner(AuthService authService) {
//		return args -> {
//			var admin = RegisterRequest
//					.builder()
//					.firstname("admin")
//					.lastname("admin")
//					.email("admin@gmail.com")
//					.password("admin")
//					.role(Role.ADMIN)
//					.build();
//			System.out.println("admin token"+ authService.register(admin).getAccessToken());
//		};
//	}

}
