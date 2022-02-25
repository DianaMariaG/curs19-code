package ro.fasttrackit.curs19.homework.model;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import static ro.fasttrackit.curs19.homework.StringUtils.ensureNotEmpty;
import static ro.fasttrackit.curs19.homework.StringUtils.ensureNotNegative;

public class Transaction {
    private int id;
    private final String product;
    private final Type type;
    private final double amount;

    public Transaction(int id, String product, Type type, Double amount) {
        this.id = 0;
        this.product = ensureNotEmpty(product);
        this.type = type;
        this.amount = ensureNotNegative(amount);
    }

    public String getProduct() {
        return product;
    }

    public Type getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return id == that.id && Double.compare(that.amount, amount) == 0 && Objects.equals(product, that.product) && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product, type, amount);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", product='" + product + '\'' +
                ", type=" + type +
                ", amount=" + amount +
                '}';
    }
}
