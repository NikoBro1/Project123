public class User {
    private String userName;
    private String password;
    private String phoneNumber;
    private String sellerOrRegular;

    public User (String username, String password, String phoneNumber, String sellerOrRegular){
        this.userName = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.sellerOrRegular = sellerOrRegular;
    }

    public boolean checkCreds (String userName, String password){
        boolean success = false;
        if (this.userName.equals(userName) && this.password.equals(password)){
            success = true;
        }
        return success;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getPassword(){
        return this.password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getSellerOrRegular() {
        return sellerOrRegular;
    }

    public String toString(){
        return this.getUserName() + " " + this.getPhoneNumber() + " " + this.getSellerOrRegular();
    }
}
