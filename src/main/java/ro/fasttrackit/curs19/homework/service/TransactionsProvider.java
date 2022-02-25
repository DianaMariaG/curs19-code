package ro.fasttrackit.curs19.homework.service;

import org.springframework.stereotype.Component;
import ro.fasttrackit.curs19.homework.model.Transaction;

import java.util.List;

import static ro.fasttrackit.curs19.homework.model.Type.BUY;
import static ro.fasttrackit.curs19.homework.model.Type.SELL;

@Component
public class TransactionsProvider {
    public List<Transaction> getTransactions() {
        return List.of(
                new Transaction(3, "payment", SELL, 120.0),
				new Transaction(55, "payment", SELL, 550.25),
				new Transaction(88, "payment", BUY, 330.85),
				new Transaction(100, "invoice", BUY, 700.0)
        );
    }
}
