package edu.hanoi.jazz.dao;

import edu.hanoi.jazz.dao.model.Group;

import java.util.List;

public interface GroupDAO {
    public void insert(Group group);
    public List<Group> list(String pattern);
    public void delete(int id);
    public void update(Group group);
    public Group get(int id);
}
