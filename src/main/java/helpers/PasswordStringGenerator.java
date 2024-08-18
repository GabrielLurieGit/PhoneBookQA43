package helpers;

import java.util.Random;

public class PasswordStringGenerator {

    public static void main(String[] args) {
        System.out.println("Password --> " +generateRandomPassword());
    }

    public static String generateRandomPassword(){
        StringBuilder password = new StringBuilder();
        for(int i = 0;i<4;i++){
            char charUpperCase = (char)('A' + Math.random()*('Z'-'A')+1);
            password.append(charUpperCase);
        }
        for(int i = 0; i<4;i++){
            char charLowerCase = (char)('a' + Math.random()*('z'-'a')+1);
            password.append(charLowerCase);
        }
        Random random = new Random();

        for (int i = 0; i<3;i++){
            int digit = random.nextInt(10);
            password.append(digit);
        }
        String specialCharacters = "@$#^&*!";
        int specialCharCount = 1 + random.nextInt(2);
        for(int i = 0;i < specialCharCount; i++){
            int index = random.nextInt(specialCharacters.length());
            char spechar = specialCharacters.charAt(index);
            password.append(spechar);
        }
        return password.toString();
    }
}
