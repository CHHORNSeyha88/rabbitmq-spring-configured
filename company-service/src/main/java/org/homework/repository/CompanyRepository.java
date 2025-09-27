package org.homework.repository;

import org.homework.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Developed by ChhornSeyha
 * Date: 26/09/2025
 */

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {
}
