import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        InventoryManager manager = new InventoryManager();
        manager.getStockItems().addAll(FileHandler.loadStock("stock.txt"));

        boolean running = true;
        while (running) {
            System.out.println("\n=== Inventory Menu ===");
            System.out.println("1. View Stock");
            System.out.println("2. Add Item");
            System.out.println("3. Sell Item");
            System.out.println("4. View Sold Out");
            System.out.println("5. Save & Exit");
            int choice = getIntInput("Choose an option: ");

            switch (choice) {
                case 1 -> {
                    System.out.println("\n--- In Stock ---");
                    for (Item item : manager.getStockItems()) {
                        System.out.println(item);
                    }
                }
                case 2 -> {
                    System.out.print("Item name: ");
                    String name = scanner.nextLine();
                    int qty = getIntInput("Quantity: ");
                    manager.addItem(name, qty);
                }
                case 3 -> {
                    System.out.print("Item name to sell: ");
                    String name = scanner.nextLine();
                    int qty = getIntInput("Quantity to sell: ");
                    manager.sellItem(name, qty);
                }
                case 4 -> {
                    System.out.println("\n--- Sold Out Items ---");
                    for (Item item : manager.getSoldOutItems()) {
                        if (item != null) System.out.println(item);
                    }
                }
                case 5 -> {
                    FileHandler.saveStock(manager.getStockItems(), "stock.txt");
                    System.out.println("Data saved. Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    
    public static int getIntInput(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number, try again.");
            return getIntInput(prompt);
        }
    }
}

