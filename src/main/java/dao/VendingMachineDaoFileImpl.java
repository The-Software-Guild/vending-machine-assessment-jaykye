package dao;

import dto.Item;

import java.awt.*;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.List;

public class VendingMachineDaoFileImpl implements VendingMachineDao{

    public final String INVENTORYFILE;
    public static final String DELIMITER = "::";
    private Map<String, Item> items = new HashMap<>();

    public VendingMachineDaoFileImpl() {
        INVENTORYFILE = "vendingmachine.txt";
    }

    public VendingMachineDaoFileImpl(String filename) {
        INVENTORYFILE = filename;
    }

    // Methods
    private Item unmarshallItem(String currentLine) {
        Item currentItem;
        String[] tokens = currentLine.split(DELIMITER);

        String name = tokens[0];
        BigDecimal price = new BigDecimal(tokens[1]);
        int inventory = Integer.parseInt(tokens[2]);
        currentItem = new Item(name, price, inventory);

        return currentItem;
    }

    @Override
    public void loadData() throws VendingMachinePersistenceException {
        Scanner scanner;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(INVENTORYFILE)));
        }
        catch (FileNotFoundException e){
            throw new VendingMachinePersistenceException("Could not find the inventory file.", e);
        }

        String currentLine;
        Item currentItem;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentItem = unmarshallItem(currentLine);
            items.put(currentItem.getName(), currentItem);
        }

        scanner.close();
    }

    private String marshallItem(Item item) {

        String itemAsString;

        itemAsString = item.getName() + DELIMITER + item.getPrice() + DELIMITER + item.getInventory();

        return itemAsString;
    }

    @Override
    public void writeData() throws VendingMachinePersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(INVENTORYFILE));
        }
        catch (IOException e){
            throw new VendingMachinePersistenceException("Could not save data to a file.", e);
        }

        String itemAsString;

        for (Item item: items.values()){
            itemAsString = marshallItem(item);
            out.println(itemAsString);
            out.flush();
        }
        out.close();
    }

    @Override
    public Item addItem(String name, Item item) {
        return items.put(name, item);
    }

    @Override
    public Item removeItem(String name) {
        return items.remove(name);
    }

    @Override
    public List<Item> getAllItems() {
        return new ArrayList<Item>(items.values());
    }

    @Override
    public Item getItem(String name) {
        return items.get(name);
    }

    @Override
    public void updateItemInventory(String name, int newInventory) {
        getItem(name).setInventory(newInventory);
    }
}
