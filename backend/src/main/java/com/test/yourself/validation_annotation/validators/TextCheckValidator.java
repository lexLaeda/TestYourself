package com.test.yourself.validation_annotation.validators;

import com.test.yourself.validation_annotation.TextCheck;
import com.test.yourself.validation_annotation.validators.text_processor.Label;
import com.test.yourself.validation_annotation.validators.text_processor.TextAnalyzer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

@Service
public class TextCheckValidator implements ConstraintValidator<TextCheck,String> {

    private List<TextAnalyzer> textAnalyzers;

    @Autowired
    public TextCheckValidator(List<TextAnalyzer> textAnalyzers) {
        this.textAnalyzers = textAnalyzers;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Label label = getLabel(value);
        if (label.equals(Label.OK)){
            return true;
        }
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(value + getDescription(label)).addConstraintViolation();
        return false;
    }

    private String getDescription(Label label) {
        switch (label){
            case BAD_WORD: return " is illegal word";
        }
        return "";
    }

    private Label getLabel(String value){
        for (TextAnalyzer textAnalyzer : textAnalyzers){
            Label label = textAnalyzer.processText(value);
            if (!label.equals(Label.OK)){
               return label;
            }
        }
        return Label.OK;
    }
}
