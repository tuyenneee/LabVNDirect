package edu.hanoi.jazz.service;

import edu.hanoi.jazz.ContextStartEventHandler;
import edu.hanoi.jazz.dao.UserDAO;
import edu.hanoi.jazz.dao.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserAuthProvider implements AuthenticationProvider {
    @Autowired
    private UserDAO userDAO;
    private final static Logger LOGGER = Logger.getLogger(ContextStartEventHandler.class);

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName().toString();
        User user = userDAO.getDetail(username);
        if(user == null) return null;
        LOGGER.info("-----> found "+user+" by "+username);
        if(!user.getPassword().equals(authentication.getCredentials())) return null;
        return successful(username, authentication.getCredentials().toString(), user.getPassword());
    }

    private UsernamePasswordAuthenticationToken successful(String username, String password, String userPassword){
        List<GrantedAuthority> grantedAuthority = new ArrayList<>();
        grantedAuthority.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new UsernamePasswordAuthenticationToken(username, password);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
