package fr.weiwei.test.service;

import java.util.List;

import fr.weiwei.test.jpa.User;

 
public interface UserService {
     
    User findById(long id);
     
    void saveUser(User user);
     
    void updateUser(User user);
     
    List<User> findAllUsers();
    
    boolean isUserExist(User user);
    
    void deleteUserById(Long id);
     
}