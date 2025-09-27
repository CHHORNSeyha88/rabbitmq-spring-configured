// Corrected CompanyServiceImpl.java
package org.homework.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.homework.client.ReviewClient;
import org.homework.dto.ReviewMessage;
import org.homework.entity.Company;
import org.homework.repository.CompanyRepository;
import org.springframework.stereotype.Service;

/**
 * Developed by ChhornSeyha
 * Date: 26/09/2025
 */

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl {
    private final CompanyRepository companyRepository;
    private final ReviewClient client;

    public Company create(Company company) {
        return companyRepository.save(company);
    }

    public Company get(Long companyId) {
        return companyRepository.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("not found"));
    }

    public void updateCompanyRating(ReviewMessage reviewMessage) {
        System.out.println(reviewMessage.getDescription());
        Company existCompany = companyRepository.findById(reviewMessage.getCompany())
                .orElseThrow(() -> new EntityNotFoundException("not found"));

        // Corrected Feign client call: use the companyId from the ReviewMessage
        double averageRating = client.getAverageReviewRating(reviewMessage.getCompany());

        existCompany.setRating(averageRating);
        companyRepository.save(existCompany);
    }
}