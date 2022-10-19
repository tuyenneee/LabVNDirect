package edu.hanoi.jazz.dao.model;

import javax.persistence.*;

@Entity
@Table(name = "HN_USER", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class User implements Comparable<User>{
    @Id
    @GeneratedValue
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "age")
    private int age;
    @Column(name = "groupId")
    private int groupId;
    @ManyToOne(cascade = CascadeType.ALL)
    private Group group;

    public User() {
    }

    public User(String password, String email, int age, Group group) {
        this.password = password;
        this.email = email;
        this.age = age;
        this.group = group;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Override
    public int compareTo(User other) {
        return age - other.age;
    }
}
