package com.revature.play;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

public class Playwright04TracingTest {
    @Test
    @DisplayName("Basic Tracing Demo")
    public void demoBasicTracing() {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch();
            BrowserContext context = browser.newContext();

            context.tracing().start(
                    new Tracing.StartOptions()
                            .setScreenshots(true)
                            .setSnapshots(true)
                            .setSources(true)
            );
            System.out.println("Tracing started");

            Page page = context.newPage();
            page.navigate("https://the-internet.herokuapp.com/login");

            page.locator("#username").fill("tomsmith");
            page.locator("#password").fill("SuperSecretPassword!");
            page.locator("button[type='submit']").click();

            page.waitForURL("**/secure");

            context.tracing().stop(
                    new Tracing.StopOptions()
                            .setPath(Paths.get("target/login-trace.zip"))
            );

            System.out.println("✅Trace saved to target/login-trace.zip");

            context.close();
            browser.close();
        }
    }

    @Test
    @DisplayName("Advanced Tracing")
    public void demoAdvancedTracing() {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch();
            BrowserContext context = browser.newContext();

            context.tracing().start(new Tracing.StartOptions()
                    .setScreenshots(true)
                    .setSnapshots(true)
                    .setSources(true)
                    .setTitle("Login Flow Test"));

            System.out.println("✅Tracing started with title");
            Page page = context.newPage();

            page.navigate("https://the-internet.herokuapp.com/");
            System.out.println("\tTest scenario 1: Homepage");
            context.tracing().startChunk();

            page.locator("a:has-text('Form Authentication')").click();
            page.locator("#username").fill("tomsmith");
            page.locator("#password").fill("SuperSecretPassword!");
            page.locator("button[type='submit']").click();

            context.tracing().stopChunk(new Tracing.StopChunkOptions()
                    .setPath(Paths.get("target/login-trace-advanced.zip")));
            System.out.println("\t✅Trace saved to target/login-trace-advanced.zip");

            context.tracing().startChunk();
            page.locator("a[href='/logout']").click();
            page.waitForURL("**/login");

            context.tracing().stopChunk(new Tracing.StopChunkOptions()
                    .setPath(Paths.get("target/logout-trace-advanced.zip")));
            System.out.println("\t✅Trace saved to target/logout-trace-advanced.zip");

            context.tracing().stop();
            context.close();
            browser.close();

        }
    }
}
