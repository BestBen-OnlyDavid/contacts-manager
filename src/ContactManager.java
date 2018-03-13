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

    public void printTableHead() {
        System.out.println("");
        System.out.println("+--------------------------+");
        System.out.printf("| %-10s| %-10s %-1s\n", "Name", "Phone Number",  "|");
        System.out.println("+--------------------------+");
    }

    public void printTableFooter(){
        System.out.println("+--------------------------+");
    }

    public String prompt(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public void showOptions(ArrayList<Contact> contacts) {
        System.out.println("");
        System.out.println("Contact Manager Prime");
        System.out.println("0 - View All Contacts");
        System.out.println("1 - Add new Contact");
        System.out.println("2 - Search Contact");
        System.out.println("3 - Delete Contact");
        System.out.println("/* - Exit");
        handleInput(prompt("Enter your choice: "), contacts, "contacts.txt");
    }

    public void handleInput(String choice, ArrayList<Contact> curList , String filename) {
        boolean quit;
        do {
            quit = false;
            switch (choice) {
                case "0":
                    showAll(curList);
                    break;
                case "1":
                    addItemToList(curList, prompt("Enter name: "), prompt("Enter number: "), filename);
                    break;
                case "2":
                    searchContacts(curList, prompt("Search string: "));
                    break;
                case "3":
                    deleteContact(curList, prompt("Delete item: "), filename);
                    break;
                case "4":
                    System.out.println("Goodbye!");
                    System.exit(0);
                    quit = true;
                    break;
                default:
                    break;
            }
            showOptions(curList);
        } while (quit);
    }


    public ArrayList<Contact> readFile(String fileName){
        Path path = Paths.get(fileName);
        try {
            List<String> allLines = Files.readAllLines(path);
            for (String line : allLines){
                String[] results = line.split(",");
                if (results[0].equalsIgnoreCase("") || results[1].equalsIgnoreCase("")){
                    System.out.println("Your list is empty");
                    break;
                } else {
                    Contact c = new Contact(results[0], results[1]);
                    contactList.add(c);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return contactList;
    }


    public void writeContactsToFile(List<Contact> listOfContacts, String fileName, boolean append) {
        Path path = Paths.get(fileName);

        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (!append){
            try {
                ArrayList<String> names = new ArrayList<>();
                for (Contact contact : listOfContacts){
                    String contactString = (contact.getContactName() + "," +  contact.getPhoneNumber());
                    names.add(contactString);
                }
                Files.write(path, names, StandardOpenOption.TRUNCATE_EXISTING);
            } catch (IOException e){
                throw new RuntimeException(e);
            }
        } else {
            try {
                ArrayList<String> names = new ArrayList<>();
                for (Contact contact : listOfContacts) {
                    String contactString = (contact.getContactName() + "," +  contact.getPhoneNumber());
                    names.add(contactString);
                }
                Files.write(path, names, StandardOpenOption.APPEND);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void showAll(ArrayList<Contact> contacts) {
        printTableHead();
        for (Contact contact : contacts) {
            System.out.println(contact.formatName() + contact.formatNumber());
        }
        printTableFooter();
    }

    public void addItemToList(ArrayList<Contact> curList, String name, String number, String fileName) {
        Contact contact = new Contact(name, number);
        curList.add(contact);

        System.out.println((char)27 + "[34m" );
        printTableFooter();
        System.out.println("Adding: " + name + " : " + number);
        printTableFooter();
        System.out.println((char)27 + "[39m" );
        writeContactsToFile(curList, fileName, false);
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
        if (count == 0) {
            System.out.println("No results found");
        }
        System.out.println((char)27 + "[32m");
        printTableHead();
        for (String r : resultList) {
            System.out.println(r);
        }
        printTableFooter();
        System.out.println((char)27 + "[39m" );
        return resultContacts;
    }

    public void deleteContact(ArrayList<Contact> contacts, String searchItem, String filename) {
        ArrayList<Contact> deleteThis = searchContacts(contacts, searchItem);

        System.out.println("\n" + (char)27 + "[31m" + "These Contacts Will Be Deleted");
        printTableHead();
        for (Contact d : deleteThis) {
            System.out.print(d.formatName() + d.formatNumber() + "\n");
//            System.out.println(d.formatName() + d.formatNumber() + "\n::::Pending Delete::::");
        }
        printTableFooter();

        if (prompt("Delete All? [y/n]").equalsIgnoreCase("y")) {
            contacts.removeAll(deleteThis);
            writeContactsToFile(contacts, filename, false);
        }
        System.out.printf((char)27 + "[39m");
    }
}
