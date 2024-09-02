package helpers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IdExtractor {


    public static String getID(String str){
        Pattern pattern = Pattern.compile("ID: (\\S+)");
        Matcher matcher= pattern.matcher(str);
        if(matcher.find()){
            return matcher.group(1);
        }else{
            return null;
        }
    }
}
