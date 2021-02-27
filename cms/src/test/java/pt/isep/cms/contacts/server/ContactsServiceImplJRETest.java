package pt.isep.cms.contacts.server;

import junit.framework.TestCase;
import pt.isep.cms.contacts.shared.Contact;
import pt.isep.cms.contacts.shared.ContactDetails;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class ContactsServiceImplJRETest extends TestCase {
    ContactsServiceImpl service = new ContactsServiceImpl();

    public void testAddContact() {
        Contact contact = new Contact("19", "Miguel", "Machado", "Miguel@com");
        Contact newContact = service.addContact(contact);
        String contactId = newContact.getId();
        assertEquals(service.getContact(contactId).firstName, newContact.getFirstName());
    }


    public void testUpdateContact() {
        Contact contact = new Contact("1", "Miguel", "Machado", "Miguel@com");
        Contact newContact = service.updateContact(contact);
        assertEquals(service.getContact("1").firstName, newContact.getFirstName());

    }


    public void testDeleteContact() {
        service.deleteContact("0");
        Contact contact = service.getContact("0");

        assertNull(contact);
    }


    public void testGetContactDetails() {
        ArrayList<ContactDetails> contactDetailsList = service.getContactDetails();
        assertEquals(contactDetailsList.size(), 22);
    }


    public void testDeleteContacts() {
        ArrayList<ContactDetails> contactDetailsList = service.getContactDetails();
        ArrayList<ContactDetails> deletedDetailsList = service.deleteContacts(new ArrayList<>(Arrays.asList("0", "1")));
        assertEquals(contactDetailsList.size(), deletedDetailsList.size() + 2);
    }


}