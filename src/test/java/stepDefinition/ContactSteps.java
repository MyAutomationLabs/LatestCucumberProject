package stepDefinition;

import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.ContactPage;
import utilities.DriverManager;
import org.openqa.selenium.WebDriver;

public class ContactSteps {

    WebDriver driver;
    ContactPage contactPage;

    @Given("User is on the Jupiter Toys home page")
    public void user_is_on_the_jupiter_toys_home_page() {
        driver = DriverManager.getDriver();   // get driver from DriverManager
        contactPage = new ContactPage(driver);
        driver.get("http://jupiter.cloud.planittesting.com");
    }

    @When("User navigates to the Contact page")
    public void user_navigates_to_contact_page() {
        contactPage.navigateToContactPage();
    }

    @When("User clicks the Submit button without entering any details")
    public void user_clicks_submit_button_without_entering_any_details() {
        contactPage.clickSubmit();
    }

    @Then("Error messages should be displayed for all mandatory fields")
    public void error_messages_should_be_displayed_for_all_mandatory_fields() {
        Assert.assertTrue(contactPage.areAllErrorsDisplayed(), "Mandatory field errors are not displayed");
    }

    @When("User fills in all mandatory fields")
    public void user_fills_in_all_mandatory_fields() {
        contactPage.fillMandatoryFields("Subrat", "p.subrat88@gmail.com", "This is a test message");
    }

    @When("User clicks the Submit button again")
    public void user_clicks_the_submit_button_again() {
        contactPage.clickSubmit();
    }

    @Then("Error messages should disappear")
    public void error_messages_should_disappear() {
        Assert.assertTrue(contactPage.areAllErrorsGone(), "Error messages did not disappear after filling mandatory fields");
        contactPage.clickBackBtn();

    }

    @Then("Successful submission message should be displayed")
    public void successful_submission_message_should_be_displayed() {
        Assert.assertTrue(contactPage.isSubmissionSuccessful(), "Success message is not displayed");
    }
}
