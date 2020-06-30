package bello.ishcodebellz.vma.controller;

import bello.ishcodebellz.vma.exceptions.UnknownCommandException;
import bello.ishcodebellz.vma.exceptions.InvalidFundsAddedException;
import bello.ishcodebellz.vma.exceptions.InsufficientFundsException;
import bello.ishcodebellz.vma.exceptions.NoItemInventoryException;
import bello.ishcodebellz.vma.exceptions.FilePersistenceException;
import bello.ishcodebellz.vma.exceptions.NoFundsRemainException;
import bello.ishcodebellz.vma.dto.VMAChange;
import bello.ishcodebellz.vma.dto.VMAFunds;
import bello.ishcodebellz.vma.dto.VMAItem;
import bello.ishcodebellz.vma.ui.VMAConsoleView;

import java.util.HashMap;
import java.util.Map;
import bello.ishcodebellz.vma.service.VMAServiceLayer;

public class VMAController {
    
    // controller is self explained methods named and called for functionality
    private VMAConsoleView view;
    private VMAServiceLayer service;
    private VMAFunds funds;

    public VMAController(VMAConsoleView view, VMAServiceLayer service) {
        this.view = view;
        this.service = service;
    }

    public void runVendingMachineApplication() {
        showAvailableItems();
        boolean run = true;
        while (run) {
            showMainMenu();
            int menuSelection = showMenuSelection();
            switch (menuSelection) {
                case 1:
                    addFunds();
                    break; 
                case 2:
                   purchaseItem();
                    if (view.shouldReturnChange()) {
                        returnFunds();
                    }
                    break;
                case 3:
                    returnFunds();
                    break;
                case 4:
                    showAvailableItems();
                    break;
                case 5:
                    returnFundsIfFundsRemain();
                    showExitMessage();
                    run = false;
                    break;
            }
        }
    }

    private void showMainMenu() {
        view.showMainMenu();
    }

    private int showMenuSelection() {
        boolean isValidSelection;
        int userMenuSelection = -1;
        do {
            try {
                userMenuSelection = view.getUserMenuSelection();
                isValidSelection = true;
            } catch (UnknownCommandException e) {
                isValidSelection = false;
                view.showErrorMessage(e.getMessage());
            }
        } while (!isValidSelection);
        return userMenuSelection;
    }

    private void showAvailableItems() {
        Map<String, VMAItem> itemsInStock = new HashMap<>();
        try {
            itemsInStock = service.getAvailableItems();
        } catch (FilePersistenceException e) {
            view.showErrorMessage(e.getMessage());
        }
        view.showAvailableItems(itemsInStock);
        view.userEnterToContinue();
    }

    private void addFunds() {
        view.showInsertMoneyBanner();
        boolean hasError;
        do {
            String moneyInserted = view.askUserToInsertMoney();
            try {
                funds = service.addFunds(moneyInserted);
                hasError = false;
            } catch (InvalidFundsAddedException e) {
                hasError = true;
                view.showErrorMessage(e.getMessage());
            }
        } while (hasError);
        view.showAvailableFunds(funds);
        view.userEnterToContinue();
    }

    private void purchaseItem() {
        String itemSelection = view.showUserItemSelection();
        try {
            VMAItem item = service.purchaseItem(itemSelection, funds);
            view.showPurchaseSuccessMessage(item);
        } catch (NoItemInventoryException
                | InsufficientFundsException
                | FilePersistenceException e) {
            view.showErrorMessage(e.getMessage());
        }
    }

    private void returnFundsIfFundsRemain() {
        if (service.isChangeNeeded(funds)) {
            returnFunds();
        }
    }

    private void returnFunds() {
        view.showReturnFundsBanner();
        try {
            VMAChange change = service.returnChange(funds);
            view.showFundsReturned(change);
        } catch (NoFundsRemainException e) {
            view.showErrorMessage(e.getMessage());
        }
        view.userEnterToContinue();
    }

    private void showExitMessage() {
        view.showExitMessage();
    }
}