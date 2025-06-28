package com.example.review.reviews;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
//    @Autowired
//    private ReviewService reviewService;

    private final ReviewService reviewService;
    public ReviewController(ReviewService reviewService){// constructor injection
        this.reviewService=reviewService;
    }

    @GetMapping//since RequestMapping is already done before and companyId is a request parameter
    public ResponseEntity<List<Review>> getReviews(@RequestParam Long companyId) {
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createReview(@RequestParam Long companyId, @RequestBody Review review) {
        if(reviewService.createReview(companyId, review)) {
            return new ResponseEntity<>("Review created", HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>("Review not created", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReview(@RequestParam Long companyId, @PathVariable Long reviewId) {
        return new ResponseEntity<>(reviewService.getReviewById(companyId, reviewId), HttpStatus.OK);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview( @PathVariable Long reviewId) {
        String status=reviewService.deleteReview(reviewId);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(/*@PathVariable Long companyId,*/
                                               @PathVariable Long reviewId,
                                               @RequestBody Review review){
        if(reviewService.updateReview(reviewId, review)) {
            return new ResponseEntity<>("Review has been updated", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Review not updated", HttpStatus.BAD_REQUEST);
        }
    }
}
