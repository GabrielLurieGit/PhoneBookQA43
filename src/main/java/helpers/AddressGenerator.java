package helpers;

import java.util.Random;

public class AddressGenerator {

    private static final  String[] cities = {"New York", "Los Angeles", "Chicago", "Houston", "Phoenix", "Philadelphia", "San Antonio", "San Diego", "Dallas", "San Jose"};
    private static final  String[] streets = {"Main", "Broadway", "First", "Second", "Third", "Fourth", "Fifth", "Park", "Oak", "Pine"};
    private static final  String[] states = {"AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"};


    private static final Random random = new Random();
    public static String generateAddress(){
       String city = cities[random.nextInt(cities.length)];
       String street = streets[random.nextInt(streets.length)];
       String state = states[random.nextInt(states.length)];
       String zipCode = String.format("%05d",random.nextInt(1000));
       int numberHouse = random.nextInt(1000);
       return state + " " + zipCode+ " " + city + " "+ street + " " + numberHouse;
    }

    public static void main(String[] args) {
        System.out.println(generateAddress());
    }


}
