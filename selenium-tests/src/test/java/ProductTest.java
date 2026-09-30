import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductTest {

    @Test
    public void addProductToCartTest() {

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

        // Scroll to the button
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({block: 'center'});",
            addButton
        );

// Click the button using JavaScript
((JavascriptExecutor) driver).executeScript(
    "arguments[0].click();",
    addButton
);

        // Check cart count
        String cartCount =
            driver.findElement(
                By.id("cartCount")
            ).getText();

        // Verify product was added
        assertEquals(
            "1",
            cartCount
        );

        System.out.println(
            "Add product to cart test PASSED!"
        );

        driver.quit();
    }
}