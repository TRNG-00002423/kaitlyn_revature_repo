package com.revature.play;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.ScreenshotType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

public class Playwright03ScreenshotVideoTest {
    @Test
    @Order(1)
    @DisplayName("Playwright Screenshots")
    void demoScreenshots() {
        System.out.println("1. Screenshots");
        System.out.println("-".repeat(40));
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch();
            Page page = browser.newPage();
            page.navigate("https://playwright.dev");

            page.screenshot(new Page.ScreenshotOptions()
                    .setPath(Paths.get("screenshots/fullpage.png"))
                    .setFullPage(true));
            System.out.println("\t✅Full page screenshot: screenshots/fullpage.png");

            page.setViewportSize(1280, 720);
            page.screenshot(new Page.ScreenshotOptions()
                    .setPath(Paths.get("screenshots/viewport-1280x720.png")));
            System.out.println("\t✅Viewport screenshot: screenshots/viewport-1280x720.png");

            Locator header = page.locator("header");
            header.screenshot(new Locator.ScreenshotOptions()
                    .setPath(Paths.get("screenshots/element-header.png")));
            System.out.println("\t✅Element screenshot: screenshots/element-header.png");

            page.screenshot(new Page.ScreenshotOptions()
                    .setPath(Paths.get("screenshots/masked.png"))
                    .setMask(Collections.singletonList(page.locator("header"))));
            System.out.println("\t✅Masked screenshot: screenshots/masked.png");

            byte[] screenshotBytes = page.screenshot();
            System.out.println("\t✅Screenshot as bytes: " + screenshotBytes.length + " bytes");

            page.screenshot(new Page.ScreenshotOptions()
                    .setPath(Paths.get("screenshots/quality.jpg"))
                    .setType(ScreenshotType.JPEG)
                    .setQuality(80));
            System.out.println("\t✅JPEG screenshot: screenshots/quality.jpg");

            browser.close();
        }
    }
    @Test
    @Order(2)
    @DisplayName("Playwright Video Recording")
    void demoVideoRecording() {
        System.out.println("2. Video Recording");
        System.out.println("-".repeat(40));
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(true)
            );
            BrowserContext context = browser.newContext(
                    new Browser.NewContextOptions()
                            .setRecordVideoDir(Paths.get("videos/"))
                            .setRecordVideoSize(1280, 720)
            );

            Page page = context.newPage();
            System.out.println("\tRecording started...");
            page.navigate("https://the-internet.herokuapp.com");
            System.out.println("\tNavigated to homepage");
            page.locator("a:has-text('Form Authentication')").click();
            System.out.println("\tClicked Form Authentication");

            page.locator("#username").fill("tomsmith");
            page.locator("#password").fill("SuperSecretPassword!");
            System.out.println("\tFilled login form");

            page.locator("button[type='submit']").click();
            System.out.println("\tSubmitted form");

            page.waitForTimeout(1000);

            Path videoPath = page.video().path();
            System.out.println("\tVideo will be saved to " + videoPath);

            context.close();
            System.out.println("\t✅Recording complete!");
            browser.close();
        }
    }
}
