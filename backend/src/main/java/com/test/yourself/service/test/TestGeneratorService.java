package com.test.yourself.service.test;

import com.test.yourself.model.testsystem.subject.Subject;
import com.test.yourself.model.testsystem.test.SubjectTest;

public interface TestGeneratorService {
    SubjectTest generateTest(Subject subject, int size);
}
