public class Property {
    private City city;
    private String street;
    private String rooms;
    private int price;
    private String type;
    private String rentOrSale;
    private String apartmentNumber;
    private int floor;
    private User sellerUserName;


    public Property (City city, String street, String rooms, int price, String type, String rentOrSale, String apartmentNumber, int floor, User sellerUserName){
        this.city = city;
        this.street = street;
        this.rooms = rooms;
        this.price = price;
        this.type = type;
        this.rentOrSale = rentOrSale;
        this.apartmentNumber = apartmentNumber;
        this.floor = floor;
        this.sellerUserName = sellerUserName;
    }

    public String getRooms() {
        return rooms;
    }

    public int getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public String getRentOrSale() {
        return rentOrSale;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public int getFloor() {
        return floor;
    }

    public User getSellerUserName() {
        return sellerUserName;
    }

    public City getCity() {
        return  city;
    }
    public String getStreet() {
        return street;
    }
    public String toString(){
        String property = this.city.getCityName() + " - " + this.street + " " + this.apartmentNumber + "\n" + this.type + " - " + this.rentOrSale + ": " + this.rooms + " rooms, floor " + this.floor +  "\n" + "Price: " + this.price + "$" + "\n" + "Contact info: " + this.sellerUserName;
        return property;
    }
}
