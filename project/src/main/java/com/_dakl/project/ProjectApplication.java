package com._dakl.project;

import com._dakl.project.model.User;
import com._dakl.project.services.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}
        @Bean
        CommandLineRunner init(StorageService storageService)
        {
            return (args) -> {
                storageService.init();
            };
        }
        
        @Bean(name ="USER_BEAN")
        public User setUser()
        {
            User u = new User();
            u.setUserName("tdl");
            u.setPassWord("ok");
            return  u;
        }
}
