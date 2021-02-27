package pt.isep.cms.warehouses.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import pt.isep.cms.warehouses.client.WarehousesServiceAsync;
import pt.isep.cms.warehouses.client.event.EditWarehouseCancelledEvent;
import pt.isep.cms.warehouses.shared.Warehouse;

public class EditWarehousePresenter implements Presenter {
	public interface Display {
//		HasClickHandlers getSaveButton();

		HasClickHandlers getCancelButton();

		HasValue<String> getFirstName();

		HasValue<String> getTotalCapacity();

		HasValue<String> getLocation();

		void show();

		void hide();
	}

	private Warehouse warehouse;
	private final WarehousesServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;

	public EditWarehousePresenter(WarehousesServiceAsync rpcService, HandlerManager eventBus, Display display) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.warehouse = new Warehouse();
		this.display = display;
		bind();
	}

	public EditWarehousePresenter(WarehousesServiceAsync rpcService, HandlerManager eventBus, Display display, String id) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = display;
		bind();

		rpcService.getWarehouse(id, new AsyncCallback<Warehouse>() {
			public void onSuccess(Warehouse result) {
				warehouse = result;
				EditWarehousePresenter.this.display.getFirstName().setValue(warehouse.getName());
				EditWarehousePresenter.this.display.getTotalCapacity().setValue(warehouse.getTotalCapacity());
				EditWarehousePresenter.this.display.getLocation().setValue(warehouse.getLocation());
			}

			public void onFailure(Throwable caught) {
				Window.alert("Error retrieving warehouse");
			}
		});

	}

	public void bind() {


		this.display.getCancelButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				display.hide();
				eventBus.fireEvent(new EditWarehouseCancelledEvent());
			}
		});
	}

	public void go(final HasWidgets container) {
		display.show();
	}



}
