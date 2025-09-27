package org.homework.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.homework.client.ReviewClient;
import org.homework.dto.ReviewMessage;
import org.homework.entity.Company;
import org.homework.repository.CompanyRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Developed by ChhornSeyha
 * Date: 26/09/2025
 */

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl {
    private final CompanyRepository companyRepository;
    private final ReviewClient client;

    public Company create(Company company){
        return companyRepository.save(company);
    }

    public Company get(Long companyId){
        return companyRepository.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("not found"));
    }


    public void updateCompanyRating(ReviewMessage reviewMessage) {
        System.out.println(reviewMessage.getDescription());
        Company existCompany = companyRepository.findById(reviewMessage.getCompany())
                .orElseThrow(() -> new EntityNotFoundException("not found"));
        double averageRating = client.getAverageReviewRating(reviewMessage.getId());
        existCompany.setRating(averageRating);
        companyRepository.save(existCompany);
    }
}
