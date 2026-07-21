import pytest
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager

TEST_URL = "https://the-internet.herokuapp.com/"


@pytest.fixture
def driver():
    service = Service(ChromeDriverManager().install())
    browser = webdriver.Chrome(service=service)
    browser.implicitly_wait(5)
    yield browser
    browser.quit()


def test_by_id(driver):
    driver.get("https://the-internet.herokuapp.com/login")

    username_field = driver.find_element(By.ID, "username")
    password_field = driver.find_element(By.ID, "password")
    login_button = driver.find_element(By.CSS_SELECTOR, "button[type='submit']")

    username_field.send_keys("tomsmith")
    password_field.send_keys("SuperSecretPassword!")
    login_button.click()

    flash_message = driver.find_element(By.ID, "flash")

    assert username_field.get_attribute("id") == "username"
    assert password_field.get_attribute("id") == "password"
    assert "You logged into a secure area!" in flash_message.text


def test_by_name(driver):
    driver.get("https://the-internet.herokuapp.com/login")

    username_by_name = driver.find_element(By.NAME, "username")
    password_by_name = driver.find_element(By.NAME, "password")

    assert username_by_name.get_attribute("id") == "username"
    assert password_by_name.get_attribute("id") == "password"


def test_by_class_name(driver):
    driver.get("https://the-internet.herokuapp.com/")

    heading = driver.find_element(By.CLASS_NAME, "heading")
    assert heading.tag_name == "h1"
    assert heading.text


def test_by_tag_name(driver):
    driver.get("https://the-internet.herokuapp.com/")

    all_links = driver.find_elements(By.TAG_NAME, "a")
    headers = driver.find_elements(By.TAG_NAME, "h1") + driver.find_elements(By.TAG_NAME, "h2")

    assert len(all_links) > 0
    assert len(headers) > 0


def test_by_link_text(driver):
    driver.get("https://the-internet.herokuapp.com/")

    form_auth_link = driver.find_element(By.LINK_TEXT, "Form Authentication")
    form_auth_link.click()

    assert "login" in driver.current_url


def test_by_partial_link_text(driver):
    driver.get("https://the-internet.herokuapp.com/")

    auth_link = driver.find_element(By.PARTIAL_LINK_TEXT, "Authentication")
    dropdown_link = driver.find_element(By.PARTIAL_LINK_TEXT, "Dropdown")

    assert "Form Authentication" in auth_link.text
    assert "Dropdown" in dropdown_link.text


@pytest.mark.parametrize(
    "description, selector",
    [
        ("By ID", "#username"),
        ("By class", ".radius"),
        ("By tag", "button"),
        ("By attribute", "input[type='password']"),
        ("Child selector", "form > div > input"),
        ("Attribute contains", "input[id*='user']"),
        ("Attribute starts with", "input[id^='user']"),
        ("Attribute ends with", "input[id$='name']"),
    ],
)
def test_by_css_selector(driver, description, selector):
    driver.get("https://the-internet.herokuapp.com/login")
    element = driver.find_element(By.CSS_SELECTOR, selector)

    assert element is not None, description


@pytest.mark.parametrize(
    "description, xpath",
    [
        ("By ID", "//input[@id='username']"),
        ("By name", "//input[@name='password']"),
        ("By text", "//h2[text()='Login Page']"),
        ("Contains text", "//a[contains(text(),'Elemental')]"),
        ("By attribute", "//button[@type='submit']"),
        ("By position", "(//input)[1]"),
    ],
)
def test_by_xpath(driver, description, xpath):
    driver.get("https://the-internet.herokuapp.com/login")
    element = driver.find_element(By.XPATH, xpath)

    assert element is not None, description


def test_locator_comparison(driver):
    driver.get("https://the-internet.herokuapp.com/login")

    locators = [
        (By.ID, "username"),
        (By.NAME, "username"),
        (By.CSS_SELECTOR, "#username"),
        (By.CSS_SELECTOR, "input[id='username']"),
        (By.XPATH, "//input[@id='username']"),
        (By.XPATH, "//input[@name='username']"),
    ]

    for by_type, value in locators:
        element = driver.find_element(by_type, value)
        assert element.get_attribute("id") == "username"