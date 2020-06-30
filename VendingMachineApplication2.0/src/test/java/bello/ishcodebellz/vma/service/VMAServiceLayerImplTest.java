package bello.ishcodebellz.vma.service;

import bello.ishcodebellz.vma.exceptions.InsufficientFundsException;
import bello.ishcodebellz.vma.exceptions.NoItemInventoryException;
import bello.ishcodebellz.vma.exceptions.NoFundsRemainException;
import bello.ishcodebellz.vma.exceptions.InvalidFundsAddedException;
import bello.ishcodebellz.vma.dto.VMAFunds;
import bello.ishcodebellz.vma.dto.VMAItem;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

import static org.junit.Assert.*;

public class VMAServiceLayerImplTest {

    private VMAServiceLayer service;

    public VMAServiceLayerImplTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        service = ctx.getBean("service", VMAServiceLayer.class);
    }

    @Test (expected = InvalidFundsAddedException.class)
    public void testAddFundsNull() throws Exception {
        service.addFunds(null);
    }

    @Test (expected = InvalidFundsAddedException.class)
    public void testAddFundsEmptyString() throws Exception {
        service.addFunds("");
    }

    @Test (expected = InvalidFundsAddedException.class)
    public void testAddFundsNotNumber() throws Exception {
        service.addFunds("string");
    }

    @Test
    public void testGetAvailableItemsReturnsAvailableItem() throws Exception {
        Map<String, VMAItem> availableItems = service.getAvailableItems();
        assertTrue(availableItems.containsKey("T1"));
    }

    @Test
    public void testGetAvailableItemsDoesNotReturnUnavailableItem() throws Exception {
        Map<String, VMAItem> availableItems = service.getAvailableItems();
        assertFalse(availableItems.containsKey("T0"));
    }

    @Test
    public void testPurchaseAvailableItemSufficientFunds() throws Exception {
        service.getAvailableItems();
        VMAItem item = service.purchaseItem("T1", new VMAFunds("1000"));
        assertTrue(item.getName().equals("Chips"));
    }

    @Test (expected = NoItemInventoryException.class) // exception expected because the item is not in our filtered map
    public void testPurchaseUnavailableItemSufficientFunds() throws Exception {
        service.getAvailableItems();
        service.purchaseItem("T0", new VMAFunds("1000"));
    }

    @Test (expected = InsufficientFundsException.class)
    public void testPurchaseAvailableItemInsufficientFunds() throws Exception {
        service.getAvailableItems();
        service.purchaseItem("T1", new VMAFunds("0"));
    }

    @Test (expected = NoItemInventoryException.class)
    public void testPurchaseUnavailableItemInsufficientFunds() throws Exception {
        service.getAvailableItems();
        service.purchaseItem("T0", new VMAFunds("0"));
    }

    @Test (expected = InsufficientFundsException.class)
    public void testPurchaseAvailableItemNullFunds() throws Exception {
        service.getAvailableItems();
        service.purchaseItem("T1", null);
    }

    @Test (expected = NoItemInventoryException.class)
    public void testPurchaseUnavailableItemNullFunds() throws Exception {
        service.getAvailableItems();
        service.purchaseItem("T0", null);
    }

    @Test
    public void testChangeNeeded() {
        VMAFunds oneCent = new VMAFunds("0.01");
        assertTrue(service.isChangeNeeded(oneCent));
    }

    @Test
    public void testNullFundsChangeNotNeeded() {
        assertFalse(service.isChangeNeeded(null));
    }

    @Test
    public void testNoFundsChangeNotNeeded() {
        VMAFunds noFunds = new VMAFunds("0");
        assertFalse(service.isChangeNeeded(noFunds));
    }

    @Test (expected = NoFundsRemainException.class)
    public void testReturnNullChange() throws Exception {
        service.returnChange(null);
    }

    @Test (expected = NoFundsRemainException.class)
    public void testReturnNoChange() throws Exception {
        VMAFunds zeroFunds = new VMAFunds("0");
        service.returnChange(zeroFunds);
    }
}