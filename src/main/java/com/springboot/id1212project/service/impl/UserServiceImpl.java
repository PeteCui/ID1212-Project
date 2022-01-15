package com.springboot.id1212project.service.impl;

import com.springboot.id1212project.exception.ResourceNotFoundException;
import com.springboot.id1212project.model.User;
import com.springboot.id1212project.repository.UserRepository;
import com.springboot.id1212project.service.UserService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.security.*;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    //We do not have to add autowired annotation to this constructor whenever springboot finds spring bean it has only
    //one constructor then springboot will automatically autowired this dependency
    public UserServiceImpl(UserRepository userRepository){
        super();
        this.userRepository = userRepository;
    }

    @Override
    public String saveUser(User user) {
        try{
            MessageDigest md = MessageDigest.getInstance("MD5") ;
            md.update(user.getPassword().getBytes());
            byte[] byteDigest = md.digest() ;
            int i ;
            StringBuffer buf = new StringBuffer("") ;
            for(int offset = 0 ; offset<byteDigest.length ; offset++){
                i = byteDigest[offset] ;
                if(i < 0)
                    i += 256 ;
                if(i < 16)
                    buf.append("0") ;
                buf.append(Integer.toHexString(i)) ;
            }
            String md5Password = buf.toString();
            user.setPassword(md5Password);

            User newUser = userRepository.save(user);
            if (newUser != null){
                return "ok";
            }else{
                return "no";
            }
        }catch (Exception e){
            System.out.println("User Duplicate!");
            return "no";
        }
    }

    @Override
    public List<User> getAllUser() {

        return userRepository.findAll();
    }

    @Override
    public User getUserById(long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        }else{
            throw new ResourceNotFoundException("User", "Id", id);
        }
          //approach 2
//        return userRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("User","Id",id));
    }

    @Override
    public User updateUser(User user, long id) {
        //check whether user with given id exists in DB or not
        User existingUser = userRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("User","Id",id));
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        //save existing user to DB
        userRepository.save(existingUser);
        return existingUser;
    }

    @Override
    public void deleteUser(long id) {
        //check whether a user exist in a DB or not
        userRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("User","Id",id));

        userRepository.deleteById(id);
    }

    @Override
    public boolean loginUser(String email, String password) {
        //check whether user with given email and password exists in DB or not
        if (userRepository.validateUser(email ,password) == 1){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public User getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()){
            return user.get();
        }else{
            throw new ResourceNotFoundException("User", "Id", email);
        }
    }
}
