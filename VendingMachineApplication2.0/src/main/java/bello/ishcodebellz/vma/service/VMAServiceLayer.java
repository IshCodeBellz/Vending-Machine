package bello.ishcodebellz.vma.service;

import bello.ishcodebellz.vma.exceptions.InvalidFundsAddedException;
import bello.ishcodebellz.vma.exceptions.InsufficientFundsException;
import bello.ishcodebellz.vma.exceptions.NoItemInventoryException;
import bello.ishcodebellz.vma.exceptions.FilePersistenceException;
import bello.ishcodebellz.vma.exceptions.NoFundsRemainException;
import bello.ishcodebellz.vma.dto.VMAChange;
import bello.ishcodebellz.vma.dto.VMAFunds;
import bello.ishcodebellz.vma.dto.VMAItem;

import java.util.Map;

public interface VMAServiceLayer {
    VMAFunds addFunds(String moneyInserted) throws InvalidFundsAddedException;
    Map<String, VMAItem> getAvailableItems() throws FilePersistenceException;
    VMAItem purchaseItem(String itemSelection, VMAFunds funds)
            throws NoItemInventoryException, InsufficientFundsException, FilePersistenceException;
    boolean isChangeNeeded(VMAFunds funds);
    VMAChange returnChange(VMAFunds funds) throws NoFundsRemainException;
}
