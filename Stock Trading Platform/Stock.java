public class Stock {
    private String symbol;
    private double price;

    public Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }

    public String getSymbol() { return symbol; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public void simulatePriceChange() {
        // Random price fluctuation
        double change = (Math.random() - 0.5) * 10; // ±5
        this.price = Math.max(1, this.price + change);
    }
}
