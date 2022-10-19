package edu.java.spring.dao.impl;

import edu.java.spring.controller.Student;
import edu.java.spring.dao.StudentDAO;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class StudentDAOImpl implements StudentDAO, DisposableBean {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @PostConstruct
    private void createTableIfNotExist() throws SQLException {
        DatabaseMetaData dbmd = dataSource.getConnection().getMetaData();
        ResultSet rs = dbmd.getTables(null, null, "STUDENT", null);
        if (rs.next()) {
            System.out.println("Table " + rs.getString("TABLE_NAME") + " already exists!");
            return;
        }
        jdbcTemplate.execute("create table STUDENT(" +
                "id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1), " +
                "name varchar(1000), " +
                "age INTEGER)");
    }

    @Override
    public void insert(Student student) {
        jdbcTemplate.update("insert into STUDENT (name, age) values (?, ?)", student.getName(), student.getAge());
        System.out.println("Create record name: " + student.getName());
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.execute("delete from STUDENT where id = " + id);
    }

    private final static class StudentRowMapper implements RowMapper<Student>{
        @Override
        public Student mapRow(ResultSet rs, int i) throws SQLException {
            try {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setAge(rs.getInt("age"));
                return student;
            } catch (SQLException e){
                return null;
            }
        }
    }

    @Override
    public Student get(int id) {
        String sql = "select * from STUDENT where id = " + id;
        Student student = jdbcTemplate.queryForObject(sql, new StudentRowMapper());
        return student;
    }

    @Override
    public List<Student> list() {
        String sql = "select * from STUDENT";
        List<Student> list = jdbcTemplate.query(sql, new StudentRowMapper());
        return list;
    }

    @Override
    public List<Student> search(String name) {
        String sql = "select * from STUDENT where name like '%"+name+"%'";
        List<Student> listSearch = jdbcTemplate.query(sql, new StudentRowMapper());
        return listSearch;
    }

    @Override
    public void update(Student student) {
        jdbcTemplate.update("update STUDENT set name = ? where id = ?", student.getName(), student.getId());
    }

    @Override
    public void destroy() throws Exception {
        DriverManager.getConnection("jdbc:derby:C:/Java/sampledb2;shutdown=true");
    }
}
