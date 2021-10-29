package com.ileiwe.service;

import com.ileiwe.data.dto.InstructorPartyDto;
import com.ileiwe.data.model.Instructor;

public interface InstructorService {
    Instructor save(InstructorPartyDto instructorPartyDto);

}
