package org.homework.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Developed by ChhornSeyha
 * Date: 26/09/2025
 */

@Setter
@Getter
public class ReviewMessage {
    private Long id;
    private String title;
    private String description;
    private double rating;
    private Long company;
}
