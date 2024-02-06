import java.util.ArrayList;
import java.util.List;

class Company {
    private String companyName;
    private List<Tariff> tariffs;

    public Company(String companyName) {
        this.companyName = companyName;
        this.tariffs = new ArrayList<>();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<Tariff> getTariffs() {
        return tariffs;
    }

    public void addTariff(Tariff tariff) {
        tariffs.add(tariff);
    }

    public Subscriber findSubscriberByPhoneNumber(String phoneNumber) {
        for (Tariff tariff : tariffs) {
            for (Subscriber subscriber : tariff.getSubscribers()) {
                if (subscriber.getPhoneNumber().equals(phoneNumber)) {
                    return subscriber;
                }
            }
        }
        return null; // Subscriber not found
    }
}

class Tariff {
    private String tariffName;
    private Company company;
    private List<Subscriber> subscribers;

    public Tariff(String tariffName, Company company) {
        this.tariffName = tariffName;
        this.company = company;
        this.subscribers = new ArrayList<>();
    }

    public String getTariffName() {
        return tariffName;
    }

    public void setTariffName(String tariffName) {
        this.tariffName = tariffName;
    }

    public Company getCompany() {
        return company;
    }

    public List<Subscriber> getSubscribers() {
        return subscribers;
    }

    public void addSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void removeSubscriber(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public int getNumberOfSubscribers() {
        return subscribers.size();
    }
}

class Subscriber {
    private String employeeName;
    private String phoneNumber;
    private double accountBalance;
    private Tariff tariff;

    public Subscriber(String employeeName, String phoneNumber, double accountBalance, Tariff tariff) {
        this.employeeName = employeeName;
        this.phoneNumber = phoneNumber;
        this.accountBalance = accountBalance;
        this.tariff = tariff;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public Tariff getTariff() {
        return tariff;
    }

    public void replenishAccount(double amount) {
        this.accountBalance += amount;
    }
}

public class Main {
    public static void main(String[] args) {
        // Creating companies
        Company company1 = new Company("Company 1");
        Company company2 = new Company("Company 2");

        // Creating tariffs
        Tariff tariff1 = new Tariff("Tariff 1", company1);
        Tariff tariff2 = new Tariff("Tariff 2", company2);

        // Adding tariffs to companies
        company1.addTariff(tariff1);
        company2.addTariff(tariff2);

        // Creating subscribers
        Subscriber subscriber1 = new Subscriber("Subscriber 1", "123456789", 50.0, tariff1);
        Subscriber subscriber2 = new Subscriber("Subscriber 2", "987654321", 30.0, tariff1);
        Subscriber subscriber3 = new Subscriber("Subscriber 3", "555555555", 20.0, tariff2);

        // Adding subscribers to tariffs
        tariff1.addSubscriber(subscriber1);
        tariff1.addSubscriber(subscriber2);
        tariff2.addSubscriber(subscriber3);

        // Displaying subscribers of company1
        System.out.println("Subscribers of " + company1.getCompanyName() + ":");
        for (Subscriber subscriber : tariff1.getSubscribers()) {
            System.out.println("Name: " + subscriber.getEmployeeName() + ", Phone: " + subscriber.getPhoneNumber() +
                    ", Tariff: " + subscriber.getTariff().getTariffName());
        }

        // Replenishing account balance of subscriber1
        subscriber1.replenishAccount(20.0);

        // Displaying subscribers of company1 after account replenishment
        System.out.println("\nSubscribers of " + company1.getCompanyName() + " after account replenishment:");
        for (Subscriber subscriber : tariff1.getSubscribers()) {
            System.out.println("Name: " + subscriber.getEmployeeName() + ", Phone: " + subscriber.getPhoneNumber() +
                    ", Tariff: " + subscriber.getTariff().getTariffName() + ", Account Balance: " + subscriber.getAccountBalance());
        }

        // Searching for a subscriber by phone number
        String phoneNumberToSearch = "123456789";
        Subscriber foundSubscriber = company1.findSubscriberByPhoneNumber(phoneNumberToSearch);
        if (foundSubscriber != null) {
            System.out.println("\nFound Subscriber:");
            System.out.println("Name: " + foundSubscriber.getEmployeeName() +
                    ", Tariff: " + foundSubscriber.getTariff().getTariffName());
        } else {
            System.out.println("\nSubscriber with phone number " + phoneNumberToSearch + " not found.");
        }
    }
}
