package com.example.jobs.job.clients;

import com.example.jobs.job.external.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "company") //service name should be provided correctly
public interface CompanyClient {
    @GetMapping("api/public/companies/{id}")
    Company getCompany(@PathVariable("id") Long id);

}
