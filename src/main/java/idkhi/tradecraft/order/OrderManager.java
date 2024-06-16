package idkhi.tradecraft.order;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.min;


public class OrderManager {

    private Map<String, OrderBook> orderBooks;

    public OrderManager() {
        this.orderBooks = new HashMap<>();
    }

    public void placeOrder(String item, int amount, double price, Order.OrderType type) {
        Order order = new Order(item, amount, price, type);
        orderBooks.computeIfAbsent(item, k -> new OrderBook()).addOrder(order);
        matchOrders(item);
    }

    public void cancelOrder(String item, Order order) {
        OrderBook orderBook = orderBooks.get(item);
        if (orderBook != null) {
            orderBook.cancelOrder(order);
        }
    }

    private void matchOrders(String item) {
        // Implement matching logic here
        OrderBook orderBook = orderBooks.get(item);
        if (orderBook != null) {
            double bestBid = orderBook.getBestBid();
            double bestAsk = orderBook.getBestAsk();

            // Simple matching logic for demonstration purposes
            if (bestBid >= bestAsk) {
                // Match orders at the best bid/ask price
                // For simplicity, assume we are matching the first orders at these prices
                List<Order> buyOrdersAtBestBid = orderBook.getBuyOrders().get(bestBid);
                List<Order> sellOrdersAtBestAsk = orderBook.getSellOrders().get(bestAsk);

                if (buyOrdersAtBestBid != null && sellOrdersAtBestAsk != null) {
                    int buyOrderAmount = orderBook.getBuyOrderAmount(bestBid);
                    int sellOrderAmount = orderBook.getSellOrderAmount(bestAsk);

                    int tradeAmount = min(buyOrderAmount, sellOrderAmount);
                    double tradePrice = (bestAsk + bestBid)/2;


                    // Here, you would perform the actual transaction, adjust quantities, etc.
                    // For now, we'll just print a message
                    System.out.println("Matching order for " + item + ": amount = "  + tradeAmount + " price = " + tradePrice);
                    while(tradeAmount > 0 ) {
                        Order buyOrder = buyOrdersAtBestBid.get(0);
                        int buyAmount = min(tradeAmount, buyOrder.getAmount());

                        Order sellOrder = sellOrdersAtBestAsk.get(0);
                        int sellAmount = min(tradeAmount, sellOrder.getAmount());

                        if(buyAmount>sellAmount) {
                            buyOrdersAtBestBid.get(0).modifyAmount(buyOrder.getAmount()-sellAmount);
                            sellOrdersAtBestAsk.get(0).modifyAmount(sellOrder.getAmount()-sellAmount);
                        } else  {
                            buyOrdersAtBestBid.get(0).modifyAmount(buyOrder.getAmount()-buyAmount);
                            sellOrdersAtBestAsk.get(0).modifyAmount(sellOrder.getAmount()-buyAmount);
                        }

                        if(buyOrdersAtBestBid.get(0).getAmount() == 0) {
                            buyOrdersAtBestBid.remove(0);
                        }
                        if(sellOrdersAtBestAsk.get(0).getAmount() == 0) {
                            sellOrdersAtBestAsk.remove(0);
                        }

                        tradeAmount -= min(sellAmount, buyAmount);
                    }

                    if (buyOrdersAtBestBid.isEmpty()) {
                        orderBook.getBuyOrders().remove(bestBid);
                    }
                    if (sellOrdersAtBestAsk.isEmpty()) {
                        orderBook.getSellOrders().remove(bestAsk);
                    }
                    // recursively call the method until all orders are matched.
                    matchOrders(item);
                }
            }
        }
    }
}
