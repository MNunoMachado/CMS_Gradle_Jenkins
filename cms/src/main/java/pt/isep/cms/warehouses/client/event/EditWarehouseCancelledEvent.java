package pt.isep.cms.warehouses.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class EditWarehouseCancelledEvent extends GwtEvent<EditWarehouseCancelledEventHandler>{
  public static Type<EditWarehouseCancelledEventHandler> TYPE = new Type<EditWarehouseCancelledEventHandler>();

  @Override
  public Type<EditWarehouseCancelledEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(EditWarehouseCancelledEventHandler handler) {
    handler.onEditWarehouseCancelled(this);
  }
}
