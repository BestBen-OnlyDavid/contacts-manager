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

    public void showOptions() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Contact Manager Prime");
        System.out.println("0 - View All Contacts");
        System.out.println("1 - Add new Contact");
        System.out.println("2 - Search Contact");
        System.out.println("3 - Delete Contact");
        System.out.println("/* - Exit");
        scanner.nextInt();

        //Handle the input
        //switch statements that call other functions
    }


    public void showAll(ArrayList<Contact> contacts) {
        printTableHead();
        for (Contact contact : contacts) {
            System.out.println(contact.formatName() + contact.formatNumber());
        }
    }

    public void printTableHead() {
        System.out.println("----------------------------");
        System.out.printf("%-10s | %-10s \n", "Name", "Phone Number");
        System.out.println("----------------------------");
    }

    public void writeContactsToFile(List<Contact> listOfContacts, String fileName) {
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
                String contactString = (contact.formatName() + contact.formatNumber());
                names.add(contactString);
            }
            Files.write(path, names);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public void deleteContact(ArrayList<Contact> keepList, ArrayList<Contact> deleteList, String fileName){
        keepList.removeAll(deleteList);
        writeContactsToFile(keepList, fileName);
        showAll(keepList);
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

    //Returns a list of editable/deletable contacts
    public ArrayList<Contact> searchContacts(ArrayList<Contact> list,String searchString) {
        int count = 0;
        ArrayList<Contact> resultContacts = new ArrayList<>();
        ArrayList<String> resultList = new ArrayList<>();
        for (Contact c : list) {
            if (c.getContactName().contains(searchString)){
                resultList.add(c.formatName() + c.formatNumber());
                resultContacts.add(c);
                count++;
            }
        }
        if (count == 0) {
            for (Contact c : list) {
                if (c.getPhoneNumber().contains(searchString)){
                    resultList.add(c.formatName() + c.formatNumber());
                    resultContacts.add(c);
                    count++;
                }
            }
        }

        if (count > 1){
            //Delete all results question?
            //for each item deleteit()
        }

        if (count == 1){
            //prompt user to delete THIS result or edit

            //narrow search
        }


        if (count == 0) {
            System.out.println("No results found");
        }
        for (String r : resultList) {
            System.out.println(r);
        }
        System.out.println("List of searched Contact Objects ");
        for (Contact t : resultContacts){
            System.out.println(t.getContactName());
        }
        return resultContacts;
    }


}
