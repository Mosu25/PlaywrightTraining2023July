package org.mosu.day10;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

import java.awt.image.AbstractMultiResolutionImage;

public class TestScriptUsingGetBy {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();
        BrowserType.LaunchOptions headless = new BrowserType.LaunchOptions().setHeadless(false);
        Browser browser = playwright.chromium().launch(headless);
        BrowserContext browserContext = browser.newContext();
        Page page = browserContext.newPage();
        page.setDefaultNavigationTimeout(60000);
        page.navigate("https://bookcart.azurewebsites.net");

        page.getByText("Login").click();
        page.getByLabel("Username").fill("Mosu2023");
        page.getByLabel("Password").fill("Welcome@2023");
//        page.getByText("Login").click(); // Duplicate "Login" text so it showing strict mode violation
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).last().click();  //first(), last(), nth
//        page.getByPlaceholder("Search books or authors").fill("All of Us with Wings"); // it contains based work so if we give "Search books" also work
        page.getByPlaceholder("Search books or authors",
                new Page.GetByPlaceholderOptions().setExact(true)).fill("All of Us with Wings"); // exact match text check
        page.getByRole(AriaRole.OPTION).first().click();
        page.getByAltText("Book cover image").click();
        System.out.println("Page URL = " + page.url());

        //1. getByText() 2. getByLabel() 3. getByRole() 4. getByPlaceholder() 5. getByTitle() 6. getByAltText() 7. getByTestId

        page.close();
        browserContext.close();
        playwright.close();
    }
}
