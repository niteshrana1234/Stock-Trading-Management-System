package com.takeo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int transactionId;
    private String transactionType; //fund, withdraw, buy, sell
    private String stockName;
    @Column(name="total_amount")
    private double amount;
    private int quantity;
    @JsonIgnore
    @Temporal(TemporalType.DATE)
    private Date date = new Date();

}
