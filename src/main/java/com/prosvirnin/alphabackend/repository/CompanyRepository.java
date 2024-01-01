package com.prosvirnin.alphabackend.repository;

import com.prosvirnin.alphabackend.model.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findByName(String name);

    
    boolean existsByName(String name);
    Company findByWorkers_Id(Long userId);
}
