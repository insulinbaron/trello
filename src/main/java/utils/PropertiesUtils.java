package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertiesUtils {
    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    private PropertiesUtils(){
    }

    public static String get(String key){
        return PROPERTIES.getProperty(key);
    }

    private static void loadProperties() {
        try(InputStream input = PropertiesUtils.class.getClassLoader().getResourceAsStream("db.properties");
        InputStream inputStream = PropertiesUtils.class.getClassLoader().getResourceAsStream("my.properties")) {
            PROPERTIES.load(input);
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
