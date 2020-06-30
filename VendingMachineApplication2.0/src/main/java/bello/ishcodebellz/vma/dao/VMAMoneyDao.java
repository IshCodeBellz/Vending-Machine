package bello.ishcodebellz.vma.dao;

import bello.ishcodebellz.vma.dto.VMAChange;
import bello.ishcodebellz.vma.dto.VMAFunds;

import java.math.BigDecimal;

public interface VMAMoneyDao {
    VMAFunds addFunds(String moneyInserted);
    VMAFunds subtractFunds(BigDecimal itemPrice);
    VMAChange returnChange(VMAFunds funds);
}
