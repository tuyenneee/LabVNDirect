package edu.hanoi.service;

import edu.hanoi.service.model.User;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class HNServicePermissionEvaluator implements PermissionEvaluator {
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        System.out.println("-----> "+targetDomainObject+": "+permission);
        if(targetDomainObject instanceof User){
            User user = (User) targetDomainObject;
            return user.getAge() > 50;
        }
        return true;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        System.out.println("-----> "+targetType+": "+permission);
        return true;
    }
}
