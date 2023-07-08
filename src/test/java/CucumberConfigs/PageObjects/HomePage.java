package CucumberConfigs.PageObjects;

import CucumberConfigs.DomainObjects.Entities.Player;
import CucumberConfigs.DomainObjects.Entities.TeamDetails;
import CucumberConfigs.HttpMethods.HttpMethods;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class HomePage {
    private WebDriver driver;
    @FindBy(xpath = "(//a[contains(@onclick,'click_menu(this)')][normalize-space()='STATS'])[2]")
    private WebElement lblStats;

    @FindBy(xpath = "(//a[contains(@onclick,'click_menu(this)')][normalize-space()='TEAMS'])[2]")
    private WebElement lblTeams;

    @FindBy(xpath = "(//div[@class='customSelecBox statsTypeFilter']//div[normalize-space(text())='Aramco Orange Cap'])[1]")
    private WebElement ddStats;


    @FindBy(xpath = "//div[@class='cSBListItemsFilter']//span[normalize-space(text())='BATTERS']")
    private WebElement rbBatters;

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver,this);
    }

    public void clickStats(){
        this.lblStats.click();
    }

    public void clickTeams(){
        this.lblTeams.click();
    }

    public void clickStatsDropDown(){
        this.ddStats.click();
    }

    public boolean isSBatterSelected(){
        return this.rbBatters.getAttribute("class").contains("selected");
    }

    public void clickPlayerByName(String name){
        this.driver.findElement(By.xpath("(//div[contains(text(),'"+name+"')])[1]/parent::a")).click();
    }
    public void clickViewAll(){
        WebDriverWait webDriverWait = new WebDriverWait(this.driver, Duration.ofMillis(50));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(text(),'View All')])[1]")));
        this.driver.findElement(By.xpath("(//a[contains(text(),'View All')])[1]")).click();
    }

    public String hoverOnTeam(String name){
        Actions action = new Actions(this.driver);
        WebElement element = this.driver.findElement(By.xpath("//li[contains(@class,'"+name+"')]//div[@class='trophy-text-align']"));
        action.moveToElement(element).build().perform();
        return element.getText().trim();
    }

    public String getPlayerDetails(List<Integer> lstPlayerIndex){
        TeamDetails teamDetails = new TeamDetails();
        List<Player> players = new ArrayList<Player>();

        for(Integer i : lstPlayerIndex){
            Player player = new Player();
            player.setPlayerName(this.driver.findElement(By.xpath("(//tr[contains(@ng-repeat,'tourBattingStats')])["+i+"]//td[2]//a/div[1]")).getText()); //name
            player.setMatchesPlayed(this.driver.findElement(By.xpath("(//tr[contains(@ng-repeat,'tourBattingStats')])["+i+"]//td[3]")).getText());  // matches
            player.setRunsScored(this.driver.findElement(By.xpath("(//tr[contains(@ng-repeat,'tourBattingStats')])["+i+"]//td[6]")).getText()); // runs
            player.setAverage(this.driver.findElement(By.xpath("(//tr[contains(@ng-repeat,'tourBattingStats')])["+i+"]//td[8]")).getText());  //average
            player.setStrikeRate(this.driver.findElement(By.xpath("(//tr[contains(@ng-repeat,'tourBattingStats')])["+i+"]//td[10]")).getText());  //sr
            player.setHundredsScored(this.driver.findElement(By.xpath("(//tr[contains(@ng-repeat,'tourBattingStats')])["+i+"]//td[11]")).getText());  //cetury
            player.setFiftiesScored(this.driver.findElement(By.xpath("(//tr[contains(@ng-repeat,'tourBattingStats')])["+i+"]//td[12]")).getText());  //50
            players.add(player);
        }
        teamDetails.setplayers(players);
        teamDetails.setTeamName("Team 1");
        teamDetails.setCategory("BATTERS");

        Response response = HttpMethods.post("http://10.244.218.130:8080/batters",teamDetails);
        System.out.println("Player Name is " + response.getBody().asString().trim());
        return response.getBody().asString().trim();
    }

}
