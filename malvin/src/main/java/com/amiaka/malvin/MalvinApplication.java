package com.amiaka.malvin;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.amiaka.malvin.Model.User;
import com.amiaka.malvin.Repository.UserRepo;

@SpringBootApplication
public class MalvinApplication {

	public static void main(String[] args) {
		SpringApplication.run(MalvinApplication.class, args);
		System.out.print("hello worlds");
	}

	@Bean
	CommandLineRunner run (UserRepo userRepo, PasswordEncoder passwordEncoder) {
		return args -> {
			if(userRepo.findByRole("ROLE_ADMIN") != null) return;
			  User user = new User();
			  user.setFirstname("Amiaka");
			  user.setLastname("Kingsley");
			  user.setEmail("kingsconnect@gmail.com");
			  user.setPassword(passwordEncoder.encode("kingsconnect"));
			  user.setRole("ROLE_ADMIN");

			  userRepo.save(user);
		};

}
}