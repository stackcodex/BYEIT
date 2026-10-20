
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC


def test_profile():

    options = Options()
    options.add_argument("--start-maximized")
    driver = webdriver.Chrome(options=options)
    wait = WebDriverWait(driver, 10)

    try:
        driver.get("http://127.0.0.1:5500/login-app/index.html")

        # Set login state for testing
        driver.execute_script("""
            localStorage.setItem('byeItLoggedIn', 'true');
            localStorage.setItem('byeItUserEmail', 'selenium@test.com');
            localStorage.setItem('byeItUserName', 'Selenium Test User');
        """)

        # Reload page to apply login state
        driver.refresh()

        # Open Profile
        wait.until(
            EC.element_to_be_clickable((By.ID, "profileButton"))
        ).click()

        # Verify profile name
        profile_name = wait.until(
            EC.visibility_of_element_located((By.ID, "profileName"))
        ).text

        assert profile_name == "Selenium Test User"

        # Verify email
        email = driver.find_element(By.ID, "profileEmail").text
        assert email == "selenium@test.com"

        # Update profile fields
        updates = {
            "fullName": "Selenium Updated User",
            "phone": "9876543210",
            "city": "Mumbai",
            "address": "123 Test Street",
        }

        for field_id, value in updates.items():
            field = wait.until(
                EC.visibility_of_element_located((By.ID, field_id))
            )
            field.clear()
            field.send_keys(value)

        # Save changes
        save_button = wait.until(
            EC.presence_of_element_located(
                (By.CSS_SELECTOR, "button[type='submit']")
            )
        )
        driver.execute_script("arguments[0].click();", save_button)

        # Verify update message
        message = wait.until(
            EC.visibility_of_element_located((By.ID, "profileMessage"))
        ).text

        assert message == "Profile updated successfully."

        print("Profile test PASSED!")

    finally:
        driver.quit()
