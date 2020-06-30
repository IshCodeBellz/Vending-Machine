package bello.ishcodebellz.vma.dao;

import bello.ishcodebellz.vma.dto.VMAItem;
import bello.ishcodebellz.vma.exceptions.FilePersistenceException;

import java.util.HashMap;
import java.util.Map;

public class VMAItemDaoStubImpl implements VMAItemDao {

    private Map<String, VMAItem> testItems = new HashMap<>();

    public VMAItemDaoStubImpl() {
        VMAItem availableItem = new VMAItem("Chips","1.00", "1");
        testItems.put("T1", availableItem);

        VMAItem unavailableItem = new VMAItem("Candy", "1.00", "0");
        testItems.put("T0", unavailableItem);
    }

    @Override
    public Map<String, VMAItem> getAllItems() throws FilePersistenceException {
        return testItems;
    }

    @Override
    public VMAItem dispenseItem(String itemSelection) throws FilePersistenceException {
        return testItems.get(itemSelection);
    }
}
