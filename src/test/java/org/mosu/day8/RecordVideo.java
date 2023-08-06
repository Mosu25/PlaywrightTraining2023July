package org.mosu.day8;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

import java.nio.file.Paths;

public class RecordVideo {
    @Test
    public void recordVideo() {

        Playwright playwright = Playwright.create();
        BrowserType.LaunchOptions headless = new BrowserType.LaunchOptions().setHeadless(false);
        Browser browser = playwright.chromium().launch(headless);
        BrowserContext browserContext = browser.newContext(new Browser.NewContextOptions()
                                        .setRecordVideoDir(Paths.get("Videos/"))
                                        .setRecordVideoSize(1280,720));
        Page page = browserContext.newPage();
        page.setDefaultNavigationTimeout(60000);
        page.navigate("https://www.google.com");
        page.fill("textarea[name='q']","Welcome to india");
        page.press("(//input[@name='btnK'])[2]","Enter");
//        page.click("(//input[@name='btnK'])[2]");
        System.out.println("Page title = " + page.title());

        browserContext.close();
        playwright.close();

    }
}
