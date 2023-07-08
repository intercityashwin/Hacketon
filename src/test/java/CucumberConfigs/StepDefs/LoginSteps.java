package CucumberConfigs.StepDefs;

import CucumberConfigs.PageFactory.PageFactory;
import CucumberConfigs.PageObjects.LoginPage;
import TestContext.Context;
import io.cucumber.java.en.And;
import org.testng.Assert;

public class LoginSteps {

    private Context context;

    public LoginSteps(Context context){
        this.context=context;
    }
    @And("login details are")
    public void setLoginDetails() {
        System.out.println("login details are");
    }

    @And("user is logs in to the application")
    public void loginToApplication() {
        LoginPage loginPage = PageFactory.getInstance(LoginPage.class,this.context.driver);
        loginPage.login("intercityashwin@gmail.com","Chennai@12163");
        System.out.println(loginPage.getTitle());
        Assert.assertTrue(true);
        System.out.println("user is logs in to the application");
    }
}
