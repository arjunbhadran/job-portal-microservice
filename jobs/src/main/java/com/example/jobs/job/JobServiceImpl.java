package com.example.jobs.job;

import com.example.jobs.job.clients.CompanyClient;
import com.example.jobs.job.clients.ReviewClient;
import com.example.jobs.job.dto.JobWithCompanyAndReviewDTO;
import com.example.jobs.job.external.Company;
import com.example.jobs.job.external.Review;
import com.example.jobs.job.mapper.JobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {
    @Autowired
    JobRepository jobRepository;

//    @Autowired
//    RestTemplate restTemplate;

    //OpenFeigen clients instead of RestTemplate
    @Autowired
    private CompanyClient companyClient;
    @Autowired
    private ReviewClient reviewClient;

//    @Autowired
//    private CompanyService companyService;

    @Override
    public List<JobWithCompanyAndReviewDTO> getAlljobs(){
        List<Job> jobs = jobRepository.findAll();
        List<JobWithCompanyAndReviewDTO> jobWithCompanyDTOS = new ArrayList<>();
//        for(Job job:jobs){
//            JobWithCompanyDTO jobWithCompanyDTO = JobCompanyConcat(job);
//            jobWithCompanyDTOS.add(jobWithCompanyDTO);
//        }
//        //return jobRepository.findAll();
//        return jobWithCompanyDTOS;
        return jobs.stream().map(this::JobCompanyConcat).collect(Collectors.toList());
    }

    private JobWithCompanyAndReviewDTO JobCompanyConcat(Job job){
        //JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
        //jobWithCompanyDTO.setJob(job);
        //RestTemplate restTemplate = new RestTemplate();

        //Company company=restTemplate.getForObject("http://company:8081/api/public/companies/"+String.valueOf(job.getCompanyId()),Company.class);

        //using open feign to fulfil the above request
        Company company=companyClient.getCompany(job.getCompanyId());

//        ResponseEntity<List<Review>> reviewResponse =restTemplate.exchange("http://review:8083/reviews?companyId=" + String.valueOf(job.getCompanyId()),
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<Review>>() {
//                });
//        List<Review> reviews=reviewResponse.getBody();

        //using openfeign to do the above request
        List<Review> reviews=reviewClient.getReview(job.getCompanyId());
        return new JobMapper().mapToJobWithCompanyDto(job, company, reviews);
    }

//    @Override
//    public Job getJobById(Long id) {
//        Optional<Job> job=jobRepository.findById(id);
//        if(job.isPresent()){
//            return job.get();
//        }else{
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        }
//    }

    @Override
    public JobWithCompanyAndReviewDTO getJobById(Long id) {
        Job job = jobRepository.findById(id).orElse(null);
        return JobCompanyConcat(job);
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
