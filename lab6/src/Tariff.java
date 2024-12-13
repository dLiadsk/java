import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Абстрактний клас, що описує тариф мобільної компанії.
 */
abstract class Tariff {
    private final String name;
    private final double monthlyFee;
    private final MyLinkedList<Customer> customers;

    /**
     * Конструктор для створення тарифу.
     *
     * @param name       назва тарифу
     * @param monthlyFee абонентська плата тарифу
     */
    public Tariff(String name, double monthlyFee) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Назва тарифу не може бути порожньою.");
        }
        if (monthlyFee < 0) {
            throw new IllegalArgumentException("Абонентська плата не може бути від'ємною.");
        }
        this.name = name;
        this.monthlyFee = monthlyFee;
        this.customers = new MyLinkedList<>();
    }

    /**
     * @return назва тарифу
     */
    public String getName() {
        return name;
    }

    /**
     * @return абонентська плата тарифу
     */
    public double getMonthlyFee() {
        return monthlyFee;
    }

    /**
     * @return список клієнтів тарифу
     */
    public List<Customer> getCustomers() {
        return Collections.unmodifiableList(customers);
    }

    /**
     * Додає клієнта до тарифу.
     *
     * @param customer клієнт для додавання
     */
    public void addCustomer(Customer customer) {
        customers.add(Objects.requireNonNull(customer, "Клієнт не може бути null."));
    }

    /**
     * @return тип тарифу (для реалізації в нащадках)
     */
    public abstract String getPlanType();

    @Override
    public String toString() {
        return String.format("Тариф: %s, Тип: %s, Абонплата: %.2f, Клієнти: %d",
                name, getPlanType(), monthlyFee, customers.size());
    }
}
