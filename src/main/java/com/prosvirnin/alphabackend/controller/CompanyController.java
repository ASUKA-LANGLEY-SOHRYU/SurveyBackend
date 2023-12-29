package com.prosvirnin.alphabackend.controller;

import com.prosvirnin.alphabackend.model.company.Company;
import com.prosvirnin.alphabackend.model.company.CompanyRequest;
import com.prosvirnin.alphabackend.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
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

    @GetMapping("/addUserById/{user_id}")
    public ResponseEntity<?> addUser(@PathVariable("user_id") Long user_id){
        System.out.println(user_id);
        if(companyService.addWorker(user_id))
            return  ResponseEntity.ok(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
    }
}
