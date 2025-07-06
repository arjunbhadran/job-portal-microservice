package com.example.company.company;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    /* Static since it ensures only one instance is used per class
    * and saves memory by avodiing duplicate logger creation
    *
    * final: Prevents the logger reference from being reassigned.
    * This guarantees that the logger remains constant throughout the
    * class’s lifecycle, reducing the risk of bugs or accidental changes to the logger configuration*/
    Logger logger = LoggerFactory.getLogger(CompanyServiceImpl.class);
    @Autowired
    CompanyRepository companyRepository;


    @Override
    public List<Company> getAllCompanies(){
        return companyRepository.findAll();
    }

//    @Override
//    public Company getCompanyById(Long id){
//        Optional<Company> company=companyRepository.findById(id);
//        if(company.isPresent()){
//            logger.info("Company is present");
//            return company.get();
//        }else{
//            logger.error("Company not found");
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        }
//    }

    //using direct SQL query
    @Override
    public Company getCompanyById(Long id){
        Company company=companyRepository.findCompanyByIdNative(id);
        if(company!=null){
            logger.info("Company is present");
        }else{
            logger.error("Company not found");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return company;
    }

//    @Override
//    public void createCompany(Company company){
//        companyRepository.save(company);
//    }

    //using SQL query
    @Override
    public void createCompany(Company company){
        companyRepository.insertCompany(company.getCompanyName(),company.getCompanyDescription());
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
