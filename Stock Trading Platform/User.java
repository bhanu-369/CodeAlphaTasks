import java.util.HashMap;

public class User {
    private String name;
    private double balance;
    private HashMap<String, Integer> portfolio;

    public User(String name, double balance) {
        this.name = name;
        this.balance = balance;
        this.portfolio = new HashMap<>();
    }

    public String getName() { return name; }
    public double getBalance() { return balance; }

    public void buyStock(String symbol, double price, int quantity) {
        double totalCost = price * quantity;
        if (totalCost <= balance) {
            balance -= totalCost;
            portfolio.put(symbol, portfolio.getOrDefault(symbol, 0) + quantity);
            System.out.println("Purchased " + quantity + " shares of " + symbol);
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public void sellStock(String symbol, double price, int quantity) {
        int owned = portfolio.getOrDefault(symbol, 0);
        if (quantity <= owned) {
            balance += price * quantity;
            portfolio.put(symbol, owned - quantity);
            System.out.println("Sold " + quantity + " shares of " + symbol);
        } else {
            System.out.println("You don’t own enough shares.");
        }
    }

    public void displayPortfolio(HashMap<String, Stock> market) {
        System.out.println("\n--- Portfolio for " + name + " ---");
        double totalValue = balance;
        for (String symbol : portfolio.keySet()) {
            int qty = portfolio.get(symbol);
            double price = market.get(symbol).getPrice();
            double value = qty * price;
            System.out.printf("%s: %d shares @ $%.2f = $%.2f\n", symbol, qty, price, value);
            totalValue += value;
        }
        System.out.printf("Cash Balance: $%.2f\n", balance);
        System.out.printf("Total Portfolio Value: $%.2f\n", totalValue);
    }
}
public void savePortfolioToFile() {
    try (PrintWriter writer = new PrintWriter(name + "_portfolio.txt")) {
        writer.println("Balance: " + balance);
        for (String stock : portfolio.keySet()) {
            writer.println(stock + "," + portfolio.get(stock));
        }
        System.out.println("Portfolio saved.");
    } catch (IOException e) {
        System.out.println("Error saving portfolio.");
    }
}

