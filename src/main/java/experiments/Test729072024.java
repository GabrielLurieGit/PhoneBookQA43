package experiments;

import helpers.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.security.SecureRandom;

/**
 * some class information
 */
public class Test729072024 {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void generateRandomStringTestPositive(){
        Assert.assertEquals(generateRamdomString(10).length(),12);
    }


    public static void main(String[] args) {
        //System.out.println("My \u001B[34mfirst test!");
        try {
            System.out.println("\u001B[32mGENERATED COMBINATION: " + generateRamdomString(10));
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        //int[] numbers = {2,5,0,12};
//        for(int i = 0; i <10;i++){
//            System.out.println("Number "+i);
//        }
//        byte b1 = 120;  //        byte -128 - 127
//        short s = 32764; // -32768 - 32767
//        int a = 12;
//        float f = 12.2F;
//        double d = 66.2;
//        boolean b = true;
//        char c = 'a';
//        char c2 = 'b';
             // System.out.println("Result = " + theMethodGeneratesAString(2,2));
              //dayPicker(2);
//        System.out.println("INTEGER " + add(12,12));
//        System.out.println("DOUBLE " + add(12.5,12.7));
    }

    /**
     *
     * @param length
     * @return
     */
    public static String generateRamdomString(int length){
        if(length <= 0 || length > 1000){
            throw new IllegalArgumentException("\u001B[31mERROR: Wrong value!");
        }
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder randomString = new StringBuilder(length);
        SecureRandom random = new SecureRandom();
        for(int i = 0; i < length;i++){
            int randomIndex = random.nextInt(characters.length());
            randomString.append(characters.charAt(randomIndex));
        }
        return randomString.toString();
    }

    public static int add(int a, int b){
        return a+b;
    }


    public static double add(double a, double b){
        return a+b;
    }

    /**
     * The method
     * @param a description
     * @param b description
     */
//My commentary
    public static int theMethodGeneratesAString(int a, int b){
        int res = a+b;
        return res;
    }

    public static void dayPicker(int day){
        switch (day){
            case 1:
                System.out.println("Monday");
                break;
            case 2:
                System.out.println("Tuesday");
                break;

            case 3:
                System.out.println("Wednesday");
                break;
            default:
                System.out.println("Invalid day....");
                break;

        }

    }



}
