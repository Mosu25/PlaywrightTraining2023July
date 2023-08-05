package org.mosu.day7;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;

public class LearnBrowserContext {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();
        BrowserType chromium = playwright.chromium();
        BrowserType.LaunchOptions headless = new BrowserType.LaunchOptions().setHeadless(false);
        Browser browser = chromium.launch(headless);
        BrowserContext browserContext = browser.newContext();
        Page page = browserContext.newPage();

        page.setDefaultNavigationTimeout(60000);
        page.navigate("https://bookcart.azurewebsites.net");
        page.click("//span[text()='Login']");
        page.fill("input[data-placeholder='Username']","Mosu2023");
        page.fill("input[data-placeholder='Password']","Welcome@2023");
        page.click("(//span[text()='Login'])[2]");
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
        String username = page.locator("//button[contains(@class,'mat-focus-indicator mat-menu-trigger')]//span[1]")
                .textContent();
        System.out.println("Username: " + username.split(" ")[1]);

        Page page1 = browserContext.newPage(); // opening new tab ..cookie and cache is available
        page1.navigate("https://bookcart.azurewebsites.net");
        page1.fill("input[type='search']","The Help");
        String username1 = page.locator("//button[contains(@class,'mat-focus-indicator mat-menu-trigger')]//span[1]")
                .textContent();
        System.out.println("Username2: " + username1.split(" ")[1]);

//        BrowserContext browserContext2 = browser.newContext(); //opening for new window cookies and cache not maintain
//        Page page2 = browserContext2.newPage();
//        page2.navigate("https://bookcart.azurewebsites.net");
//        String username2 = page.locator("//button[contains(@class,'mat-focus-indicator mat-menu-trigger')]//span[1]")
//                .textContent();
//        System.out.println("Username2: " + username2.split(" ")[1]);


//        playwright.close();
    }
}
