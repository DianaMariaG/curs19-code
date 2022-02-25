package ro.fasttrackit.curs19.homework.controller;

import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.curs19.exceptions.ResourceNotFoundException;
import ro.fasttrackit.curs19.homework.model.Transaction;
import ro.fasttrackit.curs19.homework.model.Type;
import ro.fasttrackit.curs19.homework.service.TransactionService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/transactions")
    public List<Transaction> getAllTransactions(@RequestParam(required = false) Optional<String> product,
                                                @RequestParam(required = false) Optional<Type> type,
                                                @RequestParam(required = false) Optional<Double> minAmount,
                                                @RequestParam(required = false) Optional<Double> maxAmount) {
        return transactionService.getAllTransactions(product, type, minAmount,maxAmount);
    }

    @GetMapping("/transactions/{id}")
    public Optional<Transaction> getTransactionById(@PathVariable int id) {
        return transactionService.getTransactionById(id);
    }

    @PostMapping("/addTransaction")
    public Transaction addNewTransaction(@RequestBody Transaction transaction) {
        return transactionService.addTransaction(transaction);
    }

    @PutMapping("/put/{id}")
    public Transaction replaceTransaction(@PathVariable int id, @RequestBody Transaction transaction) {
        return transactionService.replace(id,transaction)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found!"));
    }

    @DeleteMapping("delete/{id}")
    public Transaction deleteTransaction(@PathVariable int id) {
        return transactionService.deleteTransactionById(id)
                .orElse(null);
    }

    @GetMapping("/reports/type")
    public Map<Type, List<Transaction>> mapTypeToTransactions() {
        return transactionService.mapTypeToTransactions();
    }

    @GetMapping("/reports/product")
    public Map<String, List<Transaction>> mapProductToTransactions() {
        return transactionService.mapProductToTransactions();
    }
}
