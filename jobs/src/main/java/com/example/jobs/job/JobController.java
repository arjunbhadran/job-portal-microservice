package com.example.jobs.job;

import com.example.jobs.job.dto.JobWithCompanyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class JobController {
    @Autowired
    private JobService jobService;

    @GetMapping("/api/public/jobs")
    public ResponseEntity<List<JobWithCompanyDTO>> findAll(){
        return ResponseEntity.ok(jobService.getAlljobs());
    }

    @GetMapping("api/public/jobs/{id}")
    public ResponseEntity<Job> findById(@PathVariable Long id){
        return ResponseEntity.ok(jobService.getJobById(id));
    }

    @PostMapping("/api/admin/jobs")
    public ResponseEntity<String> createJob(@RequestBody Job job){
        jobService.createJob(job);
        return ResponseEntity.ok("Job created");
    }

    @DeleteMapping("/api/admin/jobs/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id){
        try {
            String status=jobService.deleteJob(id);
            return ResponseEntity.ok(status);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(),e.getStatusCode());
        }
    }

    @PutMapping("/api/admin/jobs/{id}")
    public Job updateJob(@PathVariable Long id, @RequestBody Job job){
        return jobService.updateJob(job,id);
    }
}
