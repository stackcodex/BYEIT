import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartTest {

    @Test
    public void cartTest() {

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

        // Click the product using JavaScript
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].click();",
            addButton
        );

        // Open cart using the Cart link
        driver.findElement(
            By.cssSelector("a[href='cart.html']")
        ).click();

        // Wait for cart page to load
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Verify product name
        String productName =
            driver.findElement(
                By.className("cart-item-name")
            ).getText();

        assertEquals(
            "Wireless Headphones",
            productName
        );

        // Verify quantity
        String quantity =
            driver.findElement(
                By.cssSelector(".quantity-control span")
            ).getText();

        assertEquals(
            "1",
            quantity
        );

        // Verify product price
        String price =
            driver.findElement(
                By.className("cart-item-price")
            ).getText();

        assertEquals(
            "₹2499",
            price
        );

        System.out.println(
            "Cart test PASSED!"
        );

        driver.quit();
    }
}