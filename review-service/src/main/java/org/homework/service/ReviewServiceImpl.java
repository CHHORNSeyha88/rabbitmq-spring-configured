package org.homework.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.homework.entity.Review;
import org.homework.repository.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Developed by ChhornSeyha
 * Date: 26/09/2025
 */

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl {
    private final ReviewRepository reviewRepository;

    public Review get(Long reviewId){
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new EntityNotFoundException("not found"));
    }
    public boolean addReview(Long companyId, Review review) {
        if (companyId == null || review == null) {
            return false;
        }
        // Set the foreign key on the review object
        review.setCompanyId(companyId);

        try {
            // Use the repository to save the review to the database
            reviewRepository.save(review);
            return true;
        } catch (Exception e) {
            // Log the exception and return false on failure
            System.err.println("Failed to save review: " + e.getMessage());
            return false;
        }
    }

    public List<Review> getAllReviews(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }
}
