package edu.hanoi.jazz.controller;

import edu.hanoi.jazz.ContextStartEventHandler;
import edu.hanoi.jazz.dao.GroupDAO;
import edu.hanoi.jazz.dao.model.Group;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/group")
public class GroupController {
    private final static Logger LOGGER = Logger.getLogger(ContextStartEventHandler.class);

    @Autowired
    GroupDAO groupDAO;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addForm() {
        ModelAndView mv = new ModelAndView("group.form", "command", new Group());
        return mv;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView addNew(@Valid @ModelAttribute("command") Group group, BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("group.form", "command", group);
            mv.addObject("error", result);
            return mv;
        }
        LOGGER.info("Add new group -----> " + group);
        if (group.getId() > 0) {
            groupDAO.update(group);
        } else {
            groupDAO.insert(group);
        }
        return new ModelAndView("redirect:/group/listGroup");
    }

    @RequestMapping(value = "/listGroup")
    public ModelAndView list(@RequestParam(value = "q", required = false) String pattern) {
        ModelAndView mv = new ModelAndView("group.list");
        mv.addObject("groups", groupDAO.list(pattern));
        return mv;
    }

    @RequestMapping(value = "/delete/{id}")
    public ModelAndView delete(@PathVariable Integer id) {
        if (id == null) return new ModelAndView("redirect:/group/listGroup");
        groupDAO.delete(id);
        return new ModelAndView("redirect:/group/listGroup");
    }

    @RequestMapping(value = "update")
    public ModelAndView updateForm(@RequestParam(value = "id", required = true) Integer id){
        Group group = groupDAO.get(id);
        if(group == null) return new ModelAndView("redirect:/group/listGroup");
        return new ModelAndView("group.form", "command", group);
    }
}
