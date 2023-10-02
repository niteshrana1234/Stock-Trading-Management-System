package com.takeo.utils;

import com.takeo.model.Transaction;

import java.util.List;

public class TransactionCalc {

    public static List<Transaction> deposit(List<Transaction> list ,int amount){

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setTransactionType("Deposit");

        list.add(transaction);

        return list;

    }

    public static List<Transaction> withdraw(List<Transaction> list ,int amount){

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setTransactionType("Withdraw");

        list.add(transaction);

        return list;

    }



}
