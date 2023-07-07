package CucumberConfigs.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class DriverFactory {
    private static Map<String, Supplier<WebDriver>> driverMap = new HashMap<String,Supplier<WebDriver>>();

    public static WebDriver initializeBrowser(String browser){
        return driverMap.get(browser.toLowerCase()).get();
    }

    static {
        driverMap.put("chrome",()->new ChromeDriver());
        driverMap.put("firefox",()->new FirefoxDriver());
        driverMap.put("safari",()->new SafariDriver());
    }
}
