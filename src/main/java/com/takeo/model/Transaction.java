package com.takeo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int transactionId;
    @JsonIgnore
    private int portfolioId;
    private int stockId;
    private String transactionType; //buy or sell//
    private int amount;
    private int quantity;
    @JsonIgnore
    @Temporal(TemporalType.DATE)
    private LocalDate date;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "Acc_id")
    private TradingAccount account;
}
