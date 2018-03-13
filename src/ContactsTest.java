public class ContactsTest {
    public static void main(String[] args) {
//        Contact david = new Contact("David", "2100009999");
        ContactManager cm = new ContactManager();
        cm.contactList = cm.readFile("contacts.txt");
        cm.showOptions(cm.contactList);

    }
}
