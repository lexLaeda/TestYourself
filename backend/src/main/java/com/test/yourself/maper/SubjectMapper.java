package com.test.yourself.maper;

import com.test.yourself.dto.SubjectDTO;
import com.test.yourself.model.Subject;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class SubjectMapper implements Mapper<Subject,SubjectDTO> {
    @Override
    public SubjectDTO toDTO(Subject object) {
        return null;
    }

    @Override
    public Subject fromDTO(SubjectDTO objectDTO) {
        return null;
    }
}
