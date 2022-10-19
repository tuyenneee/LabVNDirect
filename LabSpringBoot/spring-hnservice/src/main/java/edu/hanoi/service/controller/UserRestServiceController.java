package edu.hanoi.service.controller;

import edu.hanoi.service.dao.GroupDAO;
import edu.hanoi.service.dao.UserDAO;
import edu.hanoi.service.model.Group;
import edu.hanoi.service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class UserRestServiceController {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private GroupDAO groupDAO;

    @RequestMapping(value = "/list/users", method = RequestMethod.GET)
//    @PreAuthorize("hasRole('ADMIN')")
//    @PostFilter("filterObject.username == 'sa'")
    @PreAuthorize("hasAnyRole('USER1')")
    @PostFilter("hasPermission(filterObject, 'read')")
    public List<User> listUser(){
//        if(!request.isUserInRole("ROLE_ADMIN")){
//            throw new RuntimeException("Access denied!");
//        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("\n--------> "+auth.getAuthorities());
        return userDAO.list();
    }

    @RequestMapping(value = "/list/groups")
    public Group[] listGroups(){
        return groupDAO.list().toArray(new Group[0]);
    }

    @RequestMapping(value = "/add/user", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public String addUser(@RequestBody User user){
        return userDAO.insert(user);
    }

    @RequestMapping(value = "/get/user/{name}", method = RequestMethod.GET)
    public User getUser(@PathVariable String name){
        return userDAO.get(name);
    }

    @RequestMapping(value = "/delete/user/{name}", method = RequestMethod.GET)
    public void delete(@PathVariable String name){
        userDAO.delete(name);
    }

    @RequestMapping(value = "/update/user", method = RequestMethod.POST)
    public void updateUser(@RequestBody User user){
        userDAO.update(user);
    }
}
