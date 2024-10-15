import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @author Subrat
 */
public class practice {
    public static void main (String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.screener.in/company/INFY/consolidated/");
        System.out.println(driver.getTitle());
    }
}
