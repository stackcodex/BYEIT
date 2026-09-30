import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProfileTest {

    @Test
    public void profileTest() {

        WebDriver driver = new ChromeDriver();

        // Open BYEIT home page
        driver.get(
            "file:///C:/Users/Ram/Desktop/BTECH/TT-A/PS-IV/BYEIT/login-app/index.html"
        );

        // Set login state for testing the profile page
        ((JavascriptExecutor) driver).executeScript(
            "localStorage.setItem('byeItLoggedIn', 'true');"
        );

        ((JavascriptExecutor) driver).executeScript(
            "localStorage.setItem('byeItUserEmail', 'selenium@test.com');"
        );

        ((JavascriptExecutor) driver).executeScript(
            "localStorage.setItem('byeItUserName', 'Selenium Test User');"
        );

        // Refresh page so login state is loaded
        driver.navigate().refresh();

        // Click Profile button
        driver.findElement(
            By.id("profileButton")
        ).click();

        // Wait for profile page
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Verify profile name
        String profileName =
            driver.findElement(
                By.id("profileName")
            ).getText();

        assertEquals(
            "Selenium Test User",
            profileName
        );

        // Verify email
        String email =
            driver.findElement(
                By.id("profileEmail")
            ).getText();

        assertEquals(
            "selenium@test.com",
            email
        );

        // Change full name
        WebElement fullName =
            driver.findElement(
                By.id("fullName")
            );

        fullName.clear();

        fullName.sendKeys(
            "Selenium Updated User"
        );

        // Enter phone
        driver.findElement(
            By.id("phone")
        ).clear();

        driver.findElement(
            By.id("phone")
        ).sendKeys("9876543210");

        // Enter city
        driver.findElement(
            By.id("city")
        ).clear();

        driver.findElement(
            By.id("city")
        ).sendKeys("Mumbai");

        // Enter address
        driver.findElement(
            By.id("address")
        ).clear();

        driver.findElement(
            By.id("address")
        ).sendKeys("123 Test Street");

        // Click Save Changes
        WebElement saveButton =
            driver.findElement(
                By.cssSelector("button[type='submit']")
            );

        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].click();",
            saveButton
        );

        // Wait for success message
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Verify profile update message
        String message =
            driver.findElement(
                By.id("profileMessage")
            ).getText();

        assertEquals(
            "Profile updated successfully.",
            message
        );

        System.out.println(
            "Profile test PASSED!"
        );

        driver.quit();
    }
}
