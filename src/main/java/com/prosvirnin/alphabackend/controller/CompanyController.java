package com.prosvirnin.alphabackend.controller;

import com.prosvirnin.alphabackend.model.company.Company;
import com.prosvirnin.alphabackend.model.company.CompanyRequest;
import com.prosvirnin.alphabackend.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/{id}")
    public Company getCompany(@PathVariable("id") Long id){
        return companyService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> createCompany(@RequestBody CompanyRequest company){
        if(companyService.save(company))
            return ResponseEntity.ok(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
    }

    @GetMapping("/{company_id}/addUserById/{user_id}")
    public ResponseEntity<?> addUser(@PathVariable("company_id") Long companyId, @PathVariable("user_id") Long user_id){
        if(companyService.addWorker(companyId, user_id))
            return  ResponseEntity.ok(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
    }
}
