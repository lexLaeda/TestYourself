package com.test.yourself.validation_annotation.validators.text_processor;

import java.util.List;

public abstract class AbstractTextAnalyzer implements TextAnalyzer{
    protected abstract List<String> getKeyWords();
    protected abstract Label alertMessage();
    @Override
    public Label processText(String text) {
        boolean contains = getKeyWords().stream()
                .anyMatch(word -> word.contains(text.toLowerCase()));
        if (contains){
            return alertMessage();
        }
        return Label.OK;
    }
}
