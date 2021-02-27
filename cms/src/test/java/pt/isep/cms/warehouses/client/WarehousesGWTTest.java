package pt.isep.cms.warehouses.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import pt.isep.cms.warehouses.client.presenter.WarehousesPresenter;
import pt.isep.cms.warehouses.client.view.WarehousesView;
import pt.isep.cms.warehouses.shared.Warehouse;


// Nao se pode usar o easymock com testes GWT pois este usar reflection e o GWT não consegue "transpile"....
//import static org.easymock.EasyMock.createStrictMock;

public class WarehousesGWTTest extends GWTTestCase {
	private WarehousesPresenter warehousesPresenter;
	private WarehousesServiceAsync rpcService;
	private HandlerManager eventBus;
	private WarehousesPresenter.Display mockDisplay;

	public String getModuleName() {
		return "pt.isep.cms.warehouses.TestCMSJUnit";
	}

	public void gwtSetUp() {
		rpcService = GWT.create(WarehousesService.class);
		mockDisplay = new WarehousesView();
		warehousesPresenter = new WarehousesPresenter(rpcService, eventBus, mockDisplay);
	}


	public void testWarehousesService() {
		// Create the service that we will test.
		WarehousesServiceAsync warehousesService = GWT.create(WarehousesService.class);
		ServiceDefTarget target = (ServiceDefTarget) warehousesService;
		target.setServiceEntryPoint(GWT.getModuleBaseURL() + "warehouses/warehousesService");

		// Since RPC calls are asynchronous, we will need to wait for a response
		// after this test method returns. This line tells the test runner to wait
		// up to 10 seconds before timing out.
		delayTestFinish(10000);

		// fail("Ainda nao implementado");

		// Send a request to the server.
		warehousesService.getWarehouse("2", new AsyncCallback<Warehouse>() {
			public void onFailure(Throwable caught) {
				// The request resulted in an unexpected error.
				fail("Request failure: " + caught.getMessage());
			}

			public void onSuccess(Warehouse result) {
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
