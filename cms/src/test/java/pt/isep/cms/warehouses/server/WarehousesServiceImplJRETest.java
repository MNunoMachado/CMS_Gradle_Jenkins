package pt.isep.cms.warehouses.server;

import junit.framework.TestCase;
import pt.isep.cms.warehouses.shared.Warehouse;
import pt.isep.cms.warehouses.shared.WarehouseDetails;

import java.util.ArrayList;


public class WarehousesServiceImplJRETest extends TestCase {

    WarehousesServiceImpl service = new WarehousesServiceImpl();


    public void testGetWarehouseDetails() {
        WarehouseDetails HollDetails = new WarehouseDetails("0", "Armazem Holl");

        ArrayList<WarehouseDetails> warehouseDetailsList = service.getWarehouseDetails();

        assertEquals(warehouseDetailsList.get(0).getDisplayName(), HollDetails.getDisplayName());
        assertEquals(warehouseDetailsList.size(), 4);

    }


    public void testGetWarehouse() {
        Warehouse warehouse = service.getWarehouse("0");

        assertTrue(warehouse.name.equals("Armazem Holl"));
        assertTrue(warehouse.totalCapacity.equals("12"));
        assertTrue(warehouse.location.equals("Porto"));
    }
}