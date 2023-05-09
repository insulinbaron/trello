package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * The type Properties utils.
 */
public final class PropertiesUtils {
    /**
     * The constant INSTANCE.
     */
    private static final PropertiesUtils INSTANCE = new PropertiesUtils();
    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    private PropertiesUtils(){
    }

    /**
     * Get instance properties utils.
     *
     * @return the properties utils
     */
    public static PropertiesUtils getInstance(){
        return INSTANCE;
    }

    /**
     * Получение значения из проперти.
     *
     * @param key the key
     * @return the string
     */
    public static String get(String key){
        return PROPERTIES.getProperty(key);
    }

    private static void loadProperties() {
        try(InputStream input = PropertiesUtils.class.getClassLoader().getResourceAsStream("my.properties")) {
            PROPERTIES.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
