package pt.isep.cms.warehouses.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class EditWarehouseEvent extends GwtEvent<EditWarehouseEventHandler>{
  public static Type<EditWarehouseEventHandler> TYPE = new Type<EditWarehouseEventHandler>();
  private final String id;

  public EditWarehouseEvent(String id) {
    this.id = id;
  }

  public String getId() { return id; }

  @Override
  public Type<EditWarehouseEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(EditWarehouseEventHandler handler) {
    handler.onEditWarehouse(this);
  }
}
