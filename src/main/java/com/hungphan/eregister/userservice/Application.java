package com.hungphan.eregister.userservice;

import com.hungphan.eregister.userservice.model.User;
import com.hungphan.eregister.userservice.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class, args);
    }

    @Profile("init")
    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) {
        return args -> {
            userRepository.save(new User("harry", "$2a$12$8DIcCrCh4wAl2GfUO6REv.5Btc5hHXT3ybN9tfzarFc0CvX.F/JzO", "Harry Potter", 20, 10l, 0l));
            userRepository.save(new User("peter", "$2a$12$8DIcCrCh4wAl2GfUO6REv.5Btc5hHXT3ybN9tfzarFc0CvX.F/JzO", "Peter Pevensia", 22, 20l, 0l));
            userRepository.save(new User("gandalf", "$2a$12$8DIcCrCh4wAl2GfUO6REv.5Btc5hHXT3ybN9tfzarFc0CvX.F/JzO", "Gandalf The White", 100, 30l, 0l));
        };
    }

    @Profile("stress-test")
    @Bean
    CommandLineRunner initDatabase2(UserRepository userRepository) {
        return args -> {
            for (int i=1; i<=1000; i++) {
                userRepository.save(new User("user"+i, "$2a$12$8DIcCrCh4wAl2GfUO6REv.5Btc5hHXT3ybN9tfzarFc0CvX.F/JzO", "user "+i, 20, 20l, 0l));
            }
        };
    }
}
