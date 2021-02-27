package pt.isep.cms.contacts.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.Window;
import pt.isep.cms.contacts.client.ContactsServiceAsync;
import pt.isep.cms.contacts.client.event.ContactUpdatedEvent;
import pt.isep.cms.contacts.client.event.EditContactCancelledEvent;
import pt.isep.cms.contacts.shared.Contact;

public class EditContactPresenter implements Presenter {
	public interface Display {
		HasClickHandlers getSaveButton();

		HasClickHandlers getCancelButton();

		HasValue<String> getFirstName();

		HasValue<String> getLastName();

		HasValue<String> getEmailAddress();

		void show();

		void hide();
	}

	private Contact contact;
	private final ContactsServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;

	public EditContactPresenter(ContactsServiceAsync rpcService, HandlerManager eventBus, Display display) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.contact = new Contact();
		this.display = display;
		bind();
	}

	public EditContactPresenter(ContactsServiceAsync rpcService, HandlerManager eventBus, Display display, String id) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = display;
		bind();

		rpcService.getContact(id, new AsyncCallback<Contact>() {
			public void onSuccess(Contact result) {
				contact = result;
				EditContactPresenter.this.display.getFirstName().setValue(contact.getFirstName());
				EditContactPresenter.this.display.getLastName().setValue(contact.getLastName());
				EditContactPresenter.this.display.getEmailAddress().setValue(contact.getEmailAddress());
			}

			public void onFailure(Throwable caught) {
				Window.alert("Error retrieving contact");
			}
		});

	}

	public void bind() {
		this.display.getSaveButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				doSave();
				display.hide();
			}
		});

		this.display.getCancelButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				display.hide();
				eventBus.fireEvent(new EditContactCancelledEvent());
			}
		});
	}

	public void go(final HasWidgets container) {
		display.show();
	}

	private void doSave() {
		contact.setFirstName(display.getFirstName().getValue());
		contact.setLastName(display.getLastName().getValue());
		contact.setEmailAddress(display.getEmailAddress().getValue());

		if (contact.getId() == null) {
			// Adding new contact
			rpcService.addContact(contact, new AsyncCallback<Contact>() {
				public void onSuccess(Contact result) {
					eventBus.fireEvent(new ContactUpdatedEvent(result));
				}

				public void onFailure(Throwable caught) {
					Window.alert("Error adding contact");
				}
			});
		} else {
			// updating existing contact
			rpcService.updateContact(contact, new AsyncCallback<Contact>() {
				public void onSuccess(Contact result) {
					eventBus.fireEvent(new ContactUpdatedEvent(result));
				}

				public void onFailure(Throwable caught) {
					Window.alert("Error updating contact");
				}
			});
		}
	}

}
