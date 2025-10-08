package stepDefinition;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.CartPage;
import pages.ContactPage;
import utilities.DriverManager;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class CartSteps {

    WebDriver driver;
    CartPage cartPage;


    @Before
    public void setUp() {
        driver = DriverManager.getDriver();
        cartPage = new CartPage(driver);
    }
    @Given("User is on the Jupiter Toys shop page")
    public void user_is_on_the_jupiter_toys_shop_page() {
        driver = DriverManager.getDriver();   // get driver from DriverManager
        cartPage = new CartPage(driver);
        driver.get("https://jupiter.cloud.planittesting.com/#/shop");
    }

    @When("User adds products to cart")
    public void user_adds_products_to_cart() {
        cartPage.isBuyBtnVisible();
        cartPage.addProduct("Stuffed Frog", 2);
        cartPage.addProduct("Fluffy Bunny", 5);
        cartPage.addProduct("Valentine Bear", 3);
    }

    @When("User goes to the cart page")
    public void user_goes_to_the_cart_page() {
        cartPage.navigateToCart();
    }

    @Then("Verify subtotal and price for each product")
    public void verify_subtotal_and_price_for_each_product() {

        Map<String, Integer> expectedProducts = new HashMap<>();
        expectedProducts.put("Stuffed Frog", 2);
        expectedProducts.put("Fluffy Bunny", 5);
        expectedProducts.put("Valentine Bear", 3);

        Map<String, Double> productPrices = new HashMap<>();
        productPrices.put("Stuffed Frog", 10.99);
        productPrices.put("Fluffy Bunny", 9.99);
        productPrices.put("Valentine Bear", 14.99);

        boolean result = cartPage.verifySubtotals(expectedProducts, productPrices);
        Assert.assertTrue(result, "Subtotal verification failed for one or more products");
    }


    @Then("Verify total equals sum of subtotals")
    public void verify_total_equals_sum_of_subtotals() {
        Assert.assertTrue(cartPage.verifyTotalSum(), "Total does not equal sum of subtotals");
        //DriverManager.quitDriver();  // Quit driver after cart test completes
    }
}
