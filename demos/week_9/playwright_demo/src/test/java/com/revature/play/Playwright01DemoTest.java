package com.revature.play;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Playwright01DemoTest {
    @Test
    @DisplayName("Basic Playwright Setup")
    void basicTest() {
        System.out.println("\uD83C\uDFAD PLAYWRIGHT SETUP DEMO");
        System.out.println("===========================");
        System.out.println("1. Creating Playwright instance...");
        try (Playwright playwright = Playwright.create()) {

            System.out.println("2. Launching browser...");
            // Launch Chromium browser
            // Default is headless=true; set to false to see browser
            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions()
                            .setHeadless(false)  // Show browser for demo
                            .setSlowMo(500)      // Slow down for visibility
            );

            System.out.println("3. Creating page...");
            // Create a new page (tab)
            Page page = browser.newPage();

            System.out.println("4. Navigating to website...");
            page.navigate("https://playwright.dev");
            String title = page.title();
            System.out.println("\tPage title: " + title);
            System.out.println("\tURL: " + page.url());

            System.out.println("5. Interacting with page...");
            page.locator("a:has-text('Get started')").click();
            assertThat(page).hasURL(java.util.regex.Pattern.compile(".*intro"));
            System.out.println("\tNavigated to: " + page.url());

            System.out.println("6. Taking a screenshot...");
            page.screenshot(new Page.ScreenshotOptions()
                    .setPath(java.nio.file.Paths.get("screenshot.png")));
            System.out.println("\tScreenshot saved: screenshot.png");

            System.out.println("7. Closing browser...");
            browser.close();
        }

        System.out.println("Demo completed successfully!");


    }

}
