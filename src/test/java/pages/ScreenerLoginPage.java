package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Subrat
 */
public class ScreenerLoginPage {
    private final WebDriver driver;

    private final By home_loginBtn = By.xpath("//a[@class='button account']");
    private final By email_textbox = By.xpath("//*[@id=\"id_username\"]");
    private final By password_textbox = By.xpath("//*[@id=\"id_password\"]");
    private final By user_loginBtn = By.xpath("//button[@type='submit']");
    private final By profileDrpDwn = By.xpath("//*[@id=\"account-dropdown\"]/button");
    private final By logOut = By.xpath("//*[@id=\"nav-user-menu\"]/form/button");
    private final By errorMsgInvalidLogin = By.xpath("//li[contains(text(),\"Please enter a correct email and password\")]");


    // Constructor
    public ScreenerLoginPage(WebDriver driver) {
        this.driver=driver;
    }

    public  void click_home_login_btn()
    {
        driver.findElement(home_loginBtn).click();
    }
    public  void sendkeys_username() throws InterruptedException {
        driver.findElement(email_textbox).sendKeys("p.subrat88@gmail.com");
    }

    public  void sendkeys_password() throws InterruptedException {
        driver.findElement(password_textbox).sendKeys("Screener@2024");
    }

    public  void click_user_login_btn()
    {
        driver.findElement(user_loginBtn).click();
    }

    public  void click_home_logout_btn() throws InterruptedException {
        driver.findElement(profileDrpDwn).click();
        Thread.sleep(1000);
        driver.findElement(logOut).click();
    }
    public void enterEmail(String email) {
        WebElement emailInput = driver.findElement(email_textbox);
        emailInput.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement passwordInput = driver.findElement(password_textbox);
        passwordInput.sendKeys(password);
    }
    public void errorMessageInvalidLogin()  {
        WebElement errorMsg = driver.findElement(errorMsgInvalidLogin);
        errorMsg.isDisplayed();
    }

    public void waitForPageLoad() throws InterruptedException {
        Thread.sleep(1000);
    }
}
