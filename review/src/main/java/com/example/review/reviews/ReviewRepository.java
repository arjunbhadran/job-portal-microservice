package com.example.review.reviews;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    //List<Review> findBycompanyId(Long companyId);
}
