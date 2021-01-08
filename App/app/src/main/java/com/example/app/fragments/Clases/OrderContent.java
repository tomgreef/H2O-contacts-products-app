package com.example.app.fragments.Clases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class OrderContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<OrderItem> ITEMS = new ArrayList<OrderItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, OrderItem> ITEM_MAP = new HashMap<String, OrderItem>();

    private static final int COUNT = 7;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(new OrderItem("" + i, "1" + (i+2) + "/01/2021"));
        }
    }

    public static void addItem(OrderItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static OrderItem createDummyItem(int position) {
        return new OrderItem(String.valueOf(position), "Item " + position);
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class OrderItem {
        public final String id;
        public final String fecha;

        public OrderItem(String id, String content) {
            this.id = id;
            this.fecha = content;
        }

        @Override
        public String toString() {
            return fecha;
        }
    }
}