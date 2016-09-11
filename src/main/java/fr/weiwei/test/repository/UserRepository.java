package fr.weiwei.test.repository;

import java.util.List;

import fr.weiwei.test.jpa.User;
 
 
public interface UserRepository {
     
    User findById(long id);
     
    User findByLogin(String name);
     
    void saveUser(User user);
     
    void updateUser(User user);
     
    void deleteUserById(long id);
 
    List<User> findAllUsers(); 
     
    void deleteAllUsers();
     
    public boolean isUserExist(User user);
     
}