package pt.isep.cms.warehouses.client;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.user.client.ui.HasWidgets;
import pt.isep.cms.client.ShowcaseConstants;
import pt.isep.cms.warehouses.client.event.*;
import pt.isep.cms.warehouses.client.presenter.WarehousesPresenter;
import pt.isep.cms.warehouses.client.presenter.EditWarehousePresenter;
import pt.isep.cms.warehouses.client.presenter.Presenter;
import pt.isep.cms.warehouses.client.view.WarehousesDialog;
import pt.isep.cms.warehouses.client.view.WarehousesView;

public class WarehousesController implements Presenter { // (ATB) No history at this level, ValueChangeHandler<String> {
	private final HandlerManager eventBus;
	private final WarehousesServiceAsync rpcService;
	private HasWidgets container;

	public static interface CwConstants extends Constants {
	}

	/**
	 * An instance of the constants.
	 */
	private final CwConstants constants;
	private final ShowcaseConstants globalConstants;

	public WarehousesController(WarehousesServiceAsync rpcService, HandlerManager eventBus, ShowcaseConstants constants) {
		this.eventBus = eventBus;
		this.rpcService = rpcService;
		this.constants = constants;
		this.globalConstants = constants;

		bind();
	}

	private void bind() {
		eventBus.addHandler(EditWarehouseEvent.TYPE, new EditWarehouseEventHandler() {
			public void onEditWarehouse(EditWarehouseEvent event) {
				doEditWarehouse(event.getId());
			}
		});

		eventBus.addHandler(EditWarehouseCancelledEvent.TYPE, new EditWarehouseCancelledEventHandler() {
			public void onEditWarehouseCancelled(EditWarehouseCancelledEvent event) {
				doEditWarehouseCancelled();
			}
		});

	}

	private void doEditWarehouse(String id) {
		Presenter presenter = new EditWarehousePresenter(rpcService, eventBus, new WarehousesDialog(globalConstants), id);
		presenter.go(container);
	}

	private void doEditWarehouseCancelled() {
		// Nothing to update...
	}


	public void go(final HasWidgets container) {
		this.container = container;

		Presenter presenter = new WarehousesPresenter(rpcService, eventBus, new WarehousesView());
		presenter.go(container);
	}

}
