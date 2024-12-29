package org.mbr.expenseservice.dto;


import java.math.BigDecimal;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExpenseDTO {
    
    private String externalId;
    @JsonProperty(value = "user_id")
    private String userId;
    @JsonProperty(value = "amount")
    @NonNull
    private BigDecimal amount;
    @JsonProperty(value = "merchant")
    private String merchant;
    @JsonProperty(value = "currency")
    private String currency;
    @JsonProperty(value = "created_at")
    private Timestamp createdAt;
}