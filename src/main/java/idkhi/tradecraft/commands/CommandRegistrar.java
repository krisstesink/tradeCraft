package idkhi.tradecraft.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import idkhi.tradecraft.order.Order;
import idkhi.tradecraft.order.OrderManager;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class CommandRegistrar {
    private static OrderManager orderManager = new OrderManager();

    public static void registerCommands() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            registerMarketCommands(dispatcher);
        });
    }

    private static void registerMarketCommands(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("buy")
                .then(literal("market")
                        .then(argument("item", StringArgumentType.word())
                                .then(argument("amount", IntegerArgumentType.integer(1))
                                        .executes(context -> {
                                            try {
                                                String item = StringArgumentType.getString(context, "item");
                                                int amount = IntegerArgumentType.getInteger(context, "amount");
                                                orderManager.placeOrder(item, amount, 0.0, Order.OrderType.BUY_MARKET);
                                                context.getSource().sendFeedback(() -> Text.of("Placed market buy order for " + amount + " " + item), false);
                                            } catch (Exception e) {
                                                context.getSource().sendError(Text.of("Error: " + e.getMessage()));
                                                e.printStackTrace();
                                            }
                                            return 1;
                                        })
                                )
                        )
                )
                .then(literal("limit")
                        .then(argument("item", StringArgumentType.word())
                                .then(argument("limit", DoubleArgumentType.doubleArg(0.0))
                                        .then(argument("amount", IntegerArgumentType.integer(1))
                                                .executes(context -> {
                                                    try {
                                                        String item = StringArgumentType.getString(context, "item");
                                                        double limit = DoubleArgumentType.getDouble(context, "limit");
                                                        int amount = IntegerArgumentType.getInteger(context, "amount");
                                                        orderManager.placeOrder(item, amount, limit, Order.OrderType.BUY_LIMIT);
                                                        context.getSource().sendFeedback(() -> Text.of("Placed limit buy order for " + amount + " " + item + " at " + limit), false);
                                                    } catch (Exception e) {
                                                        context.getSource().sendError(Text.of("Error: " + e.getMessage()));
                                                        e.printStackTrace();
                                                    }
                                                    return 1;
                                                })
                                        )
                                )
                        )
                )
        );

        dispatcher.register(literal("sell")
                .then(literal("market")
                        .then(argument("item", StringArgumentType.word())
                                .then(argument("amount", IntegerArgumentType.integer(1))
                                        .executes(context -> {
                                            try {
                                                String item = StringArgumentType.getString(context, "item");
                                                int amount = IntegerArgumentType.getInteger(context, "amount");
                                                orderManager.placeOrder(item, amount, 0.0, Order.OrderType.SELL_MARKET);
                                                context.getSource().sendFeedback(() -> Text.of("Placed market sell order for " + amount + " " + item), false);
                                            } catch (Exception e) {
                                                context.getSource().sendError(Text.of("Error: " + e.getMessage()));
                                                e.printStackTrace();
                                            }
                                            return 1;
                                        })
                                )
                        )
                )
                .then(literal("limit")
                        .then(argument("item", StringArgumentType.word())
                                .then(argument("limit", DoubleArgumentType.doubleArg(0.0))
                                        .then(argument("amount", IntegerArgumentType.integer(1))
                                                .executes(context -> {
                                                    try {
                                                        String item = StringArgumentType.getString(context, "item");
                                                        double limit = DoubleArgumentType.getDouble(context, "limit");
                                                        int amount = IntegerArgumentType.getInteger(context, "amount");
                                                        orderManager.placeOrder(item, amount, limit, Order.OrderType.SELL_LIMIT);
                                                        context.getSource().sendFeedback(() -> Text.of("Placed limit sell order for " + amount + " " + item + " at " + limit), false);
                                                    } catch (Exception e) {
                                                        context.getSource().sendError(Text.of("Error: " + e.getMessage()));
                                                        e.printStackTrace();
                                                    }
                                                    return 1;
                                                })
                                        )
                                )
                        )
                )
        );
    }
}
