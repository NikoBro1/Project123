import java.util.Scanner;

public class City {
    private String cityName;
    private String location;
    private String[] streets;


    public City (String cityName, String location, String streets){
        this.cityName = cityName;
        this.location = location;
        this.streets = new String[1];
        this.streets[0] = streets;
    }

    public String getCityName() {
        return cityName;
    }

    public String getLocation() {
        return location;
    }

    public String[] getStreets() {
        return streets;
    }



    public String toString(){
        String city = this.cityName + " " + "\n" + this.location ;
        return city;
    }

}
