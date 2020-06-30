package bello.ishcodebellz.vma.ui;

import bello.ishcodebellz.vma.dto.VMAChange;
import bello.ishcodebellz.vma.dto.VMAFunds;
import bello.ishcodebellz.vma.dto.VMAItem;
import bello.ishcodebellz.vma.exceptions.UnknownCommandException;

import java.util.Map;

public class VMAConsoleView {
    private UserInputOutput io;

    public VMAConsoleView(UserInputOutput io) {
        this.io = io;
    }

    public void showMainMenu() {
        io.print("=== MAIN MENU ===");
        io.print("1. Insert funds");
        io.print("2. Purchase an item");
        io.print("3. Return change");
        io.print("4. Display all items");
        io.print("5. Exit");
    }

    public int getUserMenuSelection() throws UnknownCommandException {
        int menuSelection;
        try {
            do {
                menuSelection = io.readInt("Please select from the menu options: ");
            } while (!isValidMenuSelection(menuSelection));
        } catch (NumberFormatException e) {
            throw new UnknownCommandException("ERROR: Unknown command!");
        }
        return menuSelection;
    }

    private boolean isValidMenuSelection(int menuSelection) {
        if (menuSelection < 1 || (menuSelection > 5 && menuSelection != 9999)) {
            io.print("Invalid input. Please enter a number between 1 and 5.");
            return false;
        } else {
            return true;
        }
    }

    public void showInsertMoneyBanner() {
        io.print("=== INSERT FUNDS ===");
    }

    public String askUserToInsertMoney() {
        return io.readString("Enter dollar amount to be inserted: ");
    }

    public void showAvailableFunds(VMAFunds funds) {
        io.print("Inserted: $" + funds.getAmount());
    }

    public void showAvailableItems(Map<String, VMAItem> items) {
        for (String key : items.keySet()) {
            io.print(key + " | " + items.get(key).getName()
                    + " | $" + items.get(key).getPrice());
        }
    }

    public String showUserItemSelection() {
        return io.readString("Please enter item code (e.g., A1):");
    }

    public void showPurchaseSuccessMessage(VMAItem item) {
        io.print("Dispensing " + item.getName() + "...");
        io.print("Success!");
    }

    public boolean shouldReturnChange() {
        return io.readYesOrNo("Return funds?");
    }

    public void showReturnFundsBanner() {
        io.print("=== RETURN FUNDS ===");
    }

    public void showFundsReturned(VMAChange change) {
        io.print("Quarters: " + change.getQuarters());
        io.print("Dimes: " + change.getDimes());
        io.print("Nickels: " + change.getNickels());
        io.print("Pennies: " + change.getPennies());
    }

    public void userEnterToContinue() {
        io.readString("Press enter to continue.");
    }

    public void showExitMessage() {
        io.print("Seeya!");
    }

    public void showErrorMessage(String errorMessage) {
        io.print(errorMessage);
    }
}
