package pt.isep.cms.warehouses.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import pt.isep.cms.warehouses.client.WarehousesService;
import pt.isep.cms.warehouses.shared.Warehouse;
import pt.isep.cms.warehouses.shared.WarehouseDetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

@SuppressWarnings("serial")
public class WarehousesServiceImpl extends RemoteServiceServlet implements
        WarehousesService {

    private static final String[] warehousesNameData = new String[]{
            "Armazem Holl", "Armazem Emer", "Armazem Heal", "Armazem Brige"};

    private final String[] warehousesTotalCapacityData = new String[]{
            "12", "10", "22", "32"};

    private final String[] warehousesLocationData = new String[]{
            "Porto", "Lisboa", "Braga",
            "Viana do Castelo"};

    private final HashMap<String, Warehouse> warehouses = new HashMap<String, Warehouse>();

    public WarehousesServiceImpl() {
        initWarehouses();
    }

    private void initWarehouses() {
        // TODO: Create a real UID for each warehouse
        //
        for (int i = 0; i < warehousesNameData.length && i < warehousesTotalCapacityData.length && i < warehousesLocationData.length; ++i) {
            Warehouse warehouse = new Warehouse(String.valueOf(i), warehousesNameData[i], warehousesTotalCapacityData[i], warehousesLocationData[i]);
            warehouses.put(warehouse.getId(), warehouse);
        }
    }

    public ArrayList<WarehouseDetails> getWarehouseDetails() {
        ArrayList<WarehouseDetails> warehouseDetails = new ArrayList<WarehouseDetails>();

        Iterator<String> it = warehouses.keySet().iterator();
        while (it.hasNext()) {
            Warehouse warehouse = warehouses.get(it.next());
            warehouseDetails.add(warehouse.getLightWeightWarehouse());
        }

        return warehouseDetails;
    }

    public Warehouse getWarehouse(String id) {
        return warehouses.get(id);
    }
}
