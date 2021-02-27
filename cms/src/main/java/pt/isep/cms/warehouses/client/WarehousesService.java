package pt.isep.cms.warehouses.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import pt.isep.cms.warehouses.shared.Warehouse;
import pt.isep.cms.warehouses.shared.WarehouseDetails;

import java.util.ArrayList;

@RemoteServiceRelativePath("warehousesService")
public interface WarehousesService extends RemoteService {
    ArrayList<WarehouseDetails> getWarehouseDetails();

    Warehouse getWarehouse(String id);
}
