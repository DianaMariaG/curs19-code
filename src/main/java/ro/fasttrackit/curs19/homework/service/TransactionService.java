package ro.fasttrackit.curs19.homework.service;

import org.springframework.stereotype.Service;
import ro.fasttrackit.curs19.homework.model.Transaction;
import ro.fasttrackit.curs19.homework.model.Type;
import ro.fasttrackit.curs19.model.Country;

import java.util.*;
import java.util.stream.Collectors;

import static ro.fasttrackit.curs19.homework.model.Type.SELL;

@Service
public class TransactionService {
    private final List<Transaction> transactions = new ArrayList<>();
    private int currentId;

    public TransactionService (TransactionsProvider transactionsProvider) {

        this.currentId = 0;
        if (transactionsProvider != null) {
            for (Transaction transaction : transactionsProvider.getTransactions()) {
                this.addTransaction(transaction);
            }
        }
    }
    //POST new transaction
    public Transaction addTransaction(Transaction transaction) {
        transactions.add(transaction);
        transaction.setId(++currentId);
        return transaction;
    }

    public List<Transaction> getAllTransactions(Optional<String> product, Optional<Type> type,
                                                Optional<Double> minAmount, Optional<Double> maxAmount) {
        return transactions.stream()
                .filter(transaction -> product.isEmpty() || transaction.getProduct().equals(product.get()))
                .filter(transaction -> type.isEmpty() || transaction.getType() == type.get())
                .filter(transaction -> minAmount.isEmpty() || transaction.getAmount() >= minAmount.get())
                .filter(transaction -> maxAmount.isEmpty() || transaction.getAmount() <= maxAmount.get())
                .collect(Collectors.toList());
    }


    public Optional<Transaction> getTransactionById (int id) {
        return transactions.stream()
                .filter(transaction -> transaction.getId() == id)
                .findFirst();
    }

    public Optional<Transaction> replace(int id, Transaction transaction) {
        return findById(id)
                .map(existing -> replaceExistingTransaction(id, existing, transaction));
    }

    private Transaction replaceExistingTransaction(int id, Transaction existing, Transaction replacedTransaction) {
        this.transactions.remove(existing);
        Transaction newTransaction = cloneWithId(id, replacedTransaction);
        this.transactions.add(id, newTransaction);
        return newTransaction;
    }

    public Optional<Transaction> deleteTransactionById(int id) {
        Optional<Transaction> transactionToDelete = findById(id);
        transactionToDelete.ifPresent(o -> this.transactions.remove(o));
        return transactionToDelete;
    }

    public Map<Type, List<Transaction>> mapTypeToTransactions() {
        Map<Type, List<Transaction>> result = new HashMap<>();
        for (Transaction transaction : transactions) {
            List<Transaction> transactionsList = result.get(transaction.getType());

            if (transactionsList == null) {
                transactionsList = new ArrayList<>();
                result.put(transaction.getType(), transactionsList);
            }
            transactionsList.add(transaction);
        }
        return result;
    }

    public Map<String, List<Transaction>> mapProductToTransactions() {
        Map<String, List<Transaction>> result = new HashMap<>();
        for (Transaction transaction : transactions) {
            List<Transaction> transactionsList = result.get(transaction.getProduct());

            if (transactionsList == null) {
                transactionsList = new ArrayList<>();
                result.put(transaction.getProduct(),transactionsList);
            }
            transactionsList.add(transaction);
        }
        return result;
    }

    public Optional<Transaction> findById(int id) {
        return transactions.stream()
                .filter(transaction ->transaction.getId() == id)
                .findFirst();
    }

    private Transaction cloneWithId(int id, Transaction transaction) {
        return new Transaction(
                id,
                transaction.getProduct(),
                transaction.getType(),
                transaction.getAmount()
        );
    }
}


