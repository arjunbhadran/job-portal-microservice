package com.example.company.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    CompanyRepository companyRepository;

    @Override
    public List<Company> getAllCompanies(){
        return companyRepository.findAll();
    }

    @Override
    public Company getCompanyById(Long id){
        Optional<Company> company=companyRepository.findById(id);
        if(company.isPresent()){
            return company.get();
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void createCompany(Company company){
        companyRepository.save(company);
    }

    @Override
    public String deleteCompany(Long id){
        Optional<Company> company=companyRepository.findById(id);
        if(company.isPresent()){
            companyRepository.deleteById(id);
            return "Company with name:"+ company.get().getCompanyName()+" deleted successfully";
        }
        return "Company with id: "+id+" not found";
    }

    @Override
    public Company updateCompany(Company companyNew, Long id){
        Optional<Company> company=companyRepository.findById(id);
        if(company.isPresent()){
            Company companyEx=company.get();
            companyEx.setCompanyName(companyNew.getCompanyName());
            companyEx.setCompanyDescription(companyNew.getCompanyDescription());
            companyRepository.save(companyEx);
            return companyEx;
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
