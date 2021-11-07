package com.ileiwe.data.repository;

import com.ileiwe.data.model.Authority;
import com.ileiwe.data.model.Instructor;
import com.ileiwe.data.model.LearningParty;
import com.ileiwe.data.model.Role;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;

import javax.validation.ConstraintViolationException;

import java.util.Optional;

import static java.text.MessageFormat.format;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Slf4j
@Sql(scripts =("/db/insert.sql"))

class InstructorRepositoryTest {

    @Autowired
    InstructorRepository instructorRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void saveInstructorAsLearningPartyTest() {
        LearningParty user = new LearningParty("trained@ileiwe.com",
                "1234pass", new Authority(Role.ROLE_INSTRUCTOR));


        Instructor instructor = Instructor.builder()
                .firstname("John")
                .lastname("Alao")
                .learningParty(user).build();

        instructorRepository.save(instructor);
        assertThat(instructor.getId()).isNotNull();
        assertThat(instructor.getLearningParty().getId()).isNotNull();
        log.info("Instructor after saving -> {}", instructor);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void instructorWithNullValuesTest() {
        LearningParty user = new LearningParty("trained@ileiwe.com",
                "1234pass", new Authority(Role.ROLE_INSTRUCTOR));


        Instructor instructor = Instructor.builder()
                .firstname(null)
                .lastname(null)
                .learningParty(user).build();
        assertThrows(ConstraintViolationException.class, () -> instructorRepository.save(instructor));
    }

    @Test
    void instructorWithEmptyStringAndWhiteSpaceTest() {
        LearningParty user = new LearningParty("trained@ileiwe.com",
                "1234pass", new Authority(Role.ROLE_INSTRUCTOR));

        Instructor instructor = Instructor.builder()
                .firstname("")
                .lastname("")
                .learningParty(user).build();
        assertThrows(ConstraintViolationException.class, () -> instructorRepository.save(instructor));
    }

    @Test
    void createInstructorUniqueEmailsTest() {
        LearningParty user = new LearningParty("trained@ileiwe.com",
                "1234pass", new Authority(Role.ROLE_INSTRUCTOR));

        Instructor instructor = Instructor.builder()
                .firstname("John")
                .lastname("Alao")
                .learningParty(user).build();

        instructorRepository.save(instructor);
        assertThat(instructor.getId()).isNotNull();
        assertThat(instructor.getLearningParty().getEmail()).isEqualTo("trained@ileiwe.com");
        assertThat(instructor.getLearningParty().getAuthorities().get(0).getAuthority()).isEqualTo(Role.ROLE_INSTRUCTOR);
        assertThat(instructor.getLearningParty().getAuthorities().get(0)).isNotNull();

        LearningParty user2 = new LearningParty("trained@ileiwe.com",
                "1234pass", new Authority(Role.ROLE_INSTRUCTOR));

        Instructor instructor2 = Instructor.builder()
                .firstname("John")
                .lastname("Alao")
                .learningParty(user2).build();

        assertThrows(DataIntegrityViolationException.class, () -> instructorRepository.save(instructor2));
    }


    @Test
    void updateInstructorTableAfterCreateTest() {
        LearningParty user = new LearningParty("trained@ileiwe.com",
                "1234pass", new Authority(Role.ROLE_INSTRUCTOR));

        Instructor instructor = Instructor.builder()
                .firstname("John")
                .lastname("Alao")
                .learningParty(user).build();


        log.info("Instructor before saving --> {}", instructor);
        instructorRepository.save(instructor);
        assertThat(instructor.getId()).isNotNull();
        assertThat(instructor.getLearningParty().getId()).isNotNull();
        log.info("Instructor after saving --> {}", instructor);


        String biography = """
                A Biochemist turned Software engineer. He vows to kiss Biological sciences with Technology.
                He hails from Delta State. A graduate of Ekiti State University.
                He is passionate about Technology and bring in as many people he can bring in.
                """;
        String specialization = """
                International Speaker
                Biotechnologist
                Software Engineer
                Data Scientist with Python
                """;
        instructor.setBio(biography);
        instructor.setSpecialization(specialization);



        instructorRepository.save(instructor);

        Instructor foundInstructor = instructorRepository.findById(instructor.getId()).orElse(null);


        assertThat(instructor.getBio()).isEqualTo(foundInstructor.getBio());
        assertThat(foundInstructor.getBio()).isEqualTo(biography);
        assertThat(foundInstructor.getSpecialization()).isEqualTo(specialization);

    }

}