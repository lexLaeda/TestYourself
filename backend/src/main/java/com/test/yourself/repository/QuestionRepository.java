package com.test.yourself.repository;

import com.test.yourself.model.testsystem.subject.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {
    List<Question> findAllBySubjectId(Long id);
    Question findByName(String name);
}
