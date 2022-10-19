package edu.hanoi.jazz.dao;

import edu.hanoi.jazz.dao.model.User;

import java.util.List;

public interface UserDAO {
    public void insert(User user);
    public List<User> list(Integer group);
    public User getDetail(String username);
    public List<User> listAll();
    public void delete(String name);
    public int averageAge();
    public List<User> page(int page);
    public List<User> listUserByNativeSQL();
    public void addBatch();
}
