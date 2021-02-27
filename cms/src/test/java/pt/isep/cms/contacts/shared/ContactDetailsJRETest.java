package pt.isep.cms.contacts.shared;

import junit.framework.TestCase;


public class ContactDetailsJRETest extends TestCase {

    ContactDetails contactDetails = new ContactDetails();

    ContactDetails secondContactDetails = new ContactDetails("1", "Miguel Machado");


    public void testGetId() {
        String wareHouseId = secondContactDetails.getId();
        String expectedResult = "1";
        assertEquals(expectedResult, wareHouseId);
    }


    public void testSetId() {
        secondContactDetails.setId("3");
        String wareHouseId = secondContactDetails.getId();
        String expectedResult = "3";
        assertEquals(expectedResult, wareHouseId);
    }


    public void testGetDisplayName() {
        String wareHouseName = secondContactDetails.getDisplayName();
        String expectedResult = "Miguel Machado";
        assertEquals(expectedResult, wareHouseName);
    }


    public void testSetDisplayName() {
        contactDetails.setDisplayName("Machado");
        String wareHouseName = contactDetails.getDisplayName();
        String expectedResult = "Machado";
        assertEquals(expectedResult, wareHouseName);
    }
}