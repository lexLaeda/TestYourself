package com.test.yourself.repository;

import com.test.yourself.model.testsystem.test.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestResultsRepository extends JpaRepository<TestResult,Long> {
}
