package pt.isep.cms.contacts.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import pt.isep.cms.contacts.shared.Contact;
import pt.isep.cms.contacts.shared.ContactDetails;

import java.util.ArrayList;

@RemoteServiceRelativePath("contactsService")
public interface ContactsService extends RemoteService {
	
  Contact addContact(Contact contact);
  Boolean deleteContact(String id); 
  ArrayList<ContactDetails> deleteContacts(ArrayList<String> ids);
  ArrayList<ContactDetails> getContactDetails();
  Contact getContact(String id);
  Contact updateContact(Contact contact);
}
