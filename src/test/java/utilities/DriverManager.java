package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;

public class DriverManager {

    @BeforeSuite
    public void cleanAllureResults() throws IOException {
       File resultsDir = new File("allure-results");
        if (resultsDir.exists()) {
            FileUtils.cleanDirectory(resultsDir);  // Clean the allure-results directory
        }
    }

    // ThreadLocal to ensure WebDriver instances are separate for each thread in parallel execution
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    // Get WebDriver instance for the current thread
    public static WebDriver getDriver() {
        // if (driver.get() == null) {
        //     System.out.println("No WebDriver instance found, creating a new one...");
        //    //WebDriverManager.chromedriver().setup(); //Not required in case of latest selenium jar

        //     // Automatically downloads and sets up ChromeDriver
        //     driver.set(new ChromeDriver()); // Correctly set the ChromeDriver instance in ThreadLocal
        //     //driver.get().manage().window().maximize();
        //     driver.get().manage().window().setSize(new Dimension(1600, 1080));  // Replace 1920x1080 with your resolution

        // }
         if (driver.get() == null) {
        System.out.println("No WebDriver instance found, creating a new one...");
        
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Run in headless mode
        options.addArguments("--no-sandbox"); // Sandbox issues are common in Docker
        options.addArguments("--disable-dev-shm-usage"); // Overcome limited resource problems
        options.addArguments("--window-size=1600,1080"); // Set desired window size
        
        driver.set(new ChromeDriver(options));
    }
        return driver.get();
    }

    // Quit the WebDriver and remove from ThreadLocal
    public static void quitDriver() {
        WebDriver currentDriver = driver.get();
        if (currentDriver != null) {
            currentDriver.quit();
            driver.remove(); // Ensure ThreadLocal is cleared to prevent memory leaks
        } else {
            System.out.println("No WebDriver instance found for this thread!");
        }
    }
}
