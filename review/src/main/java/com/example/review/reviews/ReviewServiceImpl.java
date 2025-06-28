package com.example.review.reviews;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
//    @Autowired
//    private ReviewRepository reviewRepository;

    /*constructor injection is better than the above autowiring as
    * field injection causes risk of NullPointerException if
    * dependencies aren't correctly initialised*/
    private final ReviewRepository reviewRepository;
    public ReviewServiceImpl(ReviewRepository reviewRepository){
        this.reviewRepository=reviewRepository;
    }


    @Override
    public List<Review> getAllReviews(Long companyId) {
//        List<Review> reviews = reviewRepository.findAll();
//        List<Review> companySpecific=new ArrayList<>();
//        for(Review review : reviews) {
//            Company company=review.getCompanyToReview();
//            if(Objects.equals(company.getCompanyId(), companyId)){
//                companySpecific.add(review);
//            }
//        }
//        return companySpecific;
        /*Either do the above or go to ReviewRepository and add a findByCompanyId method.
        * Spring data jpa automatically generates an implementation for it by understanding
        * the name of the method (findByCompanyId).
        * JPA automatically generates the SQL query for it.*/

        return reviewRepository.findAll();
    }

    @Override
    public Review getReviewById(Long companyId,Long reviewId) {
        List<Review> reviewsByCompany=this.getAllReviews(companyId);
        for(Review review : reviewsByCompany) {
            if(review.getReviewId().equals(reviewId)) {
                return review;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @Override
    public Boolean createReview(Long companyId,Review review) {
//        Company company=companyService.getCompanyById(companyId);
//        if(company!=null){
//            review.setCompanyToReview(company);
//            reviewRepository.save(review);
//            return true;
//        }
//        return false;

        if(companyId!=null&&review!=null) {
            review.setCompanyId(companyId);
            reviewRepository.save(review);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String deleteReview(Long reviewId) {
        Optional<Review> review1=reviewRepository.findById(reviewId);
//        Company company=companyService.getCompanyById(companyId);
//        if(review1.isPresent()&& company!=null){
//            Review review=review1.get();
//            company.getReviews().remove(review);
//            companyService.updateCompany(company,companyId);
//            reviewRepository.delete(review);
//            return "Review with title: "+review.getReviewTitle()+" deleted successfully";
//        }else{
//            return "Review not found";
//        }
        if(review1.isPresent()){
            reviewRepository.deleteById(reviewId);
            return "Review with title: "+review1.get().getReviewTitle()+" deleted successfully";
        }else{
            return "Review not found";
        }
    }

    @Override
    public Boolean updateReview(Long reviewId, Review review) {
        Optional<Review> review1=reviewRepository.findById(reviewId);
        if(review1.isPresent()){
            Review updatedReview=review1.get();
            updatedReview.setReviewTitle(review.getReviewTitle());
            updatedReview.setReviewContent(review.getReviewContent());
            updatedReview.setReviewRating(review.getReviewRating());
            updatedReview.setCompanyId(review.getCompanyId());
            reviewRepository.save(updatedReview);
            return true;
        }else{
            return false;
        }
//        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        if(companyService.getCompanyById(companyId)!=null){
//            Company company = companyService.getCompanyById(companyId);
//            review.setCompanyToReview(company);
//            review.setReviewId(reviewId);
//            reviewRepository.save(review);
//            return true;
//        }
//        return false;
    }
}
