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
public class ClientContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Client> ITEMS = new ArrayList<Client>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, Client> ITEM_MAP = new HashMap<String, Client>();

    private static final int COUNT = 50;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            //addItem(createDummyItem(i));
            //addItem(new DummyItem(""+ i, "juan", "51515151"));
        }
    }

    public static void addItem(Client item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static Client createDummyItem(int position) {
        return new Client(String.valueOf(position), "Item " + position, makeDetails(position));
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
    public static class Client {
        public final String id;
        public final String name;
        public final String phone;

        public Client(String id, String name, String phone) {
            this.id = id;
            this.name = name;
            this.phone = phone;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}