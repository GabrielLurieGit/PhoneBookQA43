package helpers;

import java.util.Random;

public class PhoneNumberGenerator {
    private static  final int MIN_VALUE = 10;
    private static  final int MAX_VALUE = 15;

    public static String generatePhoneNumber(){
        Random random = new Random();
        int length = random.nextInt(MAX_VALUE-MIN_VALUE+1)+MIN_VALUE;
        StringBuilder phoneNumber = new StringBuilder();
        for(int i = 0; i < length;i++){
            phoneNumber.append(random.nextInt(10));
        }
        return  phoneNumber.toString();
    }

    public static void main(String[] args) {
        System.out.println("Number: " +generatePhoneNumber());
    }
}
