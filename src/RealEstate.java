import java.util.Scanner;

public class RealEstate {
    private final String[] CITYNAMES = {"Ashkelon","Ashdod","Tel-Aviv","Haifa","Dimona","Sderot","Mizpe-Ramon","Jerusalem","Eilat", "Kiryat-Gat"};
    private final String[] LOCATION = {"darom","merkaz","zafon"};
    private final String[] STREETS = {"eli cohen", "shmulik", "alenbi", "yosi", "shmuel", "zanhanim", "gdud", "hativa", "ha-tayasim", "ha-zanhanim"};
    private User[] users;
    private Property[] property;
    private City[] city;

    public RealEstate (){
        this.users = new User[0];
        this.property = new Property[0];
        this.city = new City[10];
        int index = 0;
        for (int i = 0; i < this.city.length; i++){
            this.city[i] = new City(CITYNAMES[i], LOCATION[index], STREETS[i]);
            index++;
            if (index > 2){
                index = 0;
            }
        }
    }
    public void menu (){
        Scanner scanner = new Scanner(System.in);
        boolean activeMenu = true;
        System.out.println("1: Create account");
        System.out.println("2: Login");
        System.out.println("3: End!");
        int input = scanner.nextInt();
        scanner.nextLine();
        User user = null;
        while (activeMenu){
            switch (input){
                case 1 ->
                        createUser();
                case 2 -> {
                    user = login();
                    loggedMenu(user);
                }
                case 3 ->{
                    activeMenu = false;
                    System.out.println("Logged out!");}
                default -> System.out.println("Invalid number try again");
            }
        }

    }

    public boolean isUserNameAvailable (String username){
        boolean available = true;
        for (int i = 0; i < this.users.length; i++){
            if (this.users[i].getUserName().equals(username)){
                available = false;
                break;
            }
        }
        return available;
    }

