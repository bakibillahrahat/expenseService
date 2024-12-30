package org.mbr.expenseservice.consumer;

import org.mbr.expenseservice.dto.ExpenseDTO;
import org.mbr.expenseservice.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExpenseConsumer {
    @Autowired
    private final ExpenseService expenseService;

    @KafkaListener(topics = "${spring.kafka.topic-json.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(@Payload(required = false) ExpenseDTO eventData) {
        try {
            expenseService.createExpense(eventData);
        } catch (Exception e) {
            // TODO: handle exception
            // e.printStackTrace();    
            System.out.println("AuthServiceConsumer: Exception is thrown while consuming kafka event");
        }
    }
}
