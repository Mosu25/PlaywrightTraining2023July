package org.mosu.day1;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class LaunchBrowser {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();

        //Playwright default run on headless mode
        Browser browser = playwright.chromium().launch(
                new BrowserType
                        .LaunchOptions()
                        .setHeadless(false)
                        .setChannel("chrome")
        );
//        Browser browser = playwright.firefox().launch(
//                new BrowserType.LaunchOptions().setHeadless(false)
//        );
//
//        Browser browser = playwright.webkit().launch(
//                new BrowserType.LaunchOptions().setHeadless(false)
//        );

        Page page = browser.newPage();
        page.navigate("https://bookcart.azurewebsites.net");
        System.out.println("Page Title: " + page.title());
        System.out.println("Page URL: " + page.url());

        page.close();
        browser.close();
        playwright.close();

    }

}
