package edu.hanoi.spring.controller;

import edu.hanoi.spring.dao.GroupDAO;
import edu.hanoi.spring.model.Group;
import edu.hanoi.spring.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GroupController {
    @Autowired
    private GroupDAO groupDAO;

    @RequestMapping(value = "/groups")
    public List<Group> list(){
        return groupDAO.list();
    }

    @MessageMapping("/group")
    @SendTo("/topic/chat")
    public Message add(Group group, Message name) {
        String status = null;
        System.out.println(name.getContent());
        if (name.getContent() == null) {

            status = groupDAO.insert(group);
            System.out.println("ID: " + group.getId() + " - Name: " + group.getName());
        } else {
            groupDAO.update(group);
            status = "Update user "+group.getName() +" successful!";
        }
        return new Message(status);
    }

    @MessageMapping("/group/delete/{id}")
    @SendTo("/topic/chat")
    public Message deleteGroup(@DestinationVariable String Id){
        System.out.println("=====================");
        int id = Integer.parseInt(Id);
        System.out.println("ID: " + id);
        groupDAO.delete(id);
        return new Message("Delete successful!");
    }

    @RequestMapping(value = "/getGroup/{id}",method = RequestMethod.GET)
    public Group getGroup(@PathVariable Integer id) {
        return groupDAO.get(String.valueOf(id));
    }
}
