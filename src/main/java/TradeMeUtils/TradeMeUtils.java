package TradeMeUtils;

import TradeMePages.TradeMeBase;

import java.io.FileInputStream;
import java.util.Properties;

public class TradeMeUtils extends TradeMeBase {

    public static String getConfigValue(String key) {
        Properties config = new Properties();

        try {
            String filename = "";
            if (System.getProperty("os.name").contains("Win")) {
                filename = "properties\\config";
            } else {
                filename = "properties/config";
            }
            config.load(new FileInputStream("target/classes/" + filename));
        } catch (Throwable t) {
            System.out.print("Issue loading properties file");
            t.printStackTrace();
        }
        return config.getProperty(key);
    }

    
}
