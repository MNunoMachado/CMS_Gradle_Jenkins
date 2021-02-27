package pt.isep.cms.contacts.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import pt.isep.cms.contacts.client.ContactsService;
import pt.isep.cms.contacts.client.ContactsServiceAsync;
import pt.isep.cms.contacts.client.presenter.ContactsPresenter;
import pt.isep.cms.contacts.client.view.ContactsView;
import pt.isep.cms.contacts.shared.Contact;
import pt.isep.cms.contacts.shared.ContactDetails;

// Nao se pode usar o easymock com testes GWT pois este usar reflection e o GWT não consegue "transpile"....
//import static org.easymock.EasyMock.createStrictMock;

import java.util.ArrayList;

public class ExampleGWTTest extends GWTTestCase {
	private ContactsPresenter contactsPresenter;
	private ContactsServiceAsync rpcService;
	private HandlerManager eventBus;
	private ContactsPresenter.Display mockDisplay;

	public String getModuleName() {
		return "pt.isep.cms.contacts.TestCMSJUnit";
	}

	public void gwtSetUp() {
		rpcService = GWT.create(ContactsService.class);
		mockDisplay = new ContactsView();
		contactsPresenter = new ContactsPresenter(rpcService, eventBus, mockDisplay);
	}


	public void testContactsService() {
		// Create the service that we will test.
		ContactsServiceAsync contactsService = GWT.create(ContactsService.class);
		ServiceDefTarget target = (ServiceDefTarget) contactsService;
		target.setServiceEntryPoint(GWT.getModuleBaseURL() + "contacts/contactsService");

		// Since RPC calls are asynchronous, we will need to wait for a response
		// after this test method returns. This line tells the test runner to wait
		// up to 10 seconds before timing out.
		delayTestFinish(10000);

		// fail("Ainda nao implementado");

		// Send a request to the server.
		contactsService.getContact("2", new AsyncCallback<Contact>() {
			public void onFailure(Throwable caught) {
				// The request resulted in an unexpected error.
				fail("Request failure: " + caught.getMessage());
			}

			public void onSuccess(Contact result) {
				// Verify that the response is correct.
				assertTrue(result != null);

				// Now that we have received a response, we need to tell the test runner
				// that the test is complete. You must call finishTest() after an
				// asynchronous test finishes successfully, or the test will time out.
				finishTest();
			}
		});
	}
}
