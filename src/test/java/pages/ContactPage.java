package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ContactPage {

    private final WebDriver driver;

    // Locators
    //Test Case 1
    private final By contactLink = By.xpath("//a[text()='Contact']");
    private final By forename = By.id("forename");
    private final By email = By.id("email");
    private final By message = By.id("message");
    private final By submitBtn = By.xpath("//a[text()='Submit']");
    private final By forenameError = By.id("forename-err");
    private final By emailError = By.id("email-err");
    private final By messageError = By.id("message-err");
    //Test Case 2
  /*  private final By firstNameField = By.id("forename");
    private final By emailField = By.id("email");
    private final By messageField = By.id("message");
    private final By submitBtn = By.cssSelector("button[type='submit']");*/
    private final By successMsg = By.cssSelector(".alert-success"); // adjust based on actual page
    private final By backBtn = By.xpath("/html/body/div[2]/div/a");


    // ✅ Constructor
    public ContactPage(WebDriver driver) {
        this.driver = driver;
    }

    // ✅ Navigate to Contact Page
    public void navigateToContactPage() {
        driver.findElement(contactLink).click();
    }

    // ✅ Click Submit
    public void clickSubmit() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();

        //driver.findElement(submitBtn).click();
    }

    public void clickBackBtn() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(backBtn)).click();
    }

    // ✅ Verify all mandatory field errors are displayed
    public boolean areAllErrorsDisplayed() {
        return driver.findElement(forenameError).isDisplayed()
                && driver.findElement(emailError).isDisplayed()
                && driver.findElement(messageError).isDisplayed();
    }

    // ✅ Fill all mandatory fields
    public void fillMandatoryFields(String name, String emailId, String msg) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("forename")));

        driver.findElement(forename).sendKeys(name);
        driver.findElement(email).sendKeys(emailId);
        driver.findElement(message).sendKeys(msg);
    }

   /* public void fillMandatoryFields(String name, String email, String message) {
        driver.findElement(firstNameField).sendKeys(name);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(messageField).sendKeys(message);
    }*/

    // ✅ Verify all errors are gone
    public boolean areAllErrorsGone() {
        // Return true if none of the error elements are visible anymore
        boolean forenameVisible = !driver.findElements(forenameError).isEmpty()
                && driver.findElement(forenameError).isDisplayed();
        boolean emailVisible = !driver.findElements(emailError).isEmpty()
                && driver.findElement(emailError).isDisplayed();
        boolean messageVisible = !driver.findElements(messageError).isEmpty()
                && driver.findElement(messageError).isDisplayed();

        return !(forenameVisible || emailVisible || messageVisible);
    }

    public boolean isSubmissionSuccessful() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMsg)).isDisplayed();
    }
}
