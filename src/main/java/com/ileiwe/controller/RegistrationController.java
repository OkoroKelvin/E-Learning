package com.ileiwe.controller;


import com.ileiwe.data.dto.InstructorPartyDto;
import com.ileiwe.service.InstructorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RegistrationController {

    @Autowired
    InstructorServiceImpl instructorServiceImpl;

//    @PostMapping("/instructor")
//    public ResponseEntity<?> registerAsInstructor(@RequestBody InstructorPartyDto) {
//
//    }
}
