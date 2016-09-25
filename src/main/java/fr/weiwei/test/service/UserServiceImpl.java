package fr.weiwei.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.weiwei.test.jpa.User;
import fr.weiwei.test.repository.UserRepository;
 
 
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{
 
    @Autowired
    private UserRepository userRepository;
 
    public User findById(long id) {
        return userRepository.findById(id);
    }
 
    public void saveUser(User user) {
    	userRepository.saveUser(user);
    }
 
    /*
     * Since the method is running with Transaction, No need to call hibernate update explicitly.
     * Just fetch the entity from db and update it with proper values within transaction.
     * It will be updated in db once transaction ends. 
     */
    public void updateUser(User user) {
    	userRepository.updateUser(user);
    }
 
    public List<User> findAllUsers() {
        return userRepository.findAllUsers();
    }

	@Override
	public boolean isUserExist(User user) {
		return userRepository.isUserExist(user);
	}

	@Override
	public void deleteUserById(Long id) {
		userRepository.deleteUserById(id);
	}
}