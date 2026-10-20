
import time

from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC


def test_registration():

    options = Options()
    options.add_argument("--start-maximized")
    driver = webdriver.Chrome(options=options)
    wait = WebDriverWait(driver, 10)

    try:
        driver.get("http://127.0.0.1:5500/login-app/index.html")

        # Open Sign In
        wait.until(
            EC.element_to_be_clickable((By.ID, "profileButton"))
        ).click()

        # Open Create Account
        wait.until(
            EC.element_to_be_clickable((By.LINK_TEXT, "Create Account"))
        ).click()

        # Generate a unique email
        email = f"selenium{int(time.time() * 1000)}@test.com"

        # Fill registration form
        fields = {
            "fullName": "Selenium Test User",
            "email": email,
            "password": "Test@12345",
            "confirmPassword": "Test@12345",
        }

        for field_id, value in fields.items():
            wait.until(
                EC.visibility_of_element_located((By.ID, field_id))
            ).send_keys(value)

        # Submit registration
        wait.until(
            EC.element_to_be_clickable((By.CLASS_NAME, "auth-button"))
        ).click()

        # Verify registration message
        message = wait.until(
            EC.visibility_of_element_located((By.ID, "registerMessage"))
        ).text

        assert message == "Registration successful."

        print("Registration test PASSED!")

    finally:
        driver.quit()
