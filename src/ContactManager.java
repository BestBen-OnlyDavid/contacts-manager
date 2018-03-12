import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class ContactManager {

    ArrayList<Contact> contactList = new ArrayList<>();

//    public ContactManager() {
//        this.contactList = contactList;
//    }

    public void showAll(ArrayList<Contact> contacts) {
        for (Contact contact : contacts) {
            System.out.println(contact.getContactName());
            System.out.println(contact.getPhoneNumber());
        }
    }

    public void writeContactToFile(String fileName, List<Contact> listOfContacts) {
        Path path = Paths.get(fileName);
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            ArrayList<String> names = new ArrayList<>();
            for (Contact contact : listOfContacts){
                String contactString = (contact.getContactName() + " | " + contact.getPhoneNumber());
                names.add(contactString);
            }
            Files.write(path, names);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

//    public static void writeToFile(String filePath, ArrayList<Contact> contacts){
//        if (!Files.exists(Paths.get(filePath))){
//            try {
//                Files.createFile(Paths.get(filePath));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        try {
//            Files.write(Paths.get(filePath), contacts, StandardOpenOption.APPEND);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
