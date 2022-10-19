package edu.hanoi.jazz;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContextStartEventHandler implements ApplicationListener {
    private final static Logger LOGGER = Logger.getLogger(ContextStartEventHandler.class);
    @Autowired
    private DataSource dataSource;

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        LOGGER.info("Context start application "+dataSource);
        try {
            createTable("HN_USER","create table HN_USER(username VARCHAR(100) primary key," +
                    "password varchar(100)," +
                    "email varchar(100)," +
                    "age Integer," +
                    "groupId bigint," +
                    "CONSTRAINT GROUP_FK FOREIGN KEY (groupId) REFERENCES HN_GROUP(id))");
        } catch (Exception e){
            LOGGER.error(e, e);
        }
    }

    private void createTable(String name, String script) throws SQLException {
        DatabaseMetaData dbmt = dataSource.getConnection().getMetaData();
        ResultSet rs = dbmt.getTables(null, null, name, null);
        if(rs.next()){
            LOGGER.info("Table "+rs.getString("TABLE_NAME")+" already exists!");
            return;
        }
        dataSource.getConnection().createStatement().executeUpdate(script);
    }
}
