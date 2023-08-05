package org.mosu.day6;

import com.microsoft.playwright.*;

public class HttpAuthentication {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();
//        Browser browser = playwright.chromium().launch();
//        BrowserContext browserContext = browser.newContext(new Browser.NewContextOptions().setHttpCredentials(
//                "admin", "admin");
//        Page page = browserContext.newPage();
//        page.setDefaultNavigationTimeout(60000);
//        page.navigate("https://the-internet.herokuapp.com/basic_auth");
//        System.out.println("Authentication success message: " + page.locator("p").textContent());

        // Disable headless mode
        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions();
        options.setHeadless(false);

        Browser browser = playwright.chromium().launch(options);
        BrowserContext browserContext = browser.newContext(new Browser.NewContextOptions()
                .setHttpCredentials("admin","admin"));
        Page page = browserContext.newPage();
        page.setDefaultNavigationTimeout(60000);
        page.navigate("https://the-internet.herokuapp.com/basic_auth");
        // Get the authentication success message
        String authenticationSuccessMessage = page.locator("p").textContent();
        System.out.println("Authentication success message: " + authenticationSuccessMessage);

        playwright.close();
    }
}
