package com.ileiwe.service;


import com.ileiwe.data.dto.InstructorPartyDto;
import com.ileiwe.data.model.Authority;
import com.ileiwe.data.model.Instructor;
import com.ileiwe.data.model.LearningParty;
import com.ileiwe.data.model.Role;
import com.ileiwe.data.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstructorServiceImpl implements InstructorService{

    @Autowired
    InstructorRepository instructorRepository;

    @Override
    public Instructor save(InstructorPartyDto instructorDto) {
        if(instructorDto == null){
            throw new IllegalArgumentException("Instruct");
        }
       LearningParty learningParty = new LearningParty(instructorDto.getEmail(),
               instructorDto.getPassword(),
               new Authority(Role.ROLE_INSTRUCTOR));

        Instructor instructor = Instructor.builder()
                .lastname(instructorDto.getLastname())
                .firstname(instructorDto.getFistName())
                .learningParty(learningParty).build();

        return instructorRepository.save(instructor);
    }
}
