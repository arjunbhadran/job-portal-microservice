package com.example.jobs.job.clients;

import com.example.jobs.job.external.Review;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("review") //service name
public interface ReviewClient {
    @GetMapping("/reviews")
    List<Review> getReview(@RequestParam("companyId") Long id);
}
