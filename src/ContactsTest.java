public class ContactsTest {
    public static void main(String[] args) {







//        Contact david = new Contact("David", "2100009999");
        ContactManager cm = new ContactManager();

        cm.addItemToList(cm.contactList, cm.prompt("Enter name: "), cm.prompt("Enter number: "));
        cm.addItemToList(cm.contactList, cm.prompt("Enter name: "), cm.prompt("Enter number: "));

        cm.showAll(cm.contactList);

        cm.writeContactToFile("contacts.txt", cm.contactList);


        cm.searchContacts(cm.contactList, cm.prompt("Enter search: "));
    }
}
