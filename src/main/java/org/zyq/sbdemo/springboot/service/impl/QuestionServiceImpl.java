package org.zyq.sbdemo.springboot.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zyq.sbdemo.springboot.dto.PaginationDTO;
import org.zyq.sbdemo.springboot.dto.QuestionDTO;
import org.zyq.sbdemo.springboot.mapper.QuestionMapper;
import org.zyq.sbdemo.springboot.mapper.UserMapper;
import org.zyq.sbdemo.springboot.model.Question;
import org.zyq.sbdemo.springboot.model.User;
import org.zyq.sbdemo.springboot.service.QuestionService;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public void create(Question question) {
        questionMapper.create(question);
    }

    @Override
    public List<Question> selectAll(Integer page,Integer size) {
        return questionMapper.selectAll(page,size);
    }

    @Override
    public PaginationDTO selectAllQuestionDTO(Integer page,Integer size) {
        Integer setOff=size*(page-1);
        PaginationDTO paginationDTO =new PaginationDTO();
        List<Question> questionList =questionMapper.selectAll(setOff,size);
        List<QuestionDTO> questionDTOList=new ArrayList<QuestionDTO>();
        for(Question question : questionList){
            System.out.println(question.getViewCount());
        User user=userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestionDTOList(questionDTOList);
        Integer totalCount=selectAllRecord();
        paginationDTO.setpagination(page,size,totalCount);
        return paginationDTO;
    }

    @Override
    public Integer selectAllRecord() {
        return questionMapper.selectAllRecord();
    }
}
