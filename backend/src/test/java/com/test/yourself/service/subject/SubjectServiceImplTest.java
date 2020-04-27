package com.test.yourself.service.subject;

import com.test.yourself.exception.SubjectNotFoundException;
import com.test.yourself.exception.SubjectServiceValidationException;
import com.test.yourself.model.testsystem.subject.Subject;
import com.test.yourself.repository.SubjectRepository;
import com.test.yourself.service.test.SubjectService;
import com.test.yourself.service.test.SubjectServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.*;

import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)
public class SubjectServiceImplTest {

    @Mock
    private SubjectRepository repository;

    private SubjectService subjectService;
    private Subject java;
    private Subject sameSubject;
    private Subject javaScript;
    private Long testId = 1000L;
    private Long fakeTestId = 1L;
    private List<Subject> subjectList;

    @Before
    public void setUp() throws Exception {
        java = new Subject();
        java.setId(testId);
        java.setName("Java");
        java.setDescription("OOP");

        sameSubject = new Subject();
        sameSubject.setName("Java");
        sameSubject.setDescription("OOP");

        javaScript = new Subject();
        javaScript.setName("JavaScript");
        javaScript.setDescription("Script");

        subjectList = new ArrayList<>();
        subjectList.add(java);

        subjectService = new SubjectServiceImpl(repository);

        Mockito.when(repository.saveAndFlush(javaScript)).thenReturn(javaScript);
        Mockito.when(repository.save(javaScript)).thenReturn(javaScript);
        Mockito.when(repository.findAll()).thenReturn(subjectList);
        Mockito.when(repository.findById(testId)).thenReturn(Optional.of(java));
        Mockito.when(repository.findById(fakeTestId)).thenThrow(new SubjectNotFoundException());
        Mockito.when(repository.findByName(java.getName())).thenReturn(java);
        Mockito.when(repository.findByName(javaScript.getName())).thenReturn(null);

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testFindSubjectByName() {
        Subject javaFromService = subjectService.findByName(java.getName());
        assertEquals(java,javaFromService);
    }

    @Test
    public void testFindAllSubjects() {
        assertEquals(subjectList,subjectService.findAll());
    }

    @Test
    public void testFindMapSubjects() {
        Map<Long,String> map = new HashMap<>();
        map.put(java.getId(),java.getName());
        assertEquals(map,subjectService.findMapSubjects());
    }

    @Test
    public void testFindSubjectById() {

        assertEquals(java,subjectService.findById(testId));
    }

    @Test(expected = SubjectNotFoundException.class)
    public void testExceptionFindSubjectById() {
        assertEquals(java,subjectService.findById(fakeTestId));
    }

    @Test
    public void TestAdd() {
        assertEquals(javaScript,subjectService.add(javaScript));
    }

    @Test(expected = SubjectServiceValidationException.class)
    public void TestExceptionAdd() {
        assertEquals(java,subjectService.add(java));
    }

    @Test
    public void TestUpdate() {
        //TODO разобраться почему фейлится
        Subject updated = subjectService.update(testId,java);


    }

    @Test
    public void TestUpdateName() {
        java.setName("newJava");
        Subject subject = subjectService.updateName("newJava", testId);

    }

}