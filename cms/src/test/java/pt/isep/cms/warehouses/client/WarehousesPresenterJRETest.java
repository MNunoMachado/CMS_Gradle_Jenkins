package pt.isep.cms.warehouses.client;

import com.google.gwt.event.shared.HandlerManager;
import junit.framework.TestCase;
import pt.isep.cms.warehouses.client.presenter.WarehousesPresenter;
import pt.isep.cms.warehouses.shared.WarehouseDetails;

import java.util.ArrayList;

import static org.easymock.EasyMock.createStrictMock;


public class WarehousesPresenterJRETest extends TestCase {
    private WarehousesPresenter warehousesPresenter;
    private WarehousesServiceAsync mockRpcService;
    private HandlerManager eventBus;
    private WarehousesPresenter.Display mockDisplay;
    ArrayList<WarehouseDetails> warehouseDetails;

    protected void setUp() {
        mockRpcService = createStrictMock(WarehousesServiceAsync.class);
        eventBus = new HandlerManager(null);
        mockDisplay = createStrictMock(WarehousesPresenter.Display.class);
        warehousesPresenter = new WarehousesPresenter(mockRpcService, eventBus, mockDisplay);

        warehouseDetails = new ArrayList<WarehouseDetails>();
        warehouseDetails.add(new WarehouseDetails("0", "WH X1"));
        warehouseDetails.add(new WarehouseDetails("2", "WH X3"));
        warehouseDetails.add(new WarehouseDetails("1", "WH X2"));
        warehousesPresenter.setWarehouseDetails(warehouseDetails);

    }

    public void testSortWarehouseDetails() {
        warehousesPresenter.sortWarehouseDetails();

        assertTrue(warehousesPresenter.getWarehouseDetail(0).getDisplayName().equals("WH X1"));
        assertTrue(warehousesPresenter.getWarehouseDetail(1).getDisplayName().equals("WH X2"));
        assertTrue(warehousesPresenter.getWarehouseDetail(2).getDisplayName().equals("WH X3"));
    }

    public void testGetWarehouseDetail() {
        warehousesPresenter.sortWarehouseDetails();
        WarehouseDetails warehouseDetails = warehousesPresenter.getWarehouseDetail(1);
        String expectedResult = "WH X2";
        assertEquals(expectedResult, warehouseDetails.getDisplayName());
    }

    public void testSetWarehouseDetail() {
        ArrayList<WarehouseDetails> newWHDetailsList = new ArrayList<>();
        newWHDetailsList = warehouseDetails;
        newWHDetailsList.get(1).setDisplayName("NEW NAME");
        warehousesPresenter.setWarehouseDetails(newWHDetailsList);
         String expectedResult = "NEW NAME";
        assertEquals(expectedResult, warehousesPresenter.getWarehouseDetail(1).getDisplayName());
    }

}