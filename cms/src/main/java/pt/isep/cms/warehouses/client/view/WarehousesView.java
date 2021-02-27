package pt.isep.cms.warehouses.client.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.*;
import pt.isep.cms.warehouses.client.presenter.WarehousesPresenter;

import java.util.ArrayList;
import java.util.List;

public class WarehousesView extends Composite implements WarehousesPresenter.Display {
 	private FlexTable warehousesTable;
	private final FlexTable contentTable;
	// private final VerticalPanel vPanel ;

	public WarehousesView() {
		DecoratorPanel contentTableDecorator = new DecoratorPanel();
		initWidget(contentTableDecorator);
		contentTableDecorator.setWidth("100%");
		contentTableDecorator.setWidth("18em");

		contentTable = new FlexTable();
		contentTable.setWidth("100%");
		contentTable.getCellFormatter().addStyleName(0, 0, "contacts-ListContainer");
		contentTable.getCellFormatter().setWidth(0, 0, "100%");
		contentTable.getFlexCellFormatter().setVerticalAlignment(0, 0, DockPanel.ALIGN_TOP);

		// vPanel = new VerticalPanel();
		// initWidget(vPanel);

		// Create the menu
		//
		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.setBorderWidth(0);
		hPanel.setSpacing(0);
		hPanel.setHorizontalAlignment(HorizontalPanel.ALIGN_LEFT);

		// vPanel.add(hPanel);

		contentTable.getCellFormatter().addStyleName(0, 0, "contacts-ListMenu");
		contentTable.setWidget(0, 0, hPanel);

		// Create the warehouses list
		//
		warehousesTable = new FlexTable();
		warehousesTable.setCellSpacing(0);
		warehousesTable.setCellPadding(0);
		warehousesTable.setWidth("100%");
		warehousesTable.addStyleName("contacts-ListContents");
		warehousesTable.getColumnFormatter().setWidth(0, "15px");

		// vPanel.add(contactsTable);

		contentTable.setWidget(1, 0, warehousesTable);

		contentTableDecorator.add(contentTable);
	}



	public HasClickHandlers getList() {
		return warehousesTable;
	}

	public void setData(List<String> data) {
		warehousesTable.removeAllRows();

		for (int i = 0; i < data.size(); ++i) {
			warehousesTable.setWidget(i, 0, new CheckBox());
			warehousesTable.setText(i, 1, data.get(i));
		}
	}

	public int getClickedRow(ClickEvent event) {
		int selectedRow = -1;
		HTMLTable.Cell cell = warehousesTable.getCellForEvent(event);

		if (cell != null) {
			// Suppress clicks if the user is actually selecting the
			// check box
			//
			if (cell.getCellIndex() > 0) {
				selectedRow = cell.getRowIndex();
			}
		}

		return selectedRow;
	}

	public List<Integer> getSelectedRows() {
		List<Integer> selectedRows = new ArrayList<Integer>();

		for (int i = 0; i < warehousesTable.getRowCount(); ++i) {
			CheckBox checkBox = (CheckBox) warehousesTable.getWidget(i, 0);
			if (checkBox.getValue()) {
				selectedRows.add(i);
			}
		}

		return selectedRows;
	}

	public Widget asWidget() {
		return this;
	}
}
