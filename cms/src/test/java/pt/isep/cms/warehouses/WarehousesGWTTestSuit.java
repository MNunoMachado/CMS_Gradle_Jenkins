package pt.isep.cms.warehouses;

import com.google.gwt.junit.tools.GWTTestSuite;
import junit.framework.Test;
import junit.framework.TestSuite;
import pt.isep.cms.warehouses.client.WarehousesGWTTest;

public class WarehousesGWTTestSuit extends GWTTestSuite {
    public static Test suite() {
        TestSuite suite = new TestSuite("Test for the Warehouses Application");
        suite.addTestSuite(WarehousesGWTTest.class);
        return suite;
    }
}