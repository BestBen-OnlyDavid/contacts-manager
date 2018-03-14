import java.util.ArrayList;

public class Contact {

    private String contactName;
    private String phoneNumber;

    public Contact(String contactName, String phoneNumber) {
        this.contactName = contactName;
        this.phoneNumber = phoneNumber;
    }

    public String formatName(){
        String name = this.getContactName();
        String firstLetter = name.substring(0,1);
        String newName = firstLetter.toUpperCase() + name.substring(1);
        return String.format("| %-9s |", newName);
    }
    public String formatNumber(){
        String number = this.getPhoneNumber();
        String areaCode = number.substring(0, 3);
        String prefix = number.substring(3, 6);
        String lineNumber = number.substring(6);
        String newString = "(" + areaCode + ")-" + prefix + "-" + lineNumber;
        return String.format(" %-15s |", newString);
    }

    public String getContactName() {
        return contactName;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}






