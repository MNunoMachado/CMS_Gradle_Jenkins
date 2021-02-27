package pt.isep.cms.contacts.shared;

import junit.framework.TestCase;


public class ContactJRETest extends TestCase {



    Contact baseContact;
    Contact secondContact;

    protected void setUp() {
        baseContact = new Contact("1", "Miguel", "Machado", "miguel@com");
        secondContact = new Contact();
    }
    
    
    public void testGetLightWeightContact() {
        ContactDetails details = baseContact.getLightWeightContact();
        String expectedResult = "Miguel Machado";
        assertEquals(expectedResult, details.getDisplayName());
    }

     
    public void testGetId() {
        String contactId = baseContact.getId();
        String expectedResult = "1";
        assertEquals(expectedResult, contactId);
    }

     
    public void testSetId() {
        baseContact.setId("2");
        String contactId = baseContact.getId();
        String expectedResult = "2";
        assertEquals(expectedResult, contactId);
    }

     
    public void testGetFirstName() {
        String contactName = baseContact.getFirstName();
        String expectedResult = "Miguel";
        assertEquals(expectedResult, contactName);
        
    }

     
    public void testSetFirstName() {
        baseContact.setFirstName("Joao");
        String contactId = baseContact.getFirstName();
        String expectedResult = "Joao";
        assertEquals(expectedResult, contactId);
    }

     
    public void testGetLastName() {
        String contactLastName = baseContact.getLastName();
        String expectedResult = "Machado";
        assertEquals(expectedResult, contactLastName);
    }

     
    public void testSetLastName() {
        secondContact.setLastName("Vieira");
        String contactLastName = secondContact.getLastName();
        String expectedResult = "Vieira";
        assertEquals(expectedResult, contactLastName);
    }

     
    public void testGetEmailAddress() {
        String contactEmail = baseContact.getEmailAddress();
        String expectedResult = "miguel@com";
        assertEquals(expectedResult, contactEmail);
    }

     
    public void testSetEmailAddress() {
        secondContact.setEmailAddress("joao@com");
        String contactEmail = secondContact.getEmailAddress();
        String expectedResult = "joao@com";
        assertEquals(expectedResult, contactEmail);
    }

     
    public void testGetFullName() {
        String  contactFullName = baseContact.getFullName();
        String expectedResult = "Miguel Machado";
        assertEquals(expectedResult,  contactFullName);
    }
}