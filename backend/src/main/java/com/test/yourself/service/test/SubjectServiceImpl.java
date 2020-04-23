package com.test.yourself.service.test;

import com.test.yourself.exception.SubjectNotFoundException;
import com.test.yourself.exception.SubjectServiceValidationException;
import com.test.yourself.model.testsystem.subject.Subject;
import com.test.yourself.repository.SubjectRepository;
import com.test.yourself.util.ReflectionUpdate;
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
        Subject byName = subjectRepository.findByName(name);
        if (byName == null){
            throw new SubjectNotFoundException(name);
        }
        return byName;
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
    public Subject findSubjectById(Long id) {
        return subjectRepository.findById(id).orElseThrow(()->new SubjectNotFoundException(id.toString()));
    }


    @Override
    public Subject add(Subject subject) {
        Subject subFromBD = subjectRepository.findByName(subject.getName());
        if (subFromBD != null){
            throw new SubjectServiceValidationException("Subject with " + subject.getName() + "already exist");
        } else {
            return subjectRepository.saveAndFlush(subject);
        }
    }

    @Override
    public Subject update(Subject subject, Long id) {
        Subject subjectFromDB = findSubjectById(id);
        Subject updated = ReflectionUpdate.updateObject(subject, subjectFromDB);
        return subjectRepository.saveAndFlush(updated);
    }

    @Override
    public Subject updateName(String subjectName, Long id) {
        Subject subjectFromDb = subjectRepository.findById(id).orElseThrow(SubjectNotFoundException::new);
        subjectFromDb.setName(subjectName);
        return subjectRepository.saveAndFlush(subjectFromDb);
    }

    @Override
    public void deleteById(Long id) {
        subjectRepository.deleteById(id);
    }

    @Override
    public void delete(Subject subject) {
        subjectRepository.delete(subject);
    }


}
