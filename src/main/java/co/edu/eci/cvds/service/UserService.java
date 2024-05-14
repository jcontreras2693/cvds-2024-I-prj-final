package co.edu.eci.cvds.service;

import co.edu.eci.cvds.exceptions.UserException;
import co.edu.eci.cvds.model.User;
import co.edu.eci.cvds.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;


    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public User getUser(int id) throws UserException {
        Optional<User> result = userRepository.findById(id);
        if(result.isEmpty()){
            throw new UserException(UserException.userNotFound);
        }
        return result.get();
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void updateUser(User user) throws UserException {
        User update = getUser(user.getUserId());
        update.setUserName(user.getUserName());
        update.setIdentificationNumber(user.getIdentificationNumber());
        update.setTelephoneNumber(user.getTelephoneNumber());
        update.setEmail(user.getEmail());
        update.setPassword(user.getPassword());
        userRepository.save(update);
        //userRepository.updateUser(user.getUserId(), user.getUserName(), user.getIdentificationNumber(), user.getTelephoneNumber(), user.getEmail(), user.getPassword());
    }

    public void deleteUser(User user){
        userRepository.delete(user);
    }

    public void deleteUser(int id) throws UserException{
        User user = getUser(id);
        deleteUser(user);
    }
}