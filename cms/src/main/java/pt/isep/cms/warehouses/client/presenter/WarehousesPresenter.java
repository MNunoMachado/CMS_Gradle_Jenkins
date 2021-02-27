package pt.isep.cms.warehouses.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import pt.isep.cms.warehouses.client.WarehousesServiceAsync;
import pt.isep.cms.warehouses.client.event.EditWarehouseEvent;
import pt.isep.cms.warehouses.shared.WarehouseDetails;

import java.util.ArrayList;
import java.util.List;

public class WarehousesPresenter implements Presenter {

	private List<WarehouseDetails> warehouseDetails;

	public interface Display {
		HasClickHandlers getList();

		void setData(List<String> data);

		int getClickedRow(ClickEvent event);

		List<Integer> getSelectedRows();

		Widget asWidget();
	}

	private final WarehousesServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;

	public WarehousesPresenter(WarehousesServiceAsync rpcService, HandlerManager eventBus, Display view) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
	}

	public void bind() {


		display.getList().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				int selectedRow = display.getClickedRow(event);

				if (selectedRow >= 0) {
					String id = warehouseDetails.get(selectedRow).getId();
					eventBus.fireEvent(new EditWarehouseEvent(id));
				}
			}
		});
	}

	public void go(final HasWidgets container) {
		bind();
		container.clear();
		container.add(display.asWidget());

		fetchWarehouseDetails();
	}

	public void sortWarehouseDetails() {

		// Yes, we could use a more optimized method of sorting, but the
		// point is to create a test case that helps illustrate the higher
		// level concepts used when creating MVP-based applications.
		//
		for (int i = 0; i < warehouseDetails.size(); ++i) {
			for (int j = 0; j < warehouseDetails.size() - 1; ++j) {
				if (warehouseDetails.get(j).getDisplayName()
						.compareToIgnoreCase(warehouseDetails.get(j + 1).getDisplayName()) >= 0) {
					WarehouseDetails tmp = warehouseDetails.get(j);
					warehouseDetails.set(j, warehouseDetails.get(j + 1));
					warehouseDetails.set(j + 1, tmp);
				}
			}
		}
	}

	public void setWarehouseDetails(List<WarehouseDetails> warehouseDetails) {
		this.warehouseDetails = warehouseDetails;
	}

	public WarehouseDetails getWarehouseDetail(int index) {
		return warehouseDetails.get(index);
	}

	private void fetchWarehouseDetails() {
		rpcService.getWarehouseDetails(new AsyncCallback<ArrayList<WarehouseDetails>>() {
			public void onSuccess(ArrayList<WarehouseDetails> result) {
				warehouseDetails = result;
				sortWarehouseDetails();
				List<String> data = new ArrayList<String>();

				for (int i = 0; i < result.size(); ++i) {
					data.add(warehouseDetails.get(i).getDisplayName());
				}

				display.setData(data);
			}

			public void onFailure(Throwable caught) {
				Window.alert("Error fetching warehouse details");
			}
		});
	}


}
