package org.mosu.day2;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;

public class LearnInputs {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(false)
                        .setChannel("chrome")
        );
        Page page = browser.newPage();
        page.setDefaultNavigationTimeout(60000);
        page.navigate("https://letcode.in/edit");
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
//        page.locator("#fullName").fill("Mosu"); // clear the input box and fill the text in input box, look like copy paste
//        page.locator("#fullName").type("Mosu"); // Append the text in input box, look like type the text sequence

        page.fill("#fullName","Mosu"); // Locator finding and input text value both in single method

        Locator locator = page.locator("#join");
        locator.press("End");
        locator.type(" man");
        locator.press("Tab");

        System.out.println("Get Attribute value:  " + page.locator("id=getMe").getAttribute("value")); // get the attribute value

        page.locator("//input[@value='Koushik Chatterjee']").clear(); // clear the text box value


        page.close();
        browser.close();
        playwright.close();

    }

}
