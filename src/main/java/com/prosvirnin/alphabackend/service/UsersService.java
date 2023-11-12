package com.prosvirnin.alphabackend.service;

import com.prosvirnin.alphabackend.model.user.LoginRequest;
import com.prosvirnin.alphabackend.model.user.User;
import com.prosvirnin.alphabackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UsersService {

    private final UserRepository userRepository;

    @Autowired
    public UsersService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void save(User user){

    }

    @Transactional
    public void save(LoginRequest loginRequest){
        User user = new User();
        user.setEmail(loginRequest.email());
        user.setPassword(loginRequest.password());
        user.setFullName(loginRequest.fullName());
        userRepository.save(user);
    }

    public User findByUsername(String username){
        return userRepository.findByFullName(username);
    }

    public User findById(Long id){
        return userRepository.findById(id).get();
    }

    @Transactional
    public boolean edit(Long id, User user){
        User toChange = findById(id);
        if(toChange == null)
            return false;
        if(user.getFullName() != null)
            toChange.setFullName(user.getFullName());
        if(user.getAddress() != null)
            toChange.setAddress(user.getAddress());
        if(user.getIncome() != null)
            toChange.setIncome(user.getIncome());
        if(user.getDateOfBirth() != null)
            toChange.setDateOfBirth(user.getDateOfBirth());
        if(user.getSex() != null)
            toChange.setSex(user.getSex());
        if(user.getEducationLevel() != null)
            toChange.setEducationLevel(user.getEducationLevel());
        if(user.getEmail() != null)
            toChange.setEmail(user.getEmail());
        userRepository.save(toChange);
        return true;
    }
}
