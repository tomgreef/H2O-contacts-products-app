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
public class ProductContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<ProductItem> ITEMS = new ArrayList<ProductItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, ProductItem> ITEM_MAP = new HashMap<String, ProductItem>();

    private static final int COUNT = 5;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    public static void addItem(ProductItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static ProductItem createDummyItem(int position) {
        return new ProductItem(String.valueOf(position), "Item " + position, makeDetails(position));
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
    public static class ProductItem {
        public final String id;
        public final String nombre;
        public final String precio;

        public ProductItem(String id, String content, String details) {
            this.id = id;
            this.nombre = content;
            this.precio = details;
        }

        @Override
        public String toString() {
            return nombre;
        }
    }
}