package stepDefinition;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.ScreenerLoginPage;
import resources.DriverManager;

public class ScreenerLogin {

    private WebDriver driver;
    private ScreenerLoginPage scrLogin;

    // This method is called before each scenario to set up the WebDriver
    @Before
    public void setup() {
        driver = DriverManager.getDriver();  // Ensures driver is initialized from DriverManager
        scrLogin = new ScreenerLoginPage(driver);// Initialize page object after driver setup
    }

    @Given("User navigates to the Screener Login page")
    @Step("User navigates to the login page")
    public void user_navigates_to_the_screener_login_page() throws InterruptedException {
        System.out.println("Navigating to the Screener Login page...");
        driver.get("https://www.screener.in/");
        scrLogin.waitForPageLoad();  // Wait for the page to load
        attachScreenshot();
    }

    @Given("User is on the Screener Login page")
    @Step("User is on the Screener Login Page")
    public void user_is_on_the_screener_login_page() {
        String pageTitle = driver.getTitle();
        Assert.assertTrue(pageTitle.contains("Stock Screener"), "Page title contain expected text!");
        attachScreenshot();
    }

    @When("User clicks on Login button")
    @Step("User Clicks Login Button")
    public void user_clicks_on_login_button() {
        scrLogin.click_home_login_btn();
        attachScreenshot();
    }

    @Then("User should be able to view the Screener Login page")
    @Step("User is on the Home Screener Login Page")
    public void user_should_be_able_to_view_the_screener_login_page() {
        String pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle, "Login - Screener");
        attachScreenshot();
    }

    @Then("User able to enter valid Username and Password to land to home page or use login using google")
    @Step("User enters Valid Username and Password")
    public void user_able_to_enter_valid_username_and_password_to_land_to_home_page_or_use_login_using_google() throws InterruptedException {
        scrLogin.sendkeys_username();
        scrLogin.sendkeys_password();
        scrLogin.click_user_login_btn();
        attachScreenshot();
    }

    @Given("I am on Screener Login Page")
    @Step("User on the Screener Login Page")
    public void i_am_on_screener_login_page() throws InterruptedException {
        scrLogin.click_home_logout_btn();
        attachScreenshot();
    }

    @Given("I have entered invalid {string} and {string}")
    @Step("User entered Invalid Login Credentials")
    public void i_have_entered_invalid_and(String username, String password) {
        scrLogin.click_home_login_btn();
        scrLogin.enterEmail(username);
        scrLogin.enterPassword(password);
        attachScreenshot();
    }

    @When("I click on the login button")
    @Step("User Clicks on Login Button")
    public void i_click_on_the_login_button() {
        scrLogin.click_user_login_btn();
    }

    @Then("I should see an error message indicating {string}")
    @Step("User see Error Message")
    public void i_should_see_an_error_message_indicating(String errorMessage) throws InterruptedException {
        scrLogin.errorMessageInvalidLogin();
        attachScreenshot();
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public void attachScreenshot() {
        ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @After
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
