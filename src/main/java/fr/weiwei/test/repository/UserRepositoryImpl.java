package fr.weiwei.test.repository;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import fr.weiwei.test.jpa.User;
 

@Repository("userRepository")
public class UserRepositoryImpl extends AbstractDao<Integer, User> implements UserRepository{
     
    private static final AtomicLong counter = new AtomicLong();
     
    private static List<User> users;
     
    @SuppressWarnings("unchecked")
	public List<User> findAllUsers() {
        users = getEntityManager().createQuery("SELECT u FROM User u ORDER BY u.firstName ASC").getResultList();
        return users;
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
    	getEntityManager().persist(user);
    }
 
    public void updateUser(User user) {
        getEntityManager().merge(user);
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
