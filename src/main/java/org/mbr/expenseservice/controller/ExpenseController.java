package org.mbr.expenseservice.controller;

import java.util.List;

import org.mbr.expenseservice.dto.ExpenseDTO;
import org.mbr.expenseservice.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.common.lang.NonNull;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequiredArgsConstructor
public class ExpenseController {
    
    @Autowired
    private final ExpenseService expenseService;

    @GetMapping("/expense/v1/")
    public ResponseEntity<List<ExpenseDTO>> getExpense(@PathParam("user_id") @NonNull String userId){ 
        try {
            List<ExpenseDTO> expenseDtoList = expenseService.getExpenses(userId);
            return new ResponseEntity<>(expenseDtoList, HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    
    
}
