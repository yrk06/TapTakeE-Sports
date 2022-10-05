package com.taptake.backend.service;

import com.taptake.backend.model.User;
import com.taptake.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User save(User user){
        //criptografia de senha
        return userRepository.save(user);
    }

    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }
    public List<User> findAll(){
        return userRepository.findAll();
    }
    public void deleteOne(UUID id){
        userRepository.deleteById(id);
    }
    public User update(User user){
        return userRepository.save(user);
    }
}
