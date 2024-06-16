package idkhi.tradecraft.order;

public class Order {
    private String item;
    private int amount;
    private double price;
    private OrderType type;

    public enum OrderType {
        BUY_LIMIT,
        SELL_LIMIT,
        BUY_MARKET,
        SELL_MARKET
    }

    public Order(String item, int amount, double price, OrderType type) {
        this.item = item;
        this.amount = amount;
        this.price = price;
        this.type = type;
    }

    public void modifyAmount(int newAmount) {
        this.amount = newAmount;
    }


    public String getItem() {
        return item;
    }

    public int getAmount() {
        return amount;
    }

    public double getPrice() {
        return price;
    }

    public OrderType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Order{" +
                "item='" + item + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                ", type=" + type +
                '}';
    }
}
