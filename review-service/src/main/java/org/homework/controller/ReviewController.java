package org.homework.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.homework.entity.Review;
import org.homework.messaging.ReviewMessageProducer;
import org.homework.repository.ReviewRepository;
import org.homework.service.ReviewServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Developed by ChhornSeyha
 * Date: 26/09/2025
 */

@RestController
@RequestMapping("api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewServiceImpl reviewService;
    private final ReviewMessageProducer reviewMessageProducer;

    @PostMapping
    public ResponseEntity<String> addReview(@RequestParam Long companyId, @RequestBody Review review){
        boolean add = reviewService.addReview(companyId,review);
        if(add){
            reviewMessageProducer.sendMessage(review);
            return new ResponseEntity<>("review saved successfully", HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("review not saved",HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{reviewId}")
    public Review get(@PathVariable Long reviewId){
        return reviewService.get(reviewId);
    }

    @GetMapping("/averageRating")
    public Double getAverageReviewRating(@RequestParam Long companyId){
        List<Review> reviewList = reviewService.getAllReviews(companyId);
        return reviewList.stream().mapToDouble(Review::getRating).average().orElse(0.0);
    }
}
