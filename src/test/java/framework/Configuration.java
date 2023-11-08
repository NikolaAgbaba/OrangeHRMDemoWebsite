package framework;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
    public static String validUsername;
    public static String validPassword;
    public static String url;

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(validUsername);
        System.out.println(validPassword);

    }

    public static void init() throws IOException {
        Properties properties = new Properties();
        FileInputStream inputStream = new FileInputStream("credentials.properties");
        properties.load(inputStream);

        validUsername = properties.getProperty("username");
        validPassword = properties.getProperty("password");
        url = properties.getProperty("url");
    }




}
