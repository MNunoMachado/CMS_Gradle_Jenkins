package pt.isep.cms.warehouses.shared;

import junit.framework.TestCase;


public class WarehouseJRETest extends TestCase {

    Warehouse baseWarehouse;
    Warehouse secondWarehouse;

    protected void setUp() {
        baseWarehouse = new Warehouse("1", "Base", "12", "Porto");
        secondWarehouse = new Warehouse();
    }

    public void testGetLightWeightWarehouse() {
        WarehouseDetails details = baseWarehouse.getLightWeightWarehouse();
        String expectedResult = "Base";
        assertEquals(expectedResult, details.getDisplayName());

    }

    public void testGetId() {
        String wareHouseId = baseWarehouse.getId();
        String expectedResult = "1";
        assertEquals(expectedResult, wareHouseId);
    }

    public void testSetId() {
        baseWarehouse.setId("2");
        String wareHouseId = baseWarehouse.getId();
        String expectedResult = "2";
        assertEquals(expectedResult, wareHouseId);
    }


    public void testGetName() {
        String wareHouseName = baseWarehouse.getName();
        String expectedResult = "Base";
        assertEquals(expectedResult, wareHouseName);
    }

    public void testSetName() {
        baseWarehouse.setName("Boavista Arm");
        String wareHouseId = baseWarehouse.getName();
        String expectedResult = "Boavista Arm";
        assertEquals(expectedResult, wareHouseId);
    }


    public void testGetTotalCapacity() {
        String wareHouseTotalCapacity = baseWarehouse.getTotalCapacity();
        String expectedResult = "12";
        assertEquals(expectedResult, wareHouseTotalCapacity);
    }

    public void testSetTotalCapacity() {
        secondWarehouse.setTotalCapacity("24");
        String wareHouseTotalCapacity = secondWarehouse.getTotalCapacity();
        String expectedResult = "24";
        assertEquals(expectedResult, wareHouseTotalCapacity);
    }


    public void testGetLocation() {
        String wareHouseLocation = baseWarehouse.getLocation();
        String expectedResult = "Porto";
        assertEquals(expectedResult, wareHouseLocation);
    }


    public void testSetLocation() {
        baseWarehouse.setLocation("Gaia");
        String wareHouseLocation = baseWarehouse.getLocation();
        String expectedResult = "Gaia";
        assertEquals(expectedResult, wareHouseLocation);
    }


    public void testGetFullName() {
        String warehouseFullName = baseWarehouse.getFullName();
        String expectedResult = "Base";
        assertEquals(expectedResult, warehouseFullName);
    }
}