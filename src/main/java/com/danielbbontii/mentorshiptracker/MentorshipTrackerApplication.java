package com.danielbbontii.mentorshiptracker;

import com.danielbbontii.mentorshiptracker.services.SeederService;
import org.springframework.beans.factory.annotation.Qualifier;
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
    CommandLineRunner commandLineRunner(@Qualifier("admin") SeederService seederService) {
        return args -> seederService.seed();
    }

}
