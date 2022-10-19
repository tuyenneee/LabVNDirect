package edu.hanoi.spring.dao;

import edu.hanoi.spring.model.Group;

import java.util.List;

public interface GroupDAO {
    public List<Group> list();
    public String insert(Group group);
    public Group get(String id);
    public void delete(int id);
    public void update(Group group);
}
