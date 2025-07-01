import java.util.Scanner;

public class TradingPlatform {

    public static void main(String[] args) {
//main code
        Scanner scanner = new Scanner(System.in);
        StockMarket market = new StockMarket();

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        User user = new User(name, 10000.0); // starting with $10,000

        while (true) {
            System.out.println("\n=== STOCK TRADING PLATFORM ===");
            System.out.println("1. View Market");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Simulate Market");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    market.displayMarket();
                    break;
                case "2":
                    System.out.print("Enter stock symbol to buy: ");
                    String buySymbol = scanner.nextLine().toUpperCase();
                    Stock buyStock = market.getStocks().get(buySymbol);
                    if (buyStock != null) {
                        System.out.print("Enter quantity: ");
                        int qty = Integer.parseInt(scanner.nextLine());
                        user.buyStock(buySymbol, buyStock.getPrice(), qty);
                    } else {
                        System.out.println("Stock not found.");
                    }
                    break;
                case "3":
                    System.out.print("Enter stock symbol to sell: ");
                    String sellSymbol = scanner.nextLine().toUpperCase();
                    Stock sellStock = market.getStocks().get(sellSymbol);
                    if (sellStock != null) {
                        System.out.print("Enter quantity: ");
                        int sellQty = Integer.parseInt(scanner.nextLine());
                        user.sellStock(sellSymbol, sellStock.getPrice(), sellQty);
                    } else {
                        System.out.println("Stock not found.");
                    }
                    break;
                case "4":
                    user.displayPortfolio(market.getStocks());
                    break;
                case "5":
                    market.updatePrices();
                    System.out.println("Market prices updated.");
                    break;
                case "6":
                    System.out.println("Exiting platform.");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
