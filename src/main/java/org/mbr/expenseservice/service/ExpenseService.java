package org.mbr.expenseservice.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.logging.log4j.util.Strings;
import org.mbr.expenseservice.dto.ExpenseDTO;
import org.mbr.expenseservice.entities.Expense;
import org.mbr.expenseservice.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ExpenseService {
    @Autowired
    private final ExpenseRepository expenseRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    public boolean createExpense(ExpenseDTO expenseDTO){
        setCurrency(expenseDTO);
        try {
            expenseRepository.save(objectMapper.convertValue(expenseDTO, Expense.class));
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }
    }

    private void setCurrency(ExpenseDTO expenseDTO) {
        if(Objects.isNull(expenseDTO.getCurrency())){
            expenseDTO.setCurrency("BDT");
        }

    }

    public boolean updateExpense(ExpenseDTO expenseDTO){
        Optional<Expense> expenseFoundOpt = expenseRepository.findByUserIdAndExternalId(expenseDTO.getUserId(), expenseDTO.getExternalId());
        try {
            if(expenseFoundOpt.isEmpty()){
                return false;
            }
            Expense expenseFound = expenseFoundOpt.get();
            expenseFound.setCurrency(Strings.isNotBlank(expenseDTO.getCurrency()) ? expenseDTO.getCurrency() : expenseFound.getCurrency());
            expenseFound.setMerchant(Strings.isNotBlank(expenseDTO.getMerchant()) ? expenseDTO.getMerchant() : expenseFound.getMerchant());
            expenseFound.setAmount(expenseDTO.getAmount());
            expenseRepository.save(expenseFound);
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }
    } 

    public List<ExpenseDTO> getExpenses(String userId){
        List<Expense> expenseOpt = expenseRepository.findByUserId(userId);
        return objectMapper.convertValue(expenseOpt, new TypeReference<List<ExpenseDTO>>() {});
    }
}