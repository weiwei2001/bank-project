package fr.weiwei.test.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import fr.weiwei.test.jpa.User;
import fr.weiwei.test.service.UserService;
 
 
/**
 * A converter class used in views to map id's to actual userProfile objects.
 */
@Component
public class RoleToUserConverter implements Converter<Object, User>{
 
    @Autowired
    UserService userService;
 
    /**
     * Gets UserProfile by Id
     * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
     */
    public User convert(Object element) {
        Long id = Long.parseLong((String)element);
        User profile= userService.findById(id);
        System.out.println("Profile : "+profile);
        return profile;
    }
     
}