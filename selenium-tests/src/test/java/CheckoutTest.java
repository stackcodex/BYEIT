import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckoutTest {

    @Test
    public void checkoutTest() {

        WebDriver driver = new ChromeDriver();

        // Open BYEIT home page
        driver.get(
            "file:///C:/Users/Ram/Desktop/BTECH/TT-A/PS-IV/BYEIT/login-app/index.html"
        );

        // Find Wireless Headphones + button
        WebElement addButton =
            driver.findElement(
                By.xpath(
                    "//button[contains(@onclick, \"Wireless Headphones\")]"
                )
            );

        // Click product using JavaScript
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].click();",
            addButton
        );

        // Open cart
        driver.findElement(
            By.cssSelector("a[href='cart.html']")
        ).click();

        // Wait for cart page
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Click Proceed to Checkout
        WebElement checkoutButton =
            driver.findElement(
                By.cssSelector(".checkout-button")
            );

        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].click();",
            checkoutButton
        );

        // Wait for checkout page
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Enter full name
        driver.findElement(
            By.id("fullName")
        ).sendKeys("Selenium Test User");

        // Enter email
        driver.findElement(
            By.id("email")
        ).sendKeys("selenium@test.com");

        // Enter phone
        driver.findElement(
            By.id("phone")
        ).sendKeys("9876543210");

        // Enter address
        driver.findElement(
            By.id("address")
        ).sendKeys("123 Test Street");

        // Enter city
        driver.findElement(
            By.id("city")
        ).sendKeys("Mumbai");

        // Enter PIN Code
        driver.findElement(
            By.id("pincode")
        ).sendKeys("400001");

        // Select Cash on Delivery
        WebElement codOption =
            driver.findElement(
                By.cssSelector(
                    "input[name='payment'][value='cod']"
                )
            );

        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].click();",
            codOption
        );

        // Find Place Order button
        WebElement placeOrderButton =
            driver.findElement(
                By.cssSelector(".place-order-button")
            );

        // Click Place Order using JavaScript
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].click();",
            placeOrderButton
        );

        // Wait for order message
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Verify order success message
        String message =
            driver.findElement(
                By.id("checkoutMessage")
            ).getText();

        assertEquals(
            "Order placed successfully!",
            message
        );

        System.out.println(
            "Checkout test PASSED!"
        );

        driver.quit();
    }
}
