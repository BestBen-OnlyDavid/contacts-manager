public class ContactsTest {
    public static void main(String[] args) {
//        Contact david = new Contact("David", "2100009999");
        ContactManager cm = new ContactManager();
        //#######PRE POPULATED#######//
        Contact c1 = new Contact("Ben", "254-768-4191");
        Contact c2 = new Contact("David", "210-911-8788");
        Contact c3 = new Contact("Steve", "210-000-2131");
        Contact c4 = new Contact("Donald", "123-889-8929");
        Contact c5 = new Contact("Stony", "911-446-8247");
        Contact c6 = new Contact("Stony", "911-446-8247");

        cm.addItemToList(cm.contactList, c1.getContactName(), c1.getPhoneNumber());
        cm.addItemToList(cm.contactList, c2.getContactName(), c2.getPhoneNumber());
        cm.addItemToList(cm.contactList, c3.getContactName(), c3.getPhoneNumber());
        cm.addItemToList(cm.contactList, c4.getContactName(), c4.getPhoneNumber());
        cm.addItemToList(cm.contactList, c5.getContactName(), c5.getPhoneNumber());
        cm.addItemToList(cm.contactList, c6.getContactName(), c6.getPhoneNumber());
        //#######PRE POPULATED#######//


//        cm.addItemToList(cm.contactList, cm.prompt("Enter name: "), cm.prompt("Enter number: "));
//        cm.addItemToList(cm.contactList, cm.prompt("Enter name: "), cm.prompt("Enter number: "));


        cm.writeContactsToFile(cm.contactList, "contacts.txt");

        cm.showOptions();

//        cm.showAll(cm.contactList);
    }
}
