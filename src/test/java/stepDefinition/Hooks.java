package stepDefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import utilities.DriverManager;

public class Hooks {

    @Before
    public void setUp() {
        // Get a new driver instance per scenario
        WebDriver driver = DriverManager.getDriver();
        driver.manage().deleteAllCookies();
        driver.get("http://jupiter.cloud.planittesting.com");
        if (DriverManager.getDriver() == null) {
            DriverManager.getDriver(); // initializes driver if null
            driver.manage().deleteAllCookies();
            driver.get("http://jupiter.cloud.planittesting.com");
        }

    }

    @After
    public void tearDown() {
        // Quit and remove driver for current thread
        //DriverManager.quitDriver();
    }
}
