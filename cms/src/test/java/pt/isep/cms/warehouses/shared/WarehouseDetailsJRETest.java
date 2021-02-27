package pt.isep.cms.warehouses.shared;

import junit.framework.TestCase;


public class WarehouseDetailsJRETest extends TestCase {

    WarehouseDetails warehouseDetails = new WarehouseDetails();

    WarehouseDetails secondWarehouseDetails = new WarehouseDetails("1", "New Warehouse");


    public void testGetId() {
        String wareHouseId = secondWarehouseDetails.getId();
        String expectedResult = "1";
        assertEquals(expectedResult, wareHouseId);
    }

    public void testSetId() {
        secondWarehouseDetails.setId("3");
        String wareHouseId = secondWarehouseDetails.getId();
        String expectedResult = "3";
        assertEquals(expectedResult, wareHouseId);
    }

    public void testGetDisplayName() {
        String wareHouseName = secondWarehouseDetails.getDisplayName();
        String expectedResult = "New Warehouse";
        assertEquals(expectedResult, wareHouseName);
    }

    public void testSetDisplayName() {
        warehouseDetails.setDisplayName("Base warehouse");
        String wareHouseName = warehouseDetails.getDisplayName();
        String expectedResult = "Base warehouse";
        assertEquals(expectedResult, wareHouseName);
    }
}