package com.example.company.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping("/companies")
    public ResponseEntity<List<Company>> getAllCompanies(){
        return ResponseEntity.ok(companyService.getAllCompanies());
    }

    @PostMapping("/companies")
    public void addCompany(@RequestBody Company company){
        companyService.createCompany(company);
    }

    @GetMapping("/companies/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id){
        try{
            return ResponseEntity.ok(companyService.getCompanyById(id));
        }catch(ResponseStatusException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/companies/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id){
        return ResponseEntity.ok(companyService.deleteCompany(id));
    }

    @PutMapping("/companies/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable Long id, @RequestBody Company company){
        return ResponseEntity.ok(companyService.updateCompany(company,id));
    }
}
