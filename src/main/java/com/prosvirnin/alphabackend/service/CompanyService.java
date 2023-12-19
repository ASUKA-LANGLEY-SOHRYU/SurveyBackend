package com.prosvirnin.alphabackend.service;

import com.prosvirnin.alphabackend.model.company.Company;
import com.prosvirnin.alphabackend.model.company.CompanyRequest;
import com.prosvirnin.alphabackend.model.user.Role;
import com.prosvirnin.alphabackend.model.user.User;
import com.prosvirnin.alphabackend.repository.CompanyRepository;
import com.prosvirnin.alphabackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CompanyService {

    private final CompanyRepository companyRepository;

    private final UserRepository userRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository, UserRepository userRepository) {
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
    }

    public Company findById(Long id){
        return companyRepository.findById(id).get();
    }

    public Company findByName(String name){
        return companyRepository.findByName(name);
    }

    @Transactional
    public boolean save(CompanyRequest company){
        if(companyRepository.existsByName(company.getName()))
            return false;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userRequest = (User) authentication.getPrincipal();
        Company newCompany = new Company();
        newCompany.setName(company.getName());

        userRequest.setRole(Role.CompanyOwner);
        newCompany.addWorker(userRequest);
        userRequest.setCompany(newCompany);

        companyRepository.save(newCompany);
        userRepository.save(userRequest);
        return true;
    }
    @Transactional
    public boolean addWorker(Long userId){
        if(userRepository.existsById(userId)){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User userRequest = (User) authentication.getPrincipal();

            Company company = userRequest.getCompany();
            User user = userRepository.getReferenceById(userId);

            company.addWorker(user);
            user.setCompany(company);
            user.setRole(Role.Interviewer);

            companyRepository.save(company);
            userRepository.save(user);
            return true;
        }
        return false;
    }
}
