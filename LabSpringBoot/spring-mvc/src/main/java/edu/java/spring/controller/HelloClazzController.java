package edu.java.spring.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


@Controller
@RequestMapping("/hello")
public class HelloClazzController {
    @RequestMapping(method = GET)
    public ModelAndView printMessage(@RequestParam(value = "data", required = false) String message){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        mv.addObject("message", message);
        return mv;
    }
    @RequestMapping(value = "welcome", method = GET)
    public ModelAndView welcome(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("../clazz");
        mv.addObject("name", "Khai ngu");
        return mv;
    }
    @RequestMapping(value = "site", method = GET)
    public String redirect(){
        return "redirect:http://moom.vn";
    }
    @RequestMapping(value = "data", method = GET, produces = MediaType.TEXT_PLAIN_VALUE)
    public @ResponseBody String raw(){
        return "xin chao 500 anh em!";
    }
}
