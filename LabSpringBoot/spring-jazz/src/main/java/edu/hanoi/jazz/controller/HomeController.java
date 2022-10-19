package edu.hanoi.jazz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @RequestMapping("/")
    ModelAndView home() {
        System.out.printf("abc");
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("message", "Hello Jazz Clazz");
        return mv;
    }

    @RequestMapping("/nguoi-dung")
    ModelAndView forUser() {
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("message", "This is protected page!");
        return mv;
    }

    @RequestMapping("/dang-nhap")
    ModelAndView login(@RequestParam(value = "error", required = false) String error) {
        ModelAndView mv = new ModelAndView("login");
        if (error != null) {
            mv.addObject("error", error);
        }
        return mv;
    }
}
