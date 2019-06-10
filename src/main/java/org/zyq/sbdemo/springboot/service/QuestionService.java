package org.zyq.sbdemo.springboot.service;

import org.zyq.sbdemo.springboot.dto.PaginationDTO;
import org.zyq.sbdemo.springboot.model.Question;

import java.util.List;

public interface QuestionService {

    void create(Question question);

    List<Question> selectAll(Integer page,Integer size);

    PaginationDTO selectAllQuestionDTO(Integer page, Integer size);

    //查询总页码
    Integer selectAllRecord();
}
