package org.homework.messaging;

import lombok.RequiredArgsConstructor;
import org.homework.dto.ReviewMessage;
import org.homework.entity.Review;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

/**
 * Developed by ChhornSeyha
 * Date: 26/09/2025
 */

@Service
@RequiredArgsConstructor
public class ReviewMessageProducer {
    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(Review review){
        ReviewMessage rm = new ReviewMessage();
        rm.setId(review.getId());
        rm.setTitle(review.getTitle());
        rm.setDescription(review.getDescription());
        rm.setCompany(review.getCompanyId());
        rabbitTemplate.convertAndSend("companyRatingQueue",rm);

    }
}
