package org.homework.messaging;

import lombok.RequiredArgsConstructor;
import org.homework.dto.ReviewMessage;
import org.homework.service.CompanyServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * Developed by ChhornSeyha
 * Date: 26/09/2025
 */

@Service
@RequiredArgsConstructor
public class ReviewMessageConsumer {
    private final CompanyServiceImpl companyService;

    @RabbitListener(queues = "companyRatingQueue")
    public void consumerMessage(ReviewMessage reviewMessage){
        System.out.println(reviewMessage.getDescription());
        companyService.updateCompanyRating(reviewMessage);
    }
}
