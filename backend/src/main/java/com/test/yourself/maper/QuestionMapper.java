package com.test.yourself.maper;

import com.test.yourself.dto.QuestionDTO;
import com.test.yourself.model.Question;
import com.test.yourself.model.Subject;
import com.test.yourself.model.enums.QuestionMode;
import com.test.yourself.service.subject.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuestionMapper implements Mapper<Question, QuestionDTO> {
    private SubjectService subjectService;

    @Autowired
    public QuestionMapper(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    /**
     * @param question
     * @return
     */
    @Override
    public QuestionDTO toDTO(Question question) {
        QuestionDTO questionDTO = QuestionDTO.builder()
                .id(question.getId())
                .name(question.getName())
                .subjectId(question.getSubject().getId())
                .description(question.getDescription())

                .build();
        return questionDTO;
    }

    /**
     * @param questionDTO
     * @return
     */
    @Override
    public Question fromDTO(QuestionDTO questionDTO) {
        Long subjectId = questionDTO.getSubjectId();
        Subject subject = subjectService.findSubjectById(subjectId);
        QuestionMode mode = getQuestionModeByName(questionDTO.getMode());
        Question question = Question.builder()
                .id(questionDTO.getId())
                .subject(subject)
                .name(questionDTO.getName())
                .build();
        return question;
    }

    private QuestionMode getQuestionModeByName(String modeName) {
        if (modeName.equals(QuestionMode.MULTI.name())) {
            return QuestionMode.MULTI;
        } else {
            return QuestionMode.SINGLE;
        }
    }
}
