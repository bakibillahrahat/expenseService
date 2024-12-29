package org.mbr.expenseservice.consumer;

import org.mbr.expenseservice.dto.ExpenseDTO;
import org.mbr.expenseservice.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExpenseConsumer {
    @Autowired
    private final ExpenseService expenseService;

    @KafkaListener(topics = "${spring.kafka.topic-json.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(ExpenseDTO expenseDTO) {
        try {
            expenseService.createExpense(expenseDTO);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Exception in listen method: " + e.getMessage());
        }
    }
}
