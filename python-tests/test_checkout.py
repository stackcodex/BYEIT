
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC


def test_checkout():

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

        # Click Proceed to Checkout
        checkout_button = wait.until(
            EC.element_to_be_clickable((By.CSS_SELECTOR, ".checkout-button"))
        )
        driver.execute_script("arguments[0].click();", checkout_button)

        # Enter delivery details
        fields = {
            "fullName": "Selenium Test User",
            "email": "selenium@test.com",
            "phone": "9876543210",
            "address": "123 Test Street",
            "city": "Mumbai",
            "pincode": "400001",
        }

        for field_id, value in fields.items():
            wait.until(
                EC.visibility_of_element_located((By.ID, field_id))
            ).send_keys(value)

        # Select Cash on Delivery
        cod_option = wait.until(
            EC.presence_of_element_located(
                (By.CSS_SELECTOR, "input[name='payment'][value='cod']")
            )
        )
        driver.execute_script("arguments[0].click();", cod_option)

        # Place order
        place_order = wait.until(
            EC.presence_of_element_located(
                (By.CSS_SELECTOR, ".place-order-button")
            )
        )
        driver.execute_script("arguments[0].click();", place_order)

        # Verify order confirmation
        message = wait.until(
            EC.visibility_of_element_located((By.ID, "checkoutMessage"))
        ).text

        assert message == "Order placed successfully!"

        print("Checkout test PASSED!")

    finally:
        driver.quit()
