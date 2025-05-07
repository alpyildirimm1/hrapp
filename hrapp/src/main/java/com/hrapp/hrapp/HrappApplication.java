package com.hrapp.hrapp;
import com.hrapp.hrapp.domain.model.User;
import com.hrapp.hrapp.domain.model.User.Role;
import com.hrapp.hrapp.domain.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HrappApplication {

	public static void main(String[] args) {
		SpringApplication.run(HrappApplication.class, args);
	}

	@Bean
	public CommandLineRunner initAdmin(UserRepository userRepository) {
		return args -> {
			String defaultUsername = "admin";
			if (!userRepository.existsByUsername(defaultUsername)) {
				User admin = new User();
				admin.setUsername(defaultUsername);
				admin.setPassword("admin123"); // burada şifreyi bcrypt ile şifrelemen önerilir
				admin.setRole(Role.ROLE_ADMIN);
				userRepository.save(admin);
				System.out.println("🌟 İlk ADMIN kullanıcı oluşturuldu: admin / admin123");
			}
		};
	}
}

