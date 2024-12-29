package org.mbr.expenseservice.consumer;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.mbr.expenseservice.dto.ExpenseDTO;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ExpenseDeserializer implements Deserializer<ExpenseDTO> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {}

    @Override
    public ExpenseDTO deserialize(String topic, byte[] data) {
        ObjectMapper objectMapper = new ObjectMapper();
        ExpenseDTO expenseDto = null;
        try{
            expenseDto = objectMapper.readValue(topic, ExpenseDTO.class);
        }catch(Exception e){
            e.printStackTrace();
        }
        return expenseDto;
    }
    
    @Override
    public void close() {}
}
