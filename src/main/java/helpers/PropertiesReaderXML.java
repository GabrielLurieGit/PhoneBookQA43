package helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReaderXML {
    public static void main(String[] args) {
        System.out.println(getProperties("mykey","src/main/resources/data.xml"));
    }

    public static String getProperties(String key,String path){
        Properties properties = new Properties();
        try(FileInputStream fileInputStream = new FileInputStream(path)){
            properties.loadFromXML(fileInputStream);
            return  properties.getProperty(key);
        }catch (IOException exception){
            exception.printStackTrace();
            return null;
        }
    }
}
