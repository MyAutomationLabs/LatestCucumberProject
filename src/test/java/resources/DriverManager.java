package resources;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {

    // ThreadLocal to ensure WebDriver instances are separate for each thread in parallel execution
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    // Get WebDriver instance for the current thread
    public static WebDriver getDriver() {
        if (driver.get() == null) {
            System.out.println("No WebDriver instance found, creating a new one...");
            WebDriverManager.chromedriver().setup();
            // Automatically downloads and sets up ChromeDriver
            driver.set(new ChromeDriver()); // Correctly set the ChromeDriver instance in ThreadLocal
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
