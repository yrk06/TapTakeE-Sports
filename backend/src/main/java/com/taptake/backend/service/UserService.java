package com.taptake.backend.service;

import com.taptake.backend.model.User;
import com.taptake.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User save(User user){
        //criptografia de senha
        return userRepository.save(user);
    }

//    public boolean existsByEmail(String email){
//        return userRepository.existByEmail(email);
//    }
    public List<User> findAll(){
        return userRepository.findAll();
    }

}
