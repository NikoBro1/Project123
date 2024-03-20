import java.util.Scanner;

public class RealEstate {
    private User[] users;
    private Property[] property;


    public boolean isUserNameAvailable (String username){
        boolean available = true;
        for (int i = 0; i <this.users.length; i++){
            if (this.users[i].getUserName().equals(username)){
                available = false;
                break;
            }
        }
        return available;
    }

    private User createUserName () {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Username:");
        String username = null;
        while (isUserNameAvailable(username)) {
            username = scanner.nextLine();
            if (isUserNameAvailable(username)) {
                System.out.println("This username is available!");
            } else {
                System.out.println("Enter another username please");
            }
        }
        return username;
    }

    public String createPassword (){
        Scanner scanner = new Scanner(System.in);
        String userPassword = "";
        while (userPassword.length() < 6){
            userPassword = scanner.nextLine();

        }
        return userPassword;
    }

}
