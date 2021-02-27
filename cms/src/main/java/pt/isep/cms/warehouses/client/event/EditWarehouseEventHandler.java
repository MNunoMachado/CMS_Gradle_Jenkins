package pt.isep.cms.warehouses.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface EditWarehouseEventHandler extends EventHandler {
  void onEditWarehouse(EditWarehouseEvent event);
}
