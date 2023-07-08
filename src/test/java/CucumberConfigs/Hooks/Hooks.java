package CucumberConfigs.Hooks;

import CucumberConfigs.DriverFactory.DriverFactory;
import Property.PropertyLoader;
import TestContext.Context;
import io.cucumber.java.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Hooks{
    private Context context;
    private static final Integer IMPLICIT_WAIT_TIME = 50;

    private final String url = "http://" + "localhost" +":4444/wd/hub";

    private static Properties properties;
    public Hooks(Context context){
       this.context=context;
    }

    @BeforeAll
    public static void setUp(){
        properties = PropertyLoader.getLoader();
    }

    public static String getPropertyValue(String property){
        return properties.getProperty(property);
    }

    @Before
    public void initializeDriver(){
        try{
            if(getPropertyValue("runOnMobile").toString().equalsIgnoreCase("false")){
                String browser = System.getProperty("browser")==null?getPropertyValue("browser"):System.getProperty("browser").toString();
                boolean isRemoteExecution = getPropertyValue("remoteExecution").toString().equalsIgnoreCase("true")?true:false;
                if(isRemoteExecution){
                    MutableCapabilities dc;
                    dc=browser.equalsIgnoreCase("chrome")?new ChromeOptions():new FirefoxOptions();
                    context.driver = new RemoteWebDriver(new URL(url),dc);
                }else {
                    context.driver = DriverFactory.initializeBrowser(browser);
                }
                context.driver.manage().timeouts().implicitlyWait(Duration.ofMillis(IMPLICIT_WAIT_TIME));
                context.driver.manage().window().maximize();
            }else {
                context.chromeDriver = (ChromeDriver)DriverFactory.initializeBrowser("chrome");
                DevTools devTools = context.chromeDriver.getDevTools();
                devTools.createSession();
                Map<String,Object> dm = new HashMap<>();
                dm.put("width",390);
                dm.put("height",844);
                dm.put("deviceScaleFactor",100);
                dm.put("mobile",true);
                context.chromeDriver.executeCdpCommand("Emulation.setDeviceMetricsOverride",dm);
                context.driver = (ChromeDriver)context.chromeDriver;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @After
    public void tearDown(){
        context.driver.quit();
    }

    @AfterStep
    public void addScreenShot(Scenario scenario) throws IOException {
        //if(scenario.isFailed()){
            File sourcePath = ((TakesScreenshot)context.driver).getScreenshotAs(OutputType.FILE);
            byte [] fileContent = FileUtils.readFileToByteArray(sourcePath);
            scenario.attach(fileContent,"image/png","image");
        //}
    }
}
