package com.example.jobs.job.mapper;

import com.example.jobs.job.Job;
import com.example.jobs.job.dto.JobWithCompanyAndReviewDTO;
import com.example.jobs.job.external.Company;
import com.example.jobs.job.external.Review;

import java.util.List;

public class JobMapper {
    public JobWithCompanyAndReviewDTO mapToJobWithCompanyDto(Job job, Company company, List<Review> reviews){
        JobWithCompanyAndReviewDTO jobWithCompanyDTO = new JobWithCompanyAndReviewDTO();
        jobWithCompanyDTO.setId(job.getId());
        jobWithCompanyDTO.setTitle(job.getTitle());
        jobWithCompanyDTO.setDescription(job.getDescription());
        jobWithCompanyDTO.setLocation(job.getLocation());
        jobWithCompanyDTO.setMaxSalary(job.getMaxSalary());
        jobWithCompanyDTO.setMinSalary(job.getMinSalary());
        jobWithCompanyDTO.setCompany(company);
        jobWithCompanyDTO.setReviews(reviews);

        return jobWithCompanyDTO;
    }
}
