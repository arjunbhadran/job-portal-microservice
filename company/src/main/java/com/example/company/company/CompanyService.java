package com.example.company.company;


import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();
    Company getCompanyById(Long companyId);
    void createCompany(Company company);
    Company updateCompany(Company companyNew, Long id);
    String deleteCompany(Long id);
}
