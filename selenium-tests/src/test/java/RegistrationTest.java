import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationTest {

    @Test
    public void registrationTest() {

        WebDriver driver = new ChromeDriver();

        // Open BYEIT home page
        driver.get(
            "file:///C:/Users/Ram/Desktop/BTECH/TT-A/PS-IV/BYEIT/login-app/index.html"
        );

        // Open Sign In page
        driver.findElement(
            By.id("profileButton")
        ).click();

        // Open Create Account page
        driver.findElement(
            By.linkText("Create Account")
        ).click();

        // Generate a unique email
        String email =
            "selenium" + System.currentTimeMillis() + "@test.com";

        // Enter full name
        driver.findElement(
            By.id("fullName")
        ).sendKeys("Selenium Test User");

        // Enter email
        driver.findElement(
            By.id("email")
        ).sendKeys(email);

        // Enter password
        driver.findElement(
            By.id("password")
        ).sendKeys("Test@12345");

        // Confirm password
        driver.findElement(
            By.id("confirmPassword")
        ).sendKeys("Test@12345");

        // Click Create Account
        driver.findElement(
            By.className("auth-button")
        ).click();

        // Wait for backend response
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Get registration message
        String message =
            driver.findElement(
                By.id("registerMessage")
            ).getText();

        // Verify successful registration
        assertEquals(
            "Registration successful.",
            message
        );

        System.out.println(
            "Registration test PASSED!"
        );

        driver.quit();
    }
}