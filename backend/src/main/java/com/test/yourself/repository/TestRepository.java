package com.test.yourself.repository;

import com.test.yourself.model.Subject;
import com.test.yourself.model.test.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<Test,Long> {
    Test findByName(String name);
    List<Test> findAllBySubjectId(Long subjectId);
    void deleteAllBySubject(Subject subject);
    void deleteAllBySubjectId(Long subjectId);
}
