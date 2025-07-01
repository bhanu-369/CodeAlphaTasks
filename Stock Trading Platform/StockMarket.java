import java.util.HashMap;

public class StockMarket {
    private HashMap<String, Stock> stocks;

    public StockMarket() {
        stocks = new HashMap<>();
        // Sample stocks
        stocks.put("AAPL", new Stock("AAPL", 150.0));
        stocks.put("GOOG", new Stock("GOOG", 2800.0));
        stocks.put("TSLA", new Stock("TSLA", 700.0));
        stocks.put("AMZN", new Stock("AMZN", 3300.0));
    }

    public HashMap<String, Stock> getStocks() { return stocks; }

    public void displayMarket() {
        System.out.println("\n--- Market Data ---");
        for (Stock s : stocks.values()) {
            System.out.printf("%s: $%.2f\n", s.getSymbol(), s.getPrice());
        }
    }

    public void updatePrices() {
        for (Stock s : stocks.values()) {
            s.simulatePriceChange();
        }
    }
}
