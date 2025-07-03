import java.util.ArrayList;

public class InventoryManager {
    private ArrayList<Item> stockItems = new ArrayList<>();
    private Item[] soldOutItems = new Item[100];
    private int soldOutIndex = 0;

    public ArrayList<Item> getStockItems() {
        return stockItems;
    }

    public Item[] getSoldOutItems() {
        return soldOutItems;
    }

    public void addItem(String name, int quantity) {
        for (Item item : stockItems) {
            if (item.getName().equalsIgnoreCase(name)) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        stockItems.add(new Item(name, quantity));
    }

    public void sellItem(String name, int quantity) {
        for (int i = 0; i < stockItems.size(); i++) {
            Item item = stockItems.get(i);
            if (item.getName().equalsIgnoreCase(name)) {
                int currentQty = item.getQuantity();
                if (currentQty >= quantity) {
                    item.setQuantity(currentQty - quantity);
                    if (item.getQuantity() == 0) {
                        stockItems.remove(i);
                        if (soldOutIndex < soldOutItems.length) {
                            soldOutItems[soldOutIndex++] = item;
                        }
                    }
                }
                return;
            }
        }
        System.out.println("Item not found or insufficient quantity.");
    }
}
