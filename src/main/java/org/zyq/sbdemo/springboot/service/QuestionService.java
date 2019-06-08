package org.zyq.sbdemo.springboot.service;

import org.zyq.sbdemo.springboot.dto.QuestionDTO;
import org.zyq.sbdemo.springboot.model.Question;

import java.util.List;

public interface QuestionService {

    void create(Question question);

    List<Question> selectAll();

    List<QuestionDTO> selectAllQuestionDTO();
}
