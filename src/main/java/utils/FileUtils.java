package utils;

import java.io.File;

/**
 * The type File utils.
 */
public final class FileUtils {
    private static final FileUtils INSTANCE = new FileUtils();

    private static final String IMAGE_PATH = PropertiesUtils.get("image.path");
    private FileUtils(){}

    /**
     * Get instance file utils.
     *
     * @return the file utils
     */
    public static final FileUtils getInstance(){
        return INSTANCE;
    }

    /**
     * Create file
     *
     * @return the file
     */
    public static File createFileImage() {
        return new File(IMAGE_PATH);
    }



}
