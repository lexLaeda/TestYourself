package com.test.yourself.service;

import com.test.yourself.dto.SubjectDTO;
import com.test.yourself.exception.SubjectNotFoundException;
import com.test.yourself.model.Subject;
import com.test.yourself.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl implements SubjectService {

    private SubjectRepository subjectRepository;

    public SubjectServiceImpl(SubjectRepository subjectRepository){
        this.subjectRepository = subjectRepository;
    }

    @Override
    public Subject findSubjectByName(String name) {
        return subjectRepository.findByName(name);
    }

    @Override
    public List<Subject> findAllSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public Map<Long, String> findMapSubjects() {
        List<Subject> subjects = subjectRepository.findAll();
        return subjects.stream().collect(Collectors.toMap(Subject::getId,Subject::getName));
    }

    @Override
    public Subject addSubject(Subject subject) {
        return subjectRepository.saveAndFlush(subject);
    }

    @Override
    public Subject findSubjectById(Long id) {
        return subjectRepository.findById(id).orElseThrow(()->new SubjectNotFoundException(id.toString()));
    }


    @Override
    public Subject add(Subject subject) {
        return subjectRepository.saveAndFlush(subject);
    }

    @Override
    public Subject update(Subject subject, Long id) {
        Subject subjectFromDB = subjectRepository.findById(id).orElseThrow(SubjectNotFoundException::new);
        subjectFromDB.setName(subject.getName());
        subjectFromDB.setQuestions(subject.getQuestions());
        return subjectRepository.saveAndFlush(subjectFromDB);
    }

    @Override
    public Subject updateName(String subjectName, Long id) {
        Subject subjectFromDb = subjectRepository.findById(id).orElseThrow(SubjectNotFoundException::new);
        subjectFromDb.setName(subjectName);
        return subjectRepository.saveAndFlush(subjectFromDb);
    }
}
