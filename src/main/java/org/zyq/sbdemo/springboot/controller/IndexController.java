package org.zyq.sbdemo.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zyq.sbdemo.springboot.dto.PaginationDTO;
import org.zyq.sbdemo.springboot.model.User;
import org.zyq.sbdemo.springboot.service.QuestionService;
import org.zyq.sbdemo.springboot.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    private  UserService userService;

    @Autowired
    private QuestionService questionService;
    @GetMapping("/")
    public String hello(HttpServletRequest request, Model model,
                        @RequestParam(value = "page",defaultValue ="1") Integer page,
                        @RequestParam(value = "size",defaultValue ="5") Integer size){
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

       PaginationDTO paginationDTO =questionService.selectAllQuestionDTO(page,size);

       model.addAttribute("paginationDTO",paginationDTO);



       return "index";


    }


}
