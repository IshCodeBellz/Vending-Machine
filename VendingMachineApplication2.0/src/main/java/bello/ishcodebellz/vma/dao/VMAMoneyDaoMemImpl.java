package bello.ishcodebellz.vma.dao;

import bello.ishcodebellz.vma.dto.VMAChange;
import bello.ishcodebellz.vma.dto.VMAFunds;

import java.math.BigDecimal;

public class VMAMoneyDaoMemImpl implements VMAMoneyDao {
    private VMAFunds funds = new VMAFunds("0");

    @Override
    public VMAFunds addFunds(String moneyInserted) {
        funds.addAmount(moneyInserted);
        return funds;
    }

    @Override
    public VMAFunds subtractFunds(BigDecimal itemPrice) {
        funds.setAmount(funds.getAmount().subtract(itemPrice));
        return funds;
    }

    @Override
    public VMAChange returnChange(VMAFunds funds) {
        VMAChange change = new VMAChange(funds.getAmount());
        funds.setAmount(BigDecimal.ZERO);
        return change;
    }
}
