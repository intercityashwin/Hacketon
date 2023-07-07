package Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(features="src\\test\\java\\CucumberConfigs\\Features\\test2.feature",
                 glue = "CucumberConfigs",
                 plugin = {"html:target/cucumber.html",
                           "json:target/cucumber.json",
                           "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:","rerun:target/failed_scenarios.txt"}
                 )

public class RunnerTest extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}

