package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class CartPage {

    WebDriver driver;
    WebDriverWait wait;

    private final By buyBtn = By.xpath("/html/body/div[2]/div/ul/li[2]/div/p/a");
    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Navigate to cart page
    public void navigateToCart() {
        driver.findElement(By.cssSelector("a[href*='cart']")).click();
    }

    // Add product by name and quantity
    public void addProduct(String productName, int quantity) {
        for (int i = 0; i < quantity; i++) {
            driver.findElement(By.xpath("//h4[text()='" + productName + "']/following-sibling::p/a")).click();
        }
    }

    // Verify each product's price (unit price)
    public boolean verifyIndividualPrices() {
        // Example: hard-coded prices for simplicity
        double stuffedFrogPrice = 10.99;
        double fluffyBunnyPrice = 9.99;
        double valentineBearPrice = 14.99;

        boolean result = true;

        List<WebElement> rows = driver.findElements(By.cssSelector("table tbody tr"));
        for (WebElement row : rows) {
            String name = row.findElement(By.cssSelector("td:nth-child(1)")).getText();
            double price = Double.parseDouble(row.findElement(By.cssSelector("td:nth-child(2)")).getText().replace("$", ""));
            switch (name) {
                case "Stuffed Frog":
                    if (price != stuffedFrogPrice) result = false;
                    break;
                case "Fluffy Bunny":
                    if (price != fluffyBunnyPrice) result = false;
                    break;
                case "Valentine Bear":
                    if (price != valentineBearPrice) result = false;
                    break;
            }
        }
        return result;
    }

    // Verify total = sum of all subtotals
    public boolean verifyTotalSum() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        double sum = 0.0;

        // Wait until cart rows are visible
        List<WebElement> rows = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("table tbody tr"))
        );

        // Loop through each product row and calculate subtotal sum
        for (WebElement row : rows) {
            String subtotalText = row.findElement(By.cssSelector("td:nth-child(4)")).getText()
                    .replace("$", "")
                    .trim();

            if (!subtotalText.isEmpty()) {
                sum += Double.parseDouble(subtotalText);
            }
        }

        // ✅ Fix: Correct locator for total text at bottom of table (inside <tfoot>)
        WebElement totalElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tfoot/tr/td/strong[contains(text(),'Total:')]"))
        );

        // Extract numeric part from "Total: 116.9"
        String totalText = totalElement.getText().replace("Total:", "").replace("$", "").trim();
        double total = Double.parseDouble(totalText);

        System.out.println("Subtotal sum: " + sum);
        System.out.println("Displayed total: " + total);

        // ✅ Return true if total matches the sum of subtotals (allowing minor rounding diff)
        return Math.abs(sum - total) < 0.01;
    }



    public void isBuyBtnVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(buyBtn)).isDisplayed();
    }

    // Verify subtotal for each product
    // Verify subtotal and total
    public boolean verifySubtotals(Map<String, Integer> expectedProducts, Map<String, Double> productPrices) {

        double expectedTotal = 0.0;
        boolean allSubtotalsCorrect = true;

        for (Map.Entry<String, Integer> entry : expectedProducts.entrySet()) {
            String productName = entry.getKey();
            int quantity = entry.getValue();
            double unitPrice = productPrices.get(productName);

            // Locate subtotal cell for this product
            String subtotalXPath = String.format("//td[normalize-space()='%s']/following-sibling::td[3]", productName);
            WebElement subtotalElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(subtotalXPath)));

            //WebElement subtotalElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='" + productName + "']/following-sibling::td[2]")));

            String subtotalText = subtotalElement.getText().replaceAll("[^\\d.]", "").trim();
            double subtotalValue = Double.parseDouble(subtotalText);

            double expectedSubtotal = unitPrice * quantity;

            if (Math.abs(subtotalValue - expectedSubtotal) > 0.01) { // allow small floating error
                allSubtotalsCorrect = false;
                System.out.println("Subtotal mismatch for " + productName +
                        ": expected " + expectedSubtotal + " but found " + subtotalValue);
            }

            expectedTotal += expectedSubtotal;
        }

        // Validate total
        WebElement totalElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div/form/table/tfoot/tr[1]/td/strong")));
        String totalText = totalElement.getText().trim();
        totalText = totalText.replaceAll("[^\\d.]", "");
        double totalValue = Double.parseDouble(totalText);
        System.out.println("Cart total = " + totalValue);
        if (Math.abs(totalValue - expectedTotal) > 0.01) {
            allSubtotalsCorrect = false;
            System.out.println("Total mismatch: expected " + expectedTotal + " but found " + totalValue);
        }

        return allSubtotalsCorrect;
    }
}
