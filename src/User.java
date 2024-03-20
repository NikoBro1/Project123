public class User {
    private String userName;
    private String password;
    private int phoneNumber;
    private String sellerOrRegular;

    public User (String username, String password){
        this.userName = username;
        this.password = password;
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

    public String setUsername(){
        this.userName = userName;
        return userName;
    }

    public String setPassword(){
        this.password = password;
        return password;
    }
}
