package CucumberConfigs.StepDefs;

import CucumberConfigs.HttpMethods.HttpMethods;
import CucumberConfigs.PageFactory.PageFactory;
import CucumberConfigs.PageObjects.HomePage;
import TestContext.Context;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.sl.In;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static CucumberConfigs.Hooks.Hooks.getPropertyValue;

public class LoginSteps {

    private Context context;
    private WebDriver driver;

    private ChromeDriver chromeDriver;

    public LoginSteps(Context context){
        this.context=context;
        this.driver=this.context.driver;
    }
    @When("user navigates to stats")
    public void setLoginDetails() {
        HomePage homePage = PageFactory.getInstance(HomePage.class,this.context.driver);
        homePage.clickStats();
        homePage.clickStatsDropDown();
    }

    @When("user navigates to teams and validate the status code")
    public void setLoginToTeams() {
        boolean found = false;
        HomePage homePage = PageFactory.getInstance(HomePage.class,this.context.driver);
        homePage.clickTeams();
        Response response = HttpMethods.get("http://10.244.218.130:8080/team");
        System.out.println("Team Name is " + response.getBody().asString().trim());
        String teamName = response.getBody().asString().trim();
        List<String> lstTeamsWithRecords =Arrays.asList("CSK","GT","KKR","MI","RR","SRH");
        for(String s : lstTeamsWithRecords){
            if(teamName.contains(s)){
                found = true;
                break;
            }
        }
        if(found){
            String years = homePage.hoverOnTeam(teamName);
            Response responsePost = HttpMethods.post("http://10.244.218.130:8080/teamTrophies",yearDetails(years,teamName));
            System.out.println(responsePost.getStatusCode());
            System.out.println("Team Name is " + response.getBody().asString().trim());
            Assert.assertEquals(responsePost.getStatusCode(),200);
        }else {
            System.out.println("Team " +teamName + " has not won any trophy !!!" );
        }

    }

    @Then("user validates the batter drop down is selected")
    public void validateBatter(){
        HomePage homePage = PageFactory.getInstance(HomePage.class,this.context.driver);
        Assert.assertTrue(homePage.isSBatterSelected());
    }


    @Then("user gets the player details")
    public void testApi(){
        int a = 0;
        int b = 1;
        int c = 0;
        List<Integer> lstPlayers = new ArrayList<Integer>();
            for (int i = 2; i <= 40; i++) {
                c = a + b;
                a = b;
                b = c;
                if(b%2==0 && b<=20)
                    lstPlayers.add(b);
            }
        System.out.println(lstPlayers);

        HomePage homePage = PageFactory.getInstance(HomePage.class,this.context.driver);
        String getPlayer = homePage.getPlayerDetails(lstPlayers);
        homePage.clickPlayerByName(getPlayer);
    }

    @And("user launch the application")
    public void loginToApplication() {
        this.context.driver.get(getPropertyValue("url"));
    }


    private static String yearDetails(String years, String teamName){
        String yearsPayload = years.replace("|",",").replace(" ","");
        return "{\n" +
                "    \"years\": \""+yearsPayload+"\",\n" +
                "    \"teamName\": \""+teamName+"\"\n" +
                "}\n";

    }
}
