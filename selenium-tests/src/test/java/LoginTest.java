import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest {

    @Test
    public void validLoginTest() {

        WebDriver driver = new ChromeDriver();

        // Open BYEIT home page
        driver.get(
            "file:///C:/Users/Ram/Desktop/BTECH/TT-A/PS-IV/BYEIT/login-app/index.html"
        );

        // Click Sign In
        driver.findElement(
            By.id("profileButton")
        ).click();

        // Enter email
        driver.findElement(
            By.id("email")
        ).sendKeys("aratrikasharma86@gmail.com");

        // Enter password
        driver.findElement(
            By.id("password")
        ).sendKeys("tulsi");

        // Click Sign In
        driver.findElement(
            By.className("auth-button")
        ).click();

        // Wait for login and redirect
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Verify that user is redirected to home page
        String currentUrl =
            driver.getCurrentUrl();

        assertEquals(
            "file:///C:/Users/Ram/Desktop/BTECH/TT-A/PS-IV/BYEIT/login-app/index.html",
            currentUrl
        );

        // Verify that Profile button is displayed
        String profileButtonText =
            driver.findElement(
                By.id("profileButton")
            ).getText();

        assertEquals(
            "Profile",
            profileButtonText
        );

        System.out.println(
            "Valid login test PASSED!"
        );

        driver.quit();
    }
}

