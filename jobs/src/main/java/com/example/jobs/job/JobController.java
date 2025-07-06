package com.example.jobs.job;

import com.example.jobs.job.dto.JobWithCompanyAndReviewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class JobController {
    @Autowired
    private JobService jobService;

    @GetMapping("/jobs")
    public ResponseEntity<List<JobWithCompanyAndReviewDTO>> findAll(){
        return ResponseEntity.ok(jobService.getAlljobs());
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<JobWithCompanyAndReviewDTO> findById(@PathVariable Long id){
        //return ResponseEntity.ok(jobService.getJobById(id));
        JobWithCompanyAndReviewDTO jobWithCompanyDTO = jobService.getJobById(id);
        if(jobWithCompanyDTO!=null){
            return new ResponseEntity<>(jobWithCompanyDTO,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/jobs")
    public ResponseEntity<String> createJob(@RequestBody Job job){
        jobService.createJob(job);
        return ResponseEntity.ok("Job created");
    }

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id){
        try {
            String status=jobService.deleteJob(id);
            return ResponseEntity.ok(status);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(),e.getStatusCode());
        }
    }

    @PutMapping("/jobs/{id}")
    public Job updateJob(@PathVariable Long id, @RequestBody Job job){
        return jobService.updateJob(job,id);
    }
}
