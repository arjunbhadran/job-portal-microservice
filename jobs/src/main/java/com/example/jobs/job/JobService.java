package com.example.jobs.job;

import com.example.jobs.job.dto.JobWithCompanyDTO;

import java.util.List;

public interface JobService {
    List<JobWithCompanyDTO> getAlljobs();
    Job getJobById(Long id);
    void createJob(Job job);
    String deleteJob(Long id);
    Job updateJob(Job job, Long id);
}
