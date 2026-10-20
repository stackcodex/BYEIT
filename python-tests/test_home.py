
from selenium import webdriver
from selenium.webdriver.chrome.options import Options


def test_open_byeit():

    options = Options()
    options.add_argument("--start-maximized")

    driver = webdriver.Chrome(options=options)

    try:
        driver.get(
            "file:///C:/Users/Ram/Desktop/BTECH/TT-A/PS-IV/BYEIT/login-app/index.html"
        )

        title = driver.title

        assert title == "BYEIT | Shopping"

        print("BYEIT home page test PASSED!")

    finally:
        driver.quit()
