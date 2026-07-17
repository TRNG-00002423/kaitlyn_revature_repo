package com.revature;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

// These are the simplest Selenium tests.
// WebDriver is the main interface for browser interaction
// Always close the driver when done (@AfterEach)
// get() navigates to URL, getTitle() gets the page title...

@DisplayName("First Selenium WebDriver Tests")
public class DemoSeleniumFirstTest {
    private WebDriver driver;
    // for now we'll use manual WebDriver setup

    // ChromeDriver requires:
    // Chrome browser installed
    // ChromeDriver executable (matching Chrome version)
    // System property set OR ChromeDriver in PATH

    @BeforeEach
    void setup() {
        // WebDriverManager.chromedriver.setup()
        // could need above
        driver = new ChromeDriver();

        // Maximize browser window
        driver.manage().window().maximize();
    }

    @AfterEach
    void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Navigate to webpage and display title")
    void navigateToWebsite_verifyTitle() {
        // 1. open a URL
        // 2. get page title
        // 3. assert it matches expected
        driver.get("https://selenium.dev/");
        String title = driver.getTitle();
        System.out.println("Page title: " + title);
        assertTrue(title.contains("Selenium"), "title should contain Selenium");
    }

    @Test
    @DisplayName("Get current URL after navigation")
    void navigateToWebsiteVerifyURL() {
        // getCurrentURL() returns the current page URL
        // useful for verifying redirects or navigation
        driver.get("https://selenium.dev/documentation");
        String currentURL = driver.getCurrentUrl();
        System.out.println("current URL: " + currentURL);
        assertTrue(currentURL.contains("documentation"));
    }

    @Test
    @DisplayName("Get page source")
    void getPageSource_getExpectedContent() {
        driver.get("https://selenium.dev/");
        String pageSource = driver.getPageSource(); // gets raw HTML
        assertTrue(pageSource.contains("Selenium"));

        System.out.println("Page source (first 500 characters):");

    }

}
