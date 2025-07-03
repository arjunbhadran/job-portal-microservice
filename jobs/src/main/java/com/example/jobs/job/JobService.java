package com.example.jobs.job;

import com.example.jobs.job.dto.JobWithCompanyAndReviewDTO;

import java.util.List;

public interface JobService {
    List<JobWithCompanyAndReviewDTO> getAlljobs();
    JobWithCompanyAndReviewDTO getJobById(Long id);
    void createJob(Job job);
    String deleteJob(Long id);
    Job updateJob(Job job, Long id);
}
