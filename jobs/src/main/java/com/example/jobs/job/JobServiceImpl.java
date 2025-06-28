package com.example.jobs.job;

import com.example.jobs.job.dto.JobWithCompanyDTO;
import com.example.jobs.job.external.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {
    @Autowired
    JobRepository jobRepository;

//    @Autowired
//    private CompanyService companyService;

    @Override
    public List<JobWithCompanyDTO> getAlljobs(){
        List<Job> jobs = jobRepository.findAll();
        List<JobWithCompanyDTO> jobWithCompanyDTOS = new ArrayList<>();
//        for(Job job:jobs){
//            JobWithCompanyDTO jobWithCompanyDTO = JobCompanyConcat(job);
//            jobWithCompanyDTOS.add(jobWithCompanyDTO);
//        }
//        //return jobRepository.findAll();
//        return jobWithCompanyDTOS;
        return jobs.stream().map(this::JobCompanyConcat).collect(Collectors.toList());
    }

    private JobWithCompanyDTO JobCompanyConcat(Job job){
        JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
        jobWithCompanyDTO.setJob(job);
        RestTemplate restTemplate = new RestTemplate();
        Company company=restTemplate.getForObject("http://localhost:8081/api/public/companies/"+String.valueOf(job.getCompanyId()),Company.class);
        jobWithCompanyDTO.setCompany(company);
        return jobWithCompanyDTO;
    }

    @Override
    public Job getJobById(Long id) {
        Optional<Job> job=jobRepository.findById(id);
        if(job.isPresent()){
            return job.get();
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @Override
    public void createJob(Job job) {
//        Company company = companyService.getCompanyById(job.getCompany().getCompanyId());
//        if(company!=null){
//            job.setCompany(company);
//            jobRepository.save(job);
//        }else{
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        }

        jobRepository.save(job);
    }
    @Override
    public String deleteJob(Long id){
        Optional<Job> job= jobRepository.findById(id);
        if(job.isPresent()){
            jobRepository.delete(job.get());
            return "Job with title "+job.get().getTitle()+" has been deleted";
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Job with id "+id+" not found");
    }
    @Override
    public Job updateJob(Job job, Long id){
        Job job1;
        Optional<Job> temp= jobRepository.findById(id);
        if(temp.isPresent()){
            job1 = temp.get();
            job1.setTitle(job.getTitle());
            job1.setDescription(job.getDescription());
            job1.setMinSalary(job.getMinSalary());
            job1.setMaxSalary(job.getMaxSalary());
            job1.setLocation(job.getLocation());
            jobRepository.save(job1);
            return job1;
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Job with id "+id+" not found");
        }
    }
}
