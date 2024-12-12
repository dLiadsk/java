import java.util.*;

/**
 * Головний клас для запуску програми.
 */
public class Main {
    public static void main(String[] args) {
        MobileCompany company = new MobileCompany();

        // Створення тарифів
        Tariff tariff1 = new BaseTariff("Тариф Легкий", 100);
        Tariff tariff2 = new TariffForStudents("Тариф Сімейний", 250);
        Tariff tariff3 = new TariffForBusiness("Тариф Бізнес", 500);
        Tariff tariff4 = new TariffForFamily("Тариф Родина", 150);
        Tariff tariff5 = new BaseTariff("Тариф Економ", 50);

        // Додавання тарифів
        company.addPlan(tariff1);
        company.addPlan(tariff2);
        company.addPlan(tariff3);
        company.addPlan(tariff4);
        company.addPlan(tariff5);

        // Додавання клієнтів до тарифів
        tariff1.addCustomer(new Customer("+380123456789", tariff1));
        tariff1.addCustomer(new Customer("+380987654321", tariff1));
        tariff2.addCustomer(new Customer("+380555555555", tariff2));
        tariff3.addCustomer(new Customer("+380444444444", tariff3));
        tariff3.addCustomer(new Customer("+380333333333", tariff3));
        tariff4.addCustomer(new Customer("+380222222222", tariff4));
        tariff5.addCustomer(new Customer("+380111111111", tariff5));

        // Виведення списку тарифів
        System.out.println("Список тарифів:");
        company.printPlans();

        // Підрахунок клієнтів
        System.out.println("Загальна кількість клієнтів: " + company.getCustomers().size());

        // Сортування тарифів за абонентською платою
        company.sortByMonthlyFee();
        System.out.println("\nТарифи після сортування за абонентською платою:");
        company.printPlans();

        // Пошук тарифів у заданому діапазоні вартості
        System.out.println("\nТарифи у діапазоні вартості 100-300:");
        company.findPlansByFeeRange(100, 300).forEach(System.out::println);
    }
}

/**
 * Клас для зберігання інформації про клієнтів мобільної компанії.
 */
class Customer {
    private final String phoneNumber;
    private final Tariff tariff;

    /**
     * Конструктор для створення клієнта.
     *
     * @param phoneNumber номер телефону клієнта
     * @param tariff      тариф клієнта
     */
    public Customer(String phoneNumber, Tariff tariff) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            throw new IllegalArgumentException("Номер телефону не може бути порожнім.");
        }
        this.phoneNumber = phoneNumber;
        this.tariff = tariff;
    }

    /**
     * @return номер телефону клієнта
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @return тариф клієнта
     */
    public Tariff getTariff() {
        return tariff;
    }

    @Override
    public String toString() {
        return "Customer with phone number: " + phoneNumber;
    }
}

/**
 * Абстрактний клас, що описує тариф мобільної компанії.
 */
abstract class Tariff {
    private final String name;
    private final double monthlyFee;
    private final List<Customer> customers;

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
        this.customers = new ArrayList<>();
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

/**
 * Клас, що описує базовий тариф.
 */
class BaseTariff extends Tariff {
    public BaseTariff(String name, double monthlyFee) {
        super(name, monthlyFee);
    }

    @Override
    public String getPlanType() {
        return "Базовий";
    }
}

/**
 * Клас, що описує тариф для студентів.
 */
class TariffForStudents extends Tariff {
    public TariffForStudents(String name, double monthlyFee) {
        super(name, monthlyFee);
    }

    @Override
    public String getPlanType() {
        return "Для студентів";
    }
}

/**
 * Клас, що описує тариф для сімей.
 */
class TariffForFamily extends Tariff {
    public TariffForFamily(String name, double monthlyFee) {
        super(name, monthlyFee);
    }

    @Override
    public String getPlanType() {
        return "Для сім'ї";
    }
}

/**
 * Клас, що описує тариф для бізнесу.
 */
class TariffForBusiness extends Tariff {
    public TariffForBusiness(String name, double monthlyFee) {
        super(name, monthlyFee);
    }

    @Override
    public String getPlanType() {
        return "Для бізнесу";
    }
}

/**
 * Клас, що описує мобільну компанію.
 */
class MobileCompany {
    private final List<Tariff> plans;

    public MobileCompany() {
        plans = new ArrayList<>();
    }

    /**
     * Додає тариф до списку компанії.
     *
     * @param plan тариф для додавання
     */
    public void addPlan(Tariff plan) {
        plans.add(Objects.requireNonNull(plan, "Тариф не може бути null."));
    }

    /**
     * @return список усіх клієнтів компанії
     */
    public List<Customer> getCustomers() {
        return plans.stream()
                .flatMap(plan -> plan.getCustomers().stream())
                .toList();
    }

    /**
     * Сортує тарифи за абонентською платою.
     */
    public void sortByMonthlyFee() {
        plans.sort(Comparator.comparingDouble(Tariff::getMonthlyFee));
    }

    /**
     * Знаходить тарифи у заданому діапазоні абонентської плати.
     *
     * @param minFee мінімальна плата
     * @param maxFee максимальна плата
     * @return список тарифів у заданому діапазоні\n
     */
    public List<Tariff> findPlansByFeeRange(double minFee, double maxFee) {
        return plans.stream()
                .filter(plan -> plan.getMonthlyFee() >= minFee && plan.getMonthlyFee() <= maxFee)
                .toList();
    }

    /**
     * Виводить список тарифів компанії.\n
     */
    public void printPlans() {
        plans.forEach(System.out::println);
    }
}