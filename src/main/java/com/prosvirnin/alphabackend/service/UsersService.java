package com.prosvirnin.alphabackend.service;

import com.prosvirnin.alphabackend.auth.LoginRequest;
import com.prosvirnin.alphabackend.model.user.Role;
import com.prosvirnin.alphabackend.model.user.User;
import com.prosvirnin.alphabackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    public User findByUsername(String username){
        return userRepository.findByFullName(username);
    }

    public User findById(Long id){
        return userRepository.findById(id).get();
    }

    public User findBySecurityContext(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }

    @Transactional
    public boolean edit(User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User toChange = (User) authentication.getPrincipal();
        if(user.getEmail() != null)
            toChange.setEmail(user.getEmail());
        if(user.getFullName() != null)
            toChange.setFullName(user.getFullName());
        if(user.getSex() != null)
            toChange.setSex(user.getSex());
        if(user.getEducationLevel() != null)
            toChange.setEducationLevel(user.getEducationLevel());
        if(user.getIncome() != null)
            toChange.setIncome(user.getIncome());
        if(user.getCity() != null)
            toChange.setCity(user.getCity());
        if (user.getHobbies() != null)
            toChange.setHobbies(user.getHobbies());
        if (user.getRestaurantVisitsPerWeek() != null)
            toChange.setRestaurantVisitsPerWeek(user.getRestaurantVisitsPerWeek());
        if (user.getHabits() != null)
            toChange.setHabits(user.getHabits());
        if (user.getHabits() != null)
            toChange.setHabits(user.getHabits());
        if (user.getIsMakingPurchasesOnline() != null)
            toChange.setIsMakingPurchasesOnline(user.getIsMakingPurchasesOnline());
        userRepository.save(toChange);
        return true;
    }
}
