package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@CucumberOptions(
        features = "src/test/java/features/Screener.feature",
        glue = {"stepDefinition"},
        plugin = {"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
        tags = "@priority1 or @priority2",
        monochrome = true,
        publish = true
)
public class TestRunner extends AbstractTestNGCucumberTests {

        @Override
        @DataProvider(parallel = true)
        public Object[][] scenarios() {
            return super.scenarios();
        }
}
