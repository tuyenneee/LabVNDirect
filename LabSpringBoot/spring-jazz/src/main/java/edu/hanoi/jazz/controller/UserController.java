package edu.hanoi.jazz.controller;

import edu.hanoi.jazz.ContextStartEventHandler;
import edu.hanoi.jazz.dao.GroupDAO;
import edu.hanoi.jazz.dao.UserDAO;
import edu.hanoi.jazz.dao.model.Group;
import edu.hanoi.jazz.dao.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "user")
public class UserController {
    private final static Logger LOGGER = Logger.getLogger(ContextStartEventHandler.class);

    @Autowired
    GroupDAO groupDAO;
    @Autowired
    UserDAO userDAO;

    @RequestMapping(value = "/add")
    public ModelAndView addForm(){
        ModelAndView mv = new ModelAndView("user.form", "command", new User());
        mv.addObject("groups", toGroupMap(groupDAO.list(null)));
        return mv;
    }

    private Map toGroupMap(List<Group> groups){
        Map<Integer, String> map = new HashMap<>();
        groups.forEach(group -> map.put(group.getId(), group.getName()));
        return map;
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ModelAndView addNew(@Valid @ModelAttribute("command") User user, BindingResult result){
        if(result.hasErrors()){
            ModelAndView mv = new ModelAndView("user.form", "command", new User());
            mv.addObject("groups", toGroupMap(groupDAO.list(null)));
            mv.addObject("error", result);
            return mv;
        }
        LOGGER.info("Add new user -----> "+user);
        userDAO.insert(user);
        return new ModelAndView("redirect:/user/listUser");
    }

    @RequestMapping("/listUser")
    public ModelAndView listAll(){
        ModelAndView mv = new ModelAndView("user.list");
        mv.addObject("users", userDAO.listUserByNativeSQL());
        mv.addObject("average", userDAO.averageAge());
        return mv;
    }

    @RequestMapping("/listUser/{groupId}")
    public ModelAndView list(@PathVariable Integer groupId){
        ModelAndView mv = new ModelAndView("user.list");
        mv.addObject("users", userDAO.list(groupId));
        return mv;
    }

    @RequestMapping("/detail/{username}")
    public ModelAndView detail(@PathVariable String username){
        ModelAndView mv = new ModelAndView("user.detail");
        mv.addObject("users", userDAO.getDetail(username));
        return mv;
    }

    @RequestMapping(value = "/delete/{name}", method = RequestMethod.GET)
    public String delete(@PathVariable String name){
        userDAO.delete(name);
        return "redirect:/user/listUser";
    }

    @RequestMapping(value = "AddMany")
    public String addRandom(){
        userDAO.addBatch();
        return "redirect:/user/listUser";
    }
}
