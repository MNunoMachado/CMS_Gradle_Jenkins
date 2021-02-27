package pt.isep.cms.contacts.client;

import pt.isep.cms.client.ShowcaseConstants;

import pt.isep.cms.contacts.client.event.AddContactEvent;
import pt.isep.cms.contacts.client.event.AddContactEventHandler;
import pt.isep.cms.contacts.client.event.ContactUpdatedEvent;
import pt.isep.cms.contacts.client.event.ContactUpdatedEventHandler;
import pt.isep.cms.contacts.client.event.EditContactEvent;
import pt.isep.cms.contacts.client.event.EditContactEventHandler;
import pt.isep.cms.contacts.client.event.EditContactCancelledEvent;
import pt.isep.cms.contacts.client.event.EditContactCancelledEventHandler;
import pt.isep.cms.contacts.client.presenter.ContactsPresenter;
import pt.isep.cms.contacts.client.presenter.EditContactPresenter;
import pt.isep.cms.contacts.client.presenter.Presenter;
import pt.isep.cms.contacts.client.view.ContactsView;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.user.client.ui.HasWidgets;

import pt.isep.cms.contacts.client.view.ContactsDialog;

public class ContactsController implements Presenter { // (ATB) No history at this level, ValueChangeHandler<String> {
	private final HandlerManager eventBus;
	private final ContactsServiceAsync rpcService;
	private HasWidgets container;

	public static interface CwConstants extends Constants {
	}

	/**
	 * An instance of the constants.
	 */
	private final CwConstants constants;
	private final ShowcaseConstants globalConstants;

	public ContactsController(ContactsServiceAsync rpcService, HandlerManager eventBus, ShowcaseConstants constants) {
		this.eventBus = eventBus;
		this.rpcService = rpcService;
		this.constants = constants;
		this.globalConstants = constants;

		bind();
	}

	private void bind() {
		// (ATB) No History at this level
		// History.addValueChangeHandler(this);

		eventBus.addHandler(AddContactEvent.TYPE, new AddContactEventHandler() {
			public void onAddContact(AddContactEvent event) {
				doAddNewContact();
			}
		});

		eventBus.addHandler(EditContactEvent.TYPE, new EditContactEventHandler() {
			public void onEditContact(EditContactEvent event) {
				doEditContact(event.getId());
			}
		});

		eventBus.addHandler(EditContactCancelledEvent.TYPE, new EditContactCancelledEventHandler() {
			public void onEditContactCancelled(EditContactCancelledEvent event) {
				doEditContactCancelled();
			}
		});

		eventBus.addHandler(ContactUpdatedEvent.TYPE, new ContactUpdatedEventHandler() {
			public void onContactUpdated(ContactUpdatedEvent event) {
				doContactUpdated();
			}
		});
	}

	private void doAddNewContact() {
		// Lets use the presenter to display a dialog...
		Presenter presenter = new EditContactPresenter(rpcService, eventBus, new ContactsDialog(globalConstants, ContactsDialog.Type.ADD));
		presenter.go(container);

	}

	private void doEditContact(String id) {
		Presenter presenter = new EditContactPresenter(rpcService, eventBus, new ContactsDialog(globalConstants, ContactsDialog.Type.UPDATE), id);
		presenter.go(container);
	}

	private void doEditContactCancelled() {
		// Nothing to update...
	}

	private void doContactUpdated() {
		// (ATB) Update the list of contacts...
		Presenter presenter = new ContactsPresenter(rpcService, eventBus, new ContactsView());
		presenter.go(container);
	}

	public void go(final HasWidgets container) {
		this.container = container;

		Presenter presenter = new ContactsPresenter(rpcService, eventBus, new ContactsView());
		presenter.go(container);
	}

}
