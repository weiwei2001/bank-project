package fr.weiwei.test.repository;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import fr.weiwei.test.jpa.User;
 
@Service("userService")
public class UserRepositoryImpl implements UserRepository{
     
    private static final AtomicLong counter = new AtomicLong();
     
    private static List<User> users;
    
    @PersistenceContext
    private EntityManager entityManager;
     
    public List<User> findAllUsers() {
    	Query query = entityManager.createQuery("SELECT u from User as u");
        return query.getResultList();
    }
     
    public User findById(long id) {
        for(User user : users){
            if(user.getId() == id){
                return user;
            }
        }
        return null;
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
        user.setId(counter.incrementAndGet());
        users.add(user);
    }
 
    public void updateUser(User user) {
        int index = users.indexOf(user);
        users.set(index, user);
    }
 
    public void deleteUserById(long id) {
         
        for (Iterator<User> iterator = users.iterator(); iterator.hasNext(); ) {
            User user = iterator.next();
            if (user.getId() == id) {
                iterator.remove();
            }
        }
    }
 
    public boolean isUserExist(User user) {
        return findByLogin(user.getLogin())!=null;
    }
     
    public void deleteAllUsers(){
        users.clear();
    }
}
