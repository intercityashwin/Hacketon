package Property;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyLoader {
    public static  PropertyLoader propertyLoader = null;
    public static Properties properties = null;
    private static FileInputStream fileInputStream = null;

    public static Properties getLoader() {
        try{
            if(propertyLoader==null){
                propertyLoader = new PropertyLoader();
                properties = new Properties();
                fileInputStream = new FileInputStream("Automation.properties");
                properties.load(fileInputStream);
                return properties;
            }else {
                return properties;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return properties;
    }

}
