import java.io.*;
import java.util.ArrayList;

public class FileHandler {

    public static void saveStock(ArrayList<Item> items, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Item item : items) {
                writer.write(item.getName() + "," + item.getQuantity());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Save error: " + e.getMessage());
        }
    }

    public static ArrayList<Item> loadStock(String filename) {
        ArrayList<Item> items = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                int quantity = Integer.parseInt(parts[1]);
                items.add(new Item(name, quantity));
            }
        } catch (IOException e) {
            System.out.println("Load error: " + e.getMessage());
        }
        return items;
    }
}
