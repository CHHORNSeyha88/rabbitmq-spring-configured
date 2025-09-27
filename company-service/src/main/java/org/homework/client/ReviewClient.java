package org.homework.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Developed by ChhornSeyha
 * Date: 26/09/2025
 */

@FeignClient(name = "review-service", url = "http://localhost:9001", path = "api/v1/reviews")
public interface ReviewClient {
    @GetMapping("/averageRating")
     Double getAverageReviewRating(@RequestParam Long companyId);
}
