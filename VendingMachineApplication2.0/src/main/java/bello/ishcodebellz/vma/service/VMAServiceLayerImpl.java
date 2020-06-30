package bello.ishcodebellz.vma.service;

import bello.ishcodebellz.vma.exceptions.InvalidFundsAddedException;
import bello.ishcodebellz.vma.exceptions.NoItemInventoryException;
import bello.ishcodebellz.vma.exceptions.InsufficientFundsException;
import bello.ishcodebellz.vma.exceptions.FilePersistenceException;
import bello.ishcodebellz.vma.exceptions.NoFundsRemainException;
import bello.ishcodebellz.vma.dto.VMAChange;
import bello.ishcodebellz.vma.dto.VMAFunds;
import bello.ishcodebellz.vma.dto.VMAItem;

import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Collectors;
import bello.ishcodebellz.vma.dao.VMAAuditDao;
import bello.ishcodebellz.vma.dao.VMAMoneyDao;
import bello.ishcodebellz.vma.dao.VMAItemDao;

public class VMAServiceLayerImpl implements VMAServiceLayer {
    private VMAItemDao itemDao;
    private VMAMoneyDao moneyDao;
    private VMAAuditDao auditDao;

    public VMAServiceLayerImpl(VMAItemDao itemDao, VMAMoneyDao moneyDao, VMAAuditDao auditDao) {
        this.itemDao = itemDao;
        this.moneyDao = moneyDao;
        this.auditDao = auditDao;
    }

    private Map<String, VMAItem> itemsInStock;

    @Override
    public VMAFunds addFunds(String moneyInserted) throws InvalidFundsAddedException {
        validateFunds(moneyInserted);
        return moneyDao.addFunds(moneyInserted);
    }

    @Override
    public Map<String, VMAItem> getAvailableItems() throws FilePersistenceException {
        itemsInStock = itemDao.getAllItems()
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue().getQuantity() > 0)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return itemsInStock;
    }

    @Override
    public VMAItem purchaseItem(String itemSelection, VMAFunds funds)
            throws InsufficientFundsException, NoItemInventoryException, FilePersistenceException {
        if (isValidSelection(itemSelection) && areSufficientFunds(itemSelection, funds)) {
            VMAItem item = itemDao.dispenseItem(itemSelection);
            moneyDao.subtractFunds(item.getPrice());
            return item;
        } else {
            return null;
        }
    }

    @Override
    public VMAChange returnChange(VMAFunds funds) throws NoFundsRemainException {
        try {
            if (funds.getAmount().compareTo(BigDecimal.ZERO) == 0) {
                throw new NoFundsRemainException("ERROR: No change to return.");
            } else {
                return moneyDao.returnChange(funds);
            }
        } catch (NullPointerException e) {
            throw new NoFundsRemainException("ERROR: No change to return.");
        }
    }

    private void validateFunds(String moneyInserted) throws InvalidFundsAddedException {
        if (moneyInserted == null || "".equals(moneyInserted) || !isNumeric(moneyInserted)) {
            throw new InvalidFundsAddedException("ERROR: Please enter a valid dollar amount (e.g., 1.50).");
        }
    }

    private boolean isNumeric(String moneyInserted) {
        try {
            Double.parseDouble(moneyInserted);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private boolean isValidSelection(String itemSelection) throws NoItemInventoryException {
        boolean isValid;
        if (!itemsInStock.containsKey(itemSelection)) {
            isValid = false;
            throw new NoItemInventoryException
                    ("ERROR: Invalid item selection. Item does not exist or is out of stock.");
        } else {
            isValid = true;
        }
        return isValid;
    }

    private boolean areSufficientFunds(String itemSelection, VMAFunds funds) throws InsufficientFundsException {
        boolean isEnoughMoney;
        if (funds == null ||
                funds.getAmount().compareTo(itemsInStock.get(itemSelection).getPrice()) < 0) {
            isEnoughMoney = false;
            throw new InsufficientFundsException("ERROR: Insufficient funds.");
        } else {
            isEnoughMoney = true;
        }
        return isEnoughMoney;
    }

    public boolean isChangeNeeded(VMAFunds funds) {
        try {
            return funds.getAmount().compareTo(BigDecimal.ZERO) > 0;
        } catch (NullPointerException e) {
            return false;
        }
    }
}
