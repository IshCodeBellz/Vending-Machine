package bello.ishcodebellz.vma.dao;

import bello.ishcodebellz.vma.dto.VMAItem;
import bello.ishcodebellz.vma.exceptions.FilePersistenceException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class VMAItemDaoFileImpl implements VMAItemDao {
    private static final String INVENTORY_FILE = "VMAiteminventory.txt";
    private static final String DELIMITER = ":;:";

    private Map<String, VMAItem> items = new HashMap<>();

    @Override
    public Map<String, VMAItem> getAllItems() throws FilePersistenceException {
        loadInventory();
        return items;
    }

    @Override
    public VMAItem dispenseItem(String itemSelection) throws FilePersistenceException {
        VMAItem item = items.get(itemSelection);
        item.setQuantity(item.getQuantity() - 1);
        writeInventory();
        return item;
    }

    private void loadInventory() throws FilePersistenceException {
        Scanner scanner;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(INVENTORY_FILE)));
        } catch (FileNotFoundException e) {
            throw new FilePersistenceException("ERROR: Could not load item inventory into memory.", e);
        }
        String currentLine;
        String[] itemAttributes;
        char keyChar = 'A';
        int keyInt = 1;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            itemAttributes = currentLine.split(DELIMITER);
            VMAItem item = new VMAItem(itemAttributes[0], itemAttributes[1],itemAttributes[2]);
            items.put(Character.toString(keyChar) + keyInt, item);
            keyInt++;
            if (keyInt > 9) {
                keyInt = 1;
                keyChar++;
            }
        }
        scanner.close();
    }

    private void writeInventory() throws FilePersistenceException {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(INVENTORY_FILE));
        } catch (IOException e) {
            throw new FilePersistenceException("Could not save item inventory.", e);
        }

        for (VMAItem item : items.values()) {
            out.println(item.getName() + DELIMITER
                    + item.getPrice() + DELIMITER
                    + item.getQuantity());
            out.flush();
        }
        out.close();
    }
}
