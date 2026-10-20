
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC


def test_valid_login():

    options = Options()
    options.add_argument("--start-maximized")

    driver = webdriver.Chrome(options=options)
    wait = WebDriverWait(driver, 10)

    try:
        # Open BYEIT home page
        driver.get(
            "http://127.0.0.1:5500/login-app/index.html"
        )

        # Click Sign In
        wait.until(
            EC.element_to_be_clickable((By.ID, "profileButton"))
        ).click()

        # Enter email
        wait.until(
            EC.visibility_of_element_located((By.ID, "email"))
        ).send_keys("aratrikasharma86@gmail.com")

        # Enter password
        driver.find_element(
            By.ID, "password"
        ).send_keys("tulsi")

        # Click Sign In
        driver.find_element(
            By.CLASS_NAME, "auth-button"
        ).click()

        # Wait for login to finish
        wait.until(
            lambda d: d.find_element(
                By.ID, "profileButton"
            ).text.strip() == "Profile"
        )

        # Verify redirect to home page
        assert driver.current_url == (
            "http://127.0.0.1:5500/login-app/index.html"
        )

        # Verify Profile button text
        profile_button_text = driver.find_element(
            By.ID, "profileButton"
        ).text

        assert profile_button_text.strip() == "Profile"

        print("Valid login test PASSED!")

    finally:
        driver.quit()
