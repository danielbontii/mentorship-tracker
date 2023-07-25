package com.danielbbontii.mentorshiptracker;

import com.danielbbontii.mentorshiptracker.services.SeederService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MentorshipTrackerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MentorshipTrackerApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(SeederService seederService) {
        return args -> seederService.seed();
    }

}
