import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    public ArrayList<Contact> addItemToList(ArrayList<Contact> curList, String name, String number) {
        Contact contact = new Contact(name, number);
        curList.add(contact);
        return curList;

    }

    public String prompt(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public void searchContacts(ArrayList<Contact> list,String searchString) {
        int count = 0;
        ArrayList<String> resultList = new ArrayList<>();
        for (Contact c : list) {
            if (c.getContactName().contains(searchString)){
                resultList.add((c.getContactName() + " | " + c.getPhoneNumber()));
                count++;
            }
        }
        if (count == 0) {
            for (Contact c : list) {
                if (c.getPhoneNumber().contains(searchString)){
                    resultList.add((c.getContactName() + " | " + c.getPhoneNumber()));
                    count++;
                }
            }
        }
        if (count == 0) {
            System.out.println("No results found");
        }
        for (String r : resultList) {
            System.out.println(r);
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
