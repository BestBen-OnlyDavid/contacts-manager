public class Contact {

    private String contactName;
    private String phoneNumber;

    public Contact(String contactName, String phoneNumber) {
        this.contactName = contactName;
        this.phoneNumber = phoneNumber;
    }

    public String formatName(){
        return String.format("%-10s |", this.getContactName());
    }
    public String formatNumber(){
        return String.format(" %-10s", this.getPhoneNumber());
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






