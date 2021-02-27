/*
 * Copyright 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package pt.isep.cms.warehouses.client.view;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.user.client.ui.*;
import pt.isep.cms.client.ShowcaseConstants;
import pt.isep.cms.warehouses.client.presenter.EditWarehousePresenter;

/**
 * Dialog Box for Adding and Updating Warehouses.
 */
public class WarehousesDialog implements EditWarehousePresenter.Display {

	/**
	 * The constants used in this Content Widget.
	 */
	public static interface CwConstants extends Constants {
		
		String cwAddWarehouseDialogCaption();
		
		String cwUpdateWarehouseDialogCaption();
				

	}

	/**
	 * An instance of the constants.
	 */
	private final CwConstants constants;
	private final ShowcaseConstants globalConstants;

	// Widgets
	private final TextBox name;
	private final TextBox totalCapacity;
	private final TextBox location;
	private final FlexTable detailsTable;
 	private final Button cancelButton;

	private void initDetailsTable() {
		detailsTable.setWidget(0, 0, new Label("Name"));
		detailsTable.setWidget(0, 1, name);
		detailsTable.setWidget(1, 0, new Label("Total Capacity"));
		detailsTable.setWidget(1, 1, totalCapacity);
		detailsTable.setWidget(2, 0, new Label("Location"));
		detailsTable.setWidget(2, 1, location);
		name.setFocus(true);
	}

	DecoratorPanel contentDetailsDecorator;
	final DialogBox dialogBox;

	/**
	 * Constructor.
	 *
	 * @param constants
	 *            the constants
	 */
	public WarehousesDialog(ShowcaseConstants constants) {
		// super(constants.cwDialogBoxName(), constants.cwDialogBoxDescription());

		this.constants = constants;
		this.globalConstants = constants;

		// Init the widgets of the dialog
		contentDetailsDecorator = new DecoratorPanel();
		contentDetailsDecorator.setWidth("30em"); // em = size of current font
		// initWidget(contentDetailsDecorator);

		VerticalPanel contentDetailsPanel = new VerticalPanel();
		contentDetailsPanel.setWidth("100%");

		// Create the warehouses list
		//
		detailsTable = new FlexTable();
		detailsTable.setCellSpacing(0);
		detailsTable.setWidth("100%");
		detailsTable.addStyleName("contacts-ListContainer");
		detailsTable.getColumnFormatter().addStyleName(1, "add-contact-input");
		name = new TextBox();
		totalCapacity = new TextBox();
		location = new TextBox();
		initDetailsTable();
		contentDetailsPanel.add(detailsTable);

		HorizontalPanel menuPanel = new HorizontalPanel();
 		cancelButton = new Button("Cancel");
 		menuPanel.add(cancelButton);
		contentDetailsPanel.add(menuPanel);
		contentDetailsDecorator.add(contentDetailsPanel);

		dialogBox = new DialogBox();
		dialogBox.ensureDebugId("cwDialogBox");

				dialogBox.add(contentDetailsDecorator);

		dialogBox.setGlassEnabled(true);
		dialogBox.setAnimationEnabled(true);
	}

	public void displayDialog() {
		// Create the dialog box
		// final DialogBox dialogBox = createDialogBox();

		dialogBox.center();
		dialogBox.show();
	}

//	@Override
//	public HasClickHandlers getSaveButton() {
//		// TODO Auto-generated method stub
//		return saveButton;
//		// return null;
//	}

	@Override
	public HasClickHandlers getCancelButton() {
		// TODO Auto-generated method stub
		return cancelButton;
		// return null;
	}

	@Override
	public HasValue<String> getFirstName() {
		// TODO Auto-generated method stub
		return name;
		// return null;
	}

	@Override
	public HasValue<String> getTotalCapacity() {
		// TODO Auto-generated method stub
		return totalCapacity;
		// return null;
	}

	@Override
	public HasValue<String> getLocation() {
		// TODO Auto-generated method stub
		return location;
		// return null;
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		// return null;
		displayDialog();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		// return null;
		dialogBox.hide();
	}

}
