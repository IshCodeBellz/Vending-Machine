package bello.ishcodebellz.vma.dao;

import bello.ishcodebellz.vma.dto.VMAChange;
import bello.ishcodebellz.vma.dto.VMAFunds;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class VMAMoneyDaoMemImplTest {
    private VMAMoneyDaoMemImpl dao = new VMAMoneyDaoMemImpl();

    @Test
    public void testAddSevenDollars() {
        assertEquals(new VMAFunds("9").getAmount(), dao.addFunds("9").getAmount());
    }

    @Test
    public void testSubtractSevenDollars() {
        assertEquals(new VMAFunds("-11").getAmount(), dao.subtractFunds(new BigDecimal("11")).getAmount());
    }

    @Test
    public void testReturnChangeFortyFourCents() {
        VMAChange change = dao.returnChange(new VMAFunds("0.44"));
        assertEquals(1, change.getQuarters());
        assertEquals(1, change.getDimes());
        assertEquals(1, change.getNickels());
        assertEquals(4, change.getPennies());
    }
}