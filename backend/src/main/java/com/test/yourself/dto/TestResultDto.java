package com.test.yourself.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TestResultDto extends AbstractDto {


    private Long testId;

    private Integer correctAmount;

    private Integer percent;

    private LocalDateTime start;

    private LocalDateTime end;

    private List<Integer> listOfCorrectId;

    private AnswerSheetDto answerSheetDto;


}

