package bello.ishcodebellz.vma.dao;

import bello.ishcodebellz.vma.dto.VMAChange;
import bello.ishcodebellz.vma.dto.VMAFunds;

import java.math.BigDecimal;

public class VMAMoneyDaoStubImpl implements VMAMoneyDao {
    @Override
    public VMAFunds addFunds(String moneyInserted) {
        return null;
    }

    @Override
    public VMAFunds subtractFunds(BigDecimal itemPrice) {
        return null;
    }

    @Override
    public VMAChange returnChange(VMAFunds funds) {
        return null;
    }
}
