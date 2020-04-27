package com.test.yourself.repository;

import com.test.yourself.model.testsystem.subject.Subject;
import com.test.yourself.model.testsystem.test.SubjectTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<SubjectTest,Long> {
    SubjectTest findByName(String name);
    List<SubjectTest> findAllBySubjectId(Long subjectId);
}
