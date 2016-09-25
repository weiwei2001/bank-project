package fr.weiwei.test.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import fr.weiwei.test.jpa.User;
 

@Repository("userRepository")
public class UserRepositoryImpl extends AbstractDao<Long, User> implements UserRepository{
     
    private static List<User> users;
     
	public List<User> findAllUsers() {
        users = this.findAll();
        return users;
    }
     
    public User findById(long id) {
        User user = this.getByKey(id);
        return user;
    }
     
    public User findByLogin(String login) {
        for(User user : users){
            if(user.getLogin().equalsIgnoreCase(login)){
                return user;
            }
        }
        return null;
    }
     
    public void saveUser(User user) {
    	this.persist(user);
    }
 
    public void updateUser(User user) {
        this.update(user);
    }
 
    public void deleteUserById(long id) {
    	User user = this.findById(id);
    	if (user != null) {
    		this.delete(user);
    	}
    }
 
    public boolean isUserExist(User user) {
        return findByLogin(user.getLogin())!=null;
    }
     
    public void deleteAllUsers(){
        users.clear();
    }
}
