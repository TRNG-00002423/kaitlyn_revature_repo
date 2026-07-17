package com.bookhaven.ui;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;

public class FirstSeleniumTest extends BaseTest {
    @Test
    @DisplayName("Navigate to Google and verify title")
    void testNavigateToGoogle() {
        driver.get("https://www.google.com");
        String title = driver.getTitle();
        assertTrue(title.contains("Google"), "title should contain Google");
    }
}
