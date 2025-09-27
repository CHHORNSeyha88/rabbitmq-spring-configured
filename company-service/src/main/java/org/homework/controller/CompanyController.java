package org.homework.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.homework.repository.CompanyRepository;
import org.homework.entity.Company;
import org.homework.service.CompanyServiceImpl;
import org.springframework.web.bind.annotation.*;

/**
 * Developed by ChhornSeyha
 * Date: 26/09/2025
 */

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/companies")
public class CompanyController {
    private final CompanyServiceImpl companyService;
    @PostMapping
    public Company create(Company company){
        return companyService.create(company);
    }

    @GetMapping("/{companyId}")
    public Company get(@PathVariable Long companyId){
        return companyService.get(companyId);
    }
}
