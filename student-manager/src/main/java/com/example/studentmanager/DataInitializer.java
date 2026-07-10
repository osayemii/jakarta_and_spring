package com.example.studentmanager;

import com.example.studentmanager.model.AppUser;
import com.example.studentmanager.model.Student;
import com.example.studentmanager.repository.AppUserRepository;
import com.example.studentmanager.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

/**
 * Seeds:
 *  - 1 admin user  (username: admin, password: admin123)
 *  - 5 students from Table 5.3 (pg 202)
 */
@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner seedData(StudentRepository studentRepo,
                                      AppUserRepository userRepo,
                                      PasswordEncoder encoder) {
        return args -> {
            // Seed admin user
            if (userRepo.count() == 0) {
                AppUser admin = new AppUser();
                admin.setUsername("admin");
                admin.setPassword(encoder.encode("admin123"));
                admin.setRole("ROLE_ADMIN");
                userRepo.save(admin);
                System.out.println(">>> Admin user created: admin / admin123");
            }

            // Seed students from Table 5.3
            if (studentRepo.count() == 0) {
                List<Student> students = List.of(
                    createStudent("S_001", "John Doe",      "john.doe@example.com",   "Computer Science",        "4 years"),
                    createStudent("S_002", "Jane Smith",    "jane.smith@example.com", "Electrical Engineering",  "5 years"),
                    createStudent("S_003", "Alice Johnson", "alice.j@example.com",    "Business Administration", "3 years"),
                    createStudent("S_004", "Bob Brown",     "bob.b@example.com",      "Mechanical Engineering",  "4 years"),
                    createStudent("S_005", "Emily Wilson",  "emily.w@example.com",    "Psychology",              "2 years")
                );
                studentRepo.saveAll(students);
                System.out.println(">>> Seeded 5 students from Table 5.3");
            }
        };
    }

    private Student createStudent(String id, String name, String email, String course, String duration) {
        Student s = new Student();
        s.setStudentId(id);
        s.setStudentName(name);
        s.setEmail(email);
        s.setCourse(course);
        s.setDuration(duration);
        return s;
    }
}