    private void createUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Username:");
        String username = null;
        while (isUserNameAvailable(username)) {
            username = scanner.nextLine();
            if (isUserNameAvailable(username)) {
                System.out.println("This username is OK");
                break;
            } else {
                System.out.println("Enter another username please");
            }
        }
        String userPassword = "";
        System.out.println("Please enter a password");
        boolean validPassword = false;
        while (userPassword.length() < 5 || !validPassword){
            userPassword = scanner.nextLine();
            if ((userPassword.contains("%") || userPassword.contains("$") || userPassword.contains("_")) && userPassword.length() >= 5) {
                System.out.println("This password is OK");
                validPassword = true;
            }else {
                System.out.println("Please enter another password");
            }
        }
        String validPhoneNumber = " ";
        boolean validNum = false;
        while (validPhoneNumber.length() < 10 && !validPhoneNumber.startsWith("05") || !validNum){
            System.out.println("Please enter a valid phone number that starts 05 and has 10 number!");
            validPhoneNumber = scanner.nextLine();
            if (validPhoneNumber.startsWith("05") && validPhoneNumber.length() == 10){
                System.out.println("Your phone number is valid");
                validNum = true;
            } else {
                System.out.println("ERROR");
            }
        }
        System.out.println("Are you a real estate broker Y/N, Y = Real Estate Broker, No = regular user");
        String seller = scanner.nextLine();
        if (seller.toUpperCase().equals("Y")){
            seller = "(real estate broker)";
            System.out.println("You are a registered real estate broker now");
        } else {
            seller = "(regular user)";
            System.out.println("You registered as a regular user!");
        }
        User user = new User(username,userPassword,validPhoneNumber,seller);
        addUserToArray(user);
        menu();
    }
    public void addUserToArray (User user){
        User[] temp = new User[this.users.length + 1];
        for (int i = 0; i < this.users.length; i++){
            temp[i] = this.users[i];
        }
        temp[temp.length - 1] = user;
        this.users = temp;
    }
    public User login () {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your username:");
        String username = scanner.nextLine();
        System.out.println("Enter your password");
        String password = scanner.nextLine();
        User found = null;
        for (int i = 0; i < this.users.length; i++){
            if (this.users[i].checkCreds(username, password)){
                found = this.users[i];
                System.out.println("Logged in successfully");
                break;
            }
        }
        if (found == null){
            System.out.println("There is no such user");
            menu();
        }
        return found;
    }

    public void loggedMenu (User user){
        Scanner scanner = new Scanner(System.in);
        boolean activeMenu = true;
        while (activeMenu){
            System.out.println("1: Add new property");
            System.out.println("2: Delete property");
            System.out.println("3: Show all property's");
            System.out.println("4: Show all property's posted by the user");
            System.out.println("5: Search property by parameters");
            System.out.println("6: Logout and back to Main Menu!");
            int input = scanner.nextInt();
            scanner.nextLine();
            switch (input){
                case 1 -> postNewProperty(user);
                case 2 -> removeProperty(user);
                case 3 -> printAllProperties();
                case 4 -> printProperties(user);
                case 5 -> printSearch(search());
                case 6 -> menu();
                default -> System.out.println("invalid option try again");
            }
        }
    }

    public boolean postNewProperty(User user){
        Scanner scanner = new Scanner(System.in);
        String rooms = "";
        int price = 0;
        int floor = 0;
        int room = 0;
        String apartNum = "";
        String rentOrSale = "";
        int apartPrice = 0;
        int counter = 0;
        boolean isOk = true;
        String isRentOrSale = "";
        boolean ifValidToSell = true;
            for (int i = 0; i < this.property.length; i++) {
                if (this.property[i].getSellerUserName() == user) {
                    counter++;
                }
            }
        if (user.getSellerOrRegular().equals("Y")) {
            if (counter >= 5) {
                isOk = false;
                System.out.println("You have reached your post limit");
            }
        }
        if (user.getSellerOrRegular().equals("N")) {
            if (counter >= 2) {
                isOk = false;
                System.out.println("You have reached your post limit");
            }
        }
        if (isOk) {
            System.out.println("Your account is A Real Estate Broker you can post up to 5 property's");
            boolean createProperty = false;
            System.out.println("please create a property");
            System.out.println("How many rooms in the property?");
            rooms = scanner.nextLine();
            System.out.println("Enter the price for the property");
            price = scanner.nextInt();
            scanner.nextLine();
            System.out.println("What kind of property are you selling \n 1: Regular apartment with floors \n 2: Penthouse \n 3: Personal house");
            String apartmentType = scanner.nextLine();
            switch (apartmentType) {
                case "1" -> apartmentType = "Regular apartment";
                case "2" -> apartmentType = "Penthouse";
                case "3" -> apartmentType = "Personal house";
            }
            if (apartmentType.equals("Regular apartment") || apartmentType.equals("Penthouse")) {
                System.out.println("What floor?");
                floor = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("What is the number of the apartment?");
                apartNum = scanner.nextLine();
            }
            System.out.println("Is the apartment rent or sale Y/N, Y = Rent, N = Sale, N = default");
            isRentOrSale = scanner.nextLine();
            if (isRentOrSale.toUpperCase().equals("Y")) {
                rentOrSale = "for rent";
                System.out.println("Your apartment is for rent!");
            } else {
                rentOrSale = "for sale";
                System.out.println("Your apartment is for sale!");
            }
            System.out.println("Choose a city");
            for (int i  = 0; i < this.city.length; i++){
                System.out.println(i + ":" + city[i]);
            }
            int chosenCity = scanner.nextInt();
            scanner.nextLine();
            City city1 = this.city[chosenCity];
            for (int i = 0; i < city1.getStreets().length; i++){
                System.out.println(i + ":" + city1.getStreets()[i]);
            }
            System.out.println("Choose a street");
            int chosenStreet = scanner.nextInt();
            scanner.nextLine();
            String street1 = city1.getStreets()[chosenStreet];
            Property property = new Property(city1, street1, rooms, price, apartmentType, rentOrSale, apartNum, floor, user);
            addToArrayProperty(property);
        }
        return ifValidToSell;
    }
    public void addToArrayProperty (Property property){
        Property[] temp = new Property[this.property.length + 1];
        for (int i = 0; i < this.property.length; i++){
            temp[i] = this.property[i];
        }
        temp[temp.length - 1] = property;
        this.property = temp;
    }


    public void printAllProperties(){
        for (int i = 0; i < this.property.length; i++){
            System.out.println(this.property[i]);
        }
    }
    public void removeProperty(User user){
        Scanner scanner = new Scanner(System.in);
        int count = 0;
        for (int i = 0; i < this.property.length; i++){
            if (this.property[i].getSellerUserName() == user){
                count++;
            }
        }
        if (count == 0){
            System.out.println("You haven't posted any property's");
        }
        if (count > 0){
            Property[] temp = new Property[this.property.length];
            int index = 0;
            for (int i = 0; i < this.property.length; i++){
                if (this.property[i].getSellerUserName() == user){
                    temp[index] = this.property[i];
                    index++;
                    System.out.println(i + ":" + this.property[i]);
                }
            }
            index = 0;
            Property[] removedProperty = new Property[this.property.length - 1];
            System.out.println("Choose which property to remove");
            int toRemove = scanner.nextInt();
            scanner.nextLine();
            if (temp[toRemove] != null){
                for (int i = 0; i < this.property.length; i++){
                    if (this.property[i] != temp[toRemove]){
                        removedProperty[index] = this.property[i];
                        index++;
                    }
                }
                this.property = removedProperty;
            }
        }
    }

    public void printProperties(User user){
        for (int i = 0; i < this.property.length; i++) {
            if (this.property[i].getSellerUserName() == user){
                System.out.println(this.property[i]);
            }
        }
    }

    public Property[] search(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Is the property you are searching for sale or for rent Y = rent / N = Sale / -999 = Ignore rent or sale search option");
        String rentOrSale = scanner.nextLine();
        if (rentOrSale.toUpperCase().equals("Y")){
            rentOrSale = "for rent";
            System.out.println("You chose a property for rent");
        } else if (rentOrSale.toUpperCase().equals("N")){
            rentOrSale = "for sale";
            System.out.println("You chose a property for sale");
        } else {
            rentOrSale = "-999";
            System.out.println("You chose to ignore that search");
        }
        System.out.println("What kind of property are you selling \n 1: Regular apartment with floors \n 2: Penthouse \n 3: Personal house \n -999: To ignore");
        String apartmentType = scanner.nextLine();
        switch (apartmentType) {
            case "1" -> apartmentType = "Regular apartment";
            case "2" -> apartmentType = "Penthouse";
            case "3" -> apartmentType = "Personal house";
            default -> apartmentType = "-999";
        }
        System.out.println("How many rooms would you like? / -999 = Ignore rooms search");
        String rooms = scanner.nextLine();
        System.out.println("Whats the price difference would you like? / if -999 = Ignore price search");
        System.out.println("Type in the minimum price");
        int minPrice = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Type in the maximum price");
        int maxPrice = scanner.nextInt();
        scanner.nextLine();
        Property[] matched = new Property[0];
        int index = 0;
        int count = 0;
        for (int i = 0; i < this.property.length; i++){
            boolean match = true;
            if (!rentOrSale.equals("-999")) {
                if (!this.property[i].getRentOrSale().equalsIgnoreCase(rentOrSale)) {
                    match = false;
                }
            }
            if (!apartmentType.equals("-999")){
                if (!this.property[i].getType().equalsIgnoreCase(apartmentType)){
                    match = false;
                }
            }
            if (!rooms.equals("-999")){
                if (!this.property[i].getRooms().equalsIgnoreCase(rooms)){
                    match = false;
                }
            }
            if (minPrice != -999){
                if (this.property[i].getPrice() <= minPrice){
                    match = false;
                }
            }
            if (maxPrice != -999){
                if (this.property[i].getPrice() >= maxPrice){
                    match = false;
                }
            }
            if (match){
                Property[] temp = new Property[count + 1];
                 for (int j = 0; j < matched.length; j++){
                     temp[j] = matched[j];
                 }
                temp[temp.length - 1] = this.property[i];
                matched = temp;
                count++;
            }
        }
        return matched;
    }

    public void printSearch (Property[] matched){
        for (int i = 0; i < matched.length; i++){
            System.out.println(matched[i]);
        }
    }

}
