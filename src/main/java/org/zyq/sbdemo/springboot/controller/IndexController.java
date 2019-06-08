package org.zyq.sbdemo.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.zyq.sbdemo.springboot.dto.QuestionDTO;
import org.zyq.sbdemo.springboot.model.User;
import org.zyq.sbdemo.springboot.service.QuestionService;
import org.zyq.sbdemo.springboot.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private  UserService userService;

    @Autowired
    private QuestionService questionService;
    @GetMapping("/")
    public String hello(HttpServletRequest request,Model model){
        Cookie[] cookies=request.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userService.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;

                }
            }
        }

       List<QuestionDTO> questionDTOList=questionService.selectAllQuestionDTO();
       model.addAttribute("questionDTOList",questionDTOList);


       return "index";


    }


}
