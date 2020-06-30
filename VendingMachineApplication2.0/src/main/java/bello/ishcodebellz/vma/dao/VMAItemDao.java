package bello.ishcodebellz.vma.dao;

import bello.ishcodebellz.vma.dto.VMAItem;
import bello.ishcodebellz.vma.exceptions.FilePersistenceException;

import java.util.Map;

public interface VMAItemDao {
    Map<String, VMAItem> getAllItems() throws FilePersistenceException;
    VMAItem dispenseItem(String itemSelection) throws FilePersistenceException;
}
