package org.mbr.expenseservice.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Expense {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "external_id")
    private String externalId;
    @Column(name = "user_id")
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

    @PrePersist
    @PreUpdate
    private void generateExternalId() {
        if(this.externalId == null) {
            this.externalId = UUID.randomUUID().toString();
        }
    }
}
