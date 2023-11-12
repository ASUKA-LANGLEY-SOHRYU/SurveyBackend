package com.prosvirnin.alphabackend.service;

import com.prosvirnin.alphabackend.model.company.Company;
import com.prosvirnin.alphabackend.model.company.CompanyRequest;
import com.prosvirnin.alphabackend.model.user.User;
import com.prosvirnin.alphabackend.repository.CompanyRepository;
import com.prosvirnin.alphabackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        if(!companyRepository.existsByName(company.getName()))
            return false;
        Company newCompany = new Company();
        newCompany.setName(company.getName());
        companyRepository.save(newCompany);
        return true;
    }
    @Transactional
    public boolean addWorker(Long companyId, Long userId){
        if(userRepository.existsById(userId) && companyRepository.existsById(companyId)){
            Company company = findById(companyId);
            User user = userRepository.getReferenceById(userId);
            company.addWorker(user);
            //user.setCompany(company);
            companyRepository.save(company);
            return true;
        }
        return false;
    }
}
