package edu.java.spring.dao;

import edu.java.spring.controller.Student;

import java.util.List;

public interface StudentDAO {
    public void insert(Student student);
    public List<Student> list();
    public void delete(int id);
    public Student get(int id);
    public void update(Student student);
    public List<Student> search(String name);
}
