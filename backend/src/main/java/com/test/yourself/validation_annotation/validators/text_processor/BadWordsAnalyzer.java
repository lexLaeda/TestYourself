package com.test.yourself.validation_annotation.validators.text_processor;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class BadWordsAnalyzer extends AbstractTextAnalyzer {
    @Override
    protected List<String> getKeyWords() {
        return Arrays.asList("хуй","пизда"," еба ","блядь");
    }

    @Override
    protected Label alertMessage() {
        return Label.BAD_WORD;
    }
}
