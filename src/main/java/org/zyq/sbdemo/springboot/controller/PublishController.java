package org.zyq.sbdemo.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.zyq.sbdemo.springboot.model.Question;
import org.zyq.sbdemo.springboot.model.User;
import org.zyq.sbdemo.springboot.service.QuestionService;
import org.zyq.sbdemo.springboot.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

   @Autowired
   private QuestionService questionService;
   @Autowired
   private UserService userService;

    @GetMapping("publish")
    public String publish(){
        return "publish";
    }
    @PostMapping("publish")
    public  String doPublish(Question question, HttpServletRequest request, Model model) {
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        if(question.getTitle()==null){
            model.addAttribute("error","标题不能为空");

            return "publish";
        }
        if(question.getDescription()==null||question.getDescription()==""){
            model.addAttribute("error","问题补充不能为空");
            return "publish";
        }
        if(question.getTag()==null||question.getTag()==""){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }
        User user=null;
        Cookie[] cookies = request.getCookies();
        if(cookies!=null&&cookies.length!=0){
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                String token = cookie.getValue();
                user = userService.findByToken(token);
                if (user != null) {
                    request.getSession().setAttribute("user", user);
                }
                break;
            }
            }
        }
        if(user==null){
            model.addAttribute("error","用户未登录！");
            return "publish";
        }
            question.setCreator(user.getId());
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());

            questionService.create(question);
            return "redirect:/";
        }

    }

