import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BYEITHomeTest {

    @Test
    public void openBYEITTest() {

        WebDriver driver = new ChromeDriver();

        driver.get(
            "file:///C:/Users/Ram/Desktop/BTECH/TT-A/PS-IV/BYEIT/login-app/index.html"
        );

        String title = driver.getTitle();

        assertEquals("BYEIT | Shopping", title);

        System.out.println("BYEIT home page test PASSED!");

        driver.quit();
    }
}