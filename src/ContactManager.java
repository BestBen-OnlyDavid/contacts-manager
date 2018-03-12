import java.util.ArrayList;

public class ContactManager {

    ArrayList<Contact> contactList = new ArrayList<>();

    public ContactManager() {
        this.contactList = contactList;
    }



    public void showAll(ArrayList<Contact> contacts) {
        for (Contact contact : contacts) {
            System.out.println(contact.getContactName());
            System.out.println(contact.getPhoneNumber());
        }



    }



}
