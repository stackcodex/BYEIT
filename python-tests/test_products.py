
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC


def test_add_product_to_cart():

    options = Options()
    options.add_argument("--start-maximized")
    driver = webdriver.Chrome(options=options)
    wait = WebDriverWait(driver, 10)

    try:
        driver.get("http://127.0.0.1:5500/login-app/index.html")

        # Find Wireless Headphones add button
        add_button = wait.until(
            EC.presence_of_element_located(
                (By.XPATH, '//button[contains(@onclick, "Wireless Headphones")]')
            )
        )

        # Scroll to the button
        driver.execute_script(
            "arguments[0].scrollIntoView({block: 'center'});",
            add_button
        )

        # Click using JavaScript
        driver.execute_script("arguments[0].click();", add_button)

        # Verify cart count
        cart_count = wait.until(
            EC.visibility_of_element_located((By.ID, "cartCount"))
        ).text

        assert cart_count == "1"

        print("Add product to cart test PASSED!")

    finally:
        driver.quit()
