
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC


def test_cart():

    options = Options()
    options.add_argument("--start-maximized")
    driver = webdriver.Chrome(options=options)
    wait = WebDriverWait(driver, 10)

    try:
        driver.get("http://127.0.0.1:5500/login-app/index.html")

        # Add Wireless Headphones
        add_button = wait.until(
            EC.presence_of_element_located(
                (By.XPATH, '//button[contains(@onclick, "Wireless Headphones")]')
            )
        )
        driver.execute_script("arguments[0].click();", add_button)

        # Open cart
        wait.until(
            EC.element_to_be_clickable((By.CSS_SELECTOR, "a[href='cart.html']"))
        ).click()

        # Verify product name
        product_name = wait.until(
            EC.visibility_of_element_located((By.CLASS_NAME, "cart-item-name"))
        ).text

        assert product_name == "Wireless Headphones"

        # Verify quantity
        quantity = driver.find_element(
            By.CSS_SELECTOR, ".quantity-control span"
        ).text

        assert quantity == "1"

        # Verify price
        price = driver.find_element(
            By.CLASS_NAME, "cart-item-price"
        ).text

        assert price == "₹2499"

        print("Cart test PASSED!")

    finally:
        driver.quit()
