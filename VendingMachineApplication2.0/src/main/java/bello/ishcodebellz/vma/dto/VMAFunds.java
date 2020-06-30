package bello.ishcodebellz.vma.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class VMAFunds {
    private BigDecimal amount;

    public VMAFunds(String amountStr) {
        this.amount = new BigDecimal(amountStr).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void addAmount(String amountStr) {
        BigDecimal additionalAmount = new BigDecimal(amountStr).setScale(2, RoundingMode.HALF_UP);
        this.amount = this.amount.add(additionalAmount);
    }

    @Override
    public String toString() {
        return " |Funds available: $" + amount;
    }
}