package bello.ishcodebellz.vma.dto;

import java.math.BigDecimal;

public class VMAChange {
    public enum Coins {
        // assigns value to coin
        QUARTER(25),
        DIME(10),
        NICKEL(5);
        // need to declare value
        private int coinType;
        Coins (int coinType){
            this.coinType = coinType;
        }
        
        // returns the value of the coin from the hashmap
        // when passing in the coin as a parameter
        public int getNumberOfCoins(int pennies) {
            return pennies / this.coinType;
        }
        
        public int pennyLeft(int pennies) {
            return pennies % this.coinType;
        }
    }
    private int quarters;
    private int dimes;
    private int nickels;
    private int pennies;

    public VMAChange(BigDecimal funds) {
        this.pennies = funds.multiply(BigDecimal.valueOf(100)).intValue();
        this.quarters = Coins.QUARTER.getNumberOfCoins(this.pennies);
        this.pennies = Coins.QUARTER.pennyLeft(this.pennies);
        this.dimes = Coins.DIME.getNumberOfCoins(this.pennies);
        this.pennies = Coins.DIME.pennyLeft(this.pennies);
        this.nickels = Coins.NICKEL.getNumberOfCoins(this.pennies);
        this.pennies = Coins.NICKEL.pennyLeft(this.pennies);
//        this.quarters = pennies / 25;
//        this.pennies = pennies % 25;
//        this.dimes = pennies / 10;
//        this.pennies = pennies % 10;
//        this.nickels = pennies / 5;
//        this.pennies = pennies % 5;
    }

    public int getQuarters() {
        return quarters;
    }

    public int getDimes() {
        return dimes;
    }

    public int getNickels() {
        return nickels;
    }

    public int getPennies() {
        return pennies;
    }

    @Override
    public String toString() {
        return " |Quarters: " + quarters + " |Dimes: " + dimes + " |Nickels: "
                + nickels + " |Pennies: " + pennies;
    }
}
