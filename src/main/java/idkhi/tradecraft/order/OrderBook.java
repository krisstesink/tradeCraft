package idkhi.tradecraft.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderBook {

    private HashMap<Double, List<Order>> buyOrders;
    private HashMap<Double, List<Order>> sellOrders;

    public OrderBook() {
        this.buyOrders = new HashMap<>();
        this.sellOrders = new HashMap<>();
    }

    public void addOrder(Order order) {
        if (order.getType() == Order.OrderType.BUY_LIMIT) {
            buyOrders.computeIfAbsent(order.getPrice(), k -> new ArrayList<Order>()).add(order);
        } else {
            sellOrders.computeIfAbsent(order.getPrice(), k -> new ArrayList<Order>()).add(order);
        }
    }

    public void cancelOrder(Order order) {
        if (order.getType() == Order.OrderType.BUY_LIMIT) {
            buyOrders.get(order.getPrice()).remove(order);
        } else {
            sellOrders.get(order.getPrice()).remove(order);
        }
    }

    public double getBestBid() {
        // Get the highest price level with buy orders
        return buyOrders.isEmpty() ? Double.MIN_VALUE : buyOrders.keySet().stream().max(Double::compareTo).get();
    }

    public double getBestAsk() {
        // Get the lowest price level with sell orders
        return sellOrders.isEmpty() ? Double.MAX_VALUE : sellOrders.keySet().stream().min(Double::compareTo).get();
    }

    public int getBuyOrderAmount(Double price) {
        List<Order> orders = this.buyOrders.get(price);
        if(orders != null) {
            int amount = 0;
            for (Order order: orders) {
                amount += order.getAmount();
            }
            return amount;
        }
        return 0;
    }

    public int getSellOrderAmount(Double price) {
        List<Order> orders = this.sellOrders.get(price);
        if(orders != null) {
            int amount = 0;
            for (Order order: orders) {
                amount += order.getAmount();
            }
            return amount;
        }
        return 0;
    }

    public HashMap<Double, List<Order>> getBuyOrders() {
        return this.buyOrders;
    }

    public HashMap<Double, List<Order>> getSellOrders() {
        return this.sellOrders;
    }
}
