package CucumberConfigs.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static CucumberConfigs.Hooks.Hooks.getPropertyValue;

public class LoginPage{
    private static final Integer IMPLICIT_WAIT = 30;
    private WebDriver driver;
    @FindBy(css ="input.email")
    private WebElement txtEmail;

    @FindBy(css ="input#Password")
    private WebElement txtPassword;

    @FindBy(css = "a.ico-login")
    private WebElement btnLogin;

    public LoginPage(WebDriver driver){
        super();
        this.driver = driver;
        PageFactory.initElements(this.driver,this);
    }

    public void login(String email,String password){
        this.driver.get(getPropertyValue("url"));
        btnLogin.click();
        this.driver.findElement(By.cssSelector("a.ico-login")).click();
        txtEmail.sendKeys(email);
        txtPassword.sendKeys(password);
    }

    public String getTitle(){
        return  driver.getTitle();
    }

}
