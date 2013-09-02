package it.sevenbits.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: sevenbits
 * Date: 9/2/13
 * Time: 1:37 PM
 * To change this template use File | Settings | File Templates.
 */
final class ControllerUtils {
    public static int getCountRow(Class resourceClass) {
        Properties prop = new Properties();
        try {
            InputStream inStream = resourceClass.getClassLoader().getResourceAsStream("common.properties");
            prop.load(inStream);
            inStream.close();
        } catch (IOException e) {
            return DEFAULT_COUNT_ROWS_TABLE;
        }
        return Integer.parseInt(prop.getProperty("countrows"));
    }

    private static final int DEFAULT_COUNT_ROWS_TABLE = 10;
}
