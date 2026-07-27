package com.revature.play;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.SelectOption;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@DisplayName("Playwright Interactions")
public class Playwright02InteractionsTest extends BaseTest{
    @Test
    @DisplayName("Demo Auto Wait")
    @Order(1)
    public void demoAutoWait() {
        System.out.println("1. Auto-wait demonstration");
        System.out.println("-".repeat(40));
        navigateTo("/dynamic_loading/1");
        System.out.println("\tClicking 'Start' button...");
        page.locator("#start button").click();
        System.out.println("\tWaiting for result... (auto-wait)");
        String result = page.locator("#finish h4").textContent();
        System.out.println("\tResult: " + result);
        System.out.println("\tNo explicit wait needed!\n");
    }

    @Test
    @DisplayName("Locator Strategies")
    @Order(2)
    public void demoLocators() {
        System.out.println("2. Locator strategies");
        System.out.println("-".repeat(40));
        navigateTo("/login");

        Locator byId = page.locator("#username");
        System.out.println("\tBy id: #username");

        Locator byText = page.locator("text=Super");
        System.out.println("\tText contains \"Super\"");

        Locator byRole = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Login"));
        System.out.println("\tBy role: button named \"Login\"");

        Locator byLabel = page.getByLabel("Username");
        System.out.println("\tBy label: \"Username\"");

        Locator chained = page.locator("form").locator("button");
        System.out.println("\tChained: form > button\n");
    }

    @Test
    @DisplayName("Form Interactions")
    @Order(3)
    void demoFormInteractions() {
        System.out.println("3. Form Interactions");
        System.out.println("-".repeat(40));
        navigateTo("/login");

        page.locator("#username").fill("tomsmith");
        System.out.println("\tFilled username");

        page.locator("#password").fill("SuperSecretPassword!");
        System.out.println("\tFilled password");

        page.locator("button[type='submit']").click();
        System.out.println("\tClicked submit button");

        assertThat(page.locator("#flash")).containsText("secure area");
        System.out.println("\t✅ Logged in\n");

        navigateTo("");

        page.locator("a:has-text('Checkboxes')").click();

        Locator checkbox1 = page.locator("input[type='checkbox']").first();
        Locator checkbox2 = page.locator("input[type='checkbox']").last();

        if (!checkbox1.isChecked()) {
            checkbox1.check();
            System.out.println("\tChecked first checkbox");
        }

        if (checkbox2.isChecked()) {
            checkbox2.uncheck();
            System.out.println("\tUnchecked second checkbox");
        }

        navigateTo("/dropdown");

        page.locator("#dropdown").selectOption("1");
        System.out.println("\tSelected option 1");
        page.locator("#dropdown").selectOption(
                new SelectOption().setLabel("Option 2")
        );
        System.out.println("Selected \"Option 2\" by label\n");
    }

    @Test
    @DisplayName("Web-First Assertions")
    @Order(4)
    void demoAssertions() {
        System.out.println("4. Web-first assertions");
        System.out.println("-".repeat(40));

        navigateTo("");

        assertThat(page).hasTitle("The Internet");
        System.out.println("\t✅Page has expected title");

        assertThat(page).hasURL(Pattern.compile(".*herokuapp."));
        System.out.println("\t✅URL matches pattern");

        Locator heading = page.locator("h1.heading");

        assertThat(heading).isVisible();
        System.out.println("\t✅Heading is visible");

        assertThat(heading).hasText("Welcome to the-internet");
        System.out.println("\t✅Heading has correct text");

        assertThat(heading).hasAttribute("class", "heading");
        System.out.println("\t✅Heading has class attribute");

        Locator links = page.locator("ul li a");
        assertThat(links).hasCount(44);
        System.out.println("\t✅Found expected number of links");

        assertThat(page.locator(".non-existent")).not().isVisible();
        System.out.println("\t✅Non-existent element is not visible");

    }

}
