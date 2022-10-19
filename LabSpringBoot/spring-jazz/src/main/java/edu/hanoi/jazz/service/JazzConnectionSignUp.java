package edu.hanoi.jazz.service;

import edu.hanoi.jazz.ContextStartEventHandler;
import edu.hanoi.jazz.dao.UserDAO;
import edu.hanoi.jazz.dao.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UserProfile;

public class JazzConnectionSignUp implements ConnectionSignUp {
    @Autowired
    private UserDAO userDAO;
    private final static Logger LOGGER = Logger.getLogger(ContextStartEventHandler.class);

    @Override
    public String execute(final Connection<?> connection) {
        UserProfile userProfile = connection.fetchUserProfile();
        LOGGER.info("----->id: "+userProfile.getId()+" name: "+userProfile.getUsername());
        User user = userDAO.getDetail(userProfile.getEmail());
        if(user != null) return user.getUsername();

        user = new User();
        user.setUsername(userProfile.getEmail());
        user.setPassword("123");
        user.setGroupId(501);
        user.setEmail(userProfile.getEmail());
        userDAO.insert(user);
        return user.getUsername();
    }
}
