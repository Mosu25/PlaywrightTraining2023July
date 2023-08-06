package day9;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;

import java.nio.file.Paths;

public class TracingDemo {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();
        BrowserType.LaunchOptions headless = new BrowserType.LaunchOptions().setHeadless(false);
        Browser browser = playwright.chromium().launch(headless);
        BrowserContext browserContext = browser.newContext();
        browserContext.tracing().start(
                new Tracing.StartOptions()
                        .setScreenshots(true)
                        .setSnapshots(true)
                        .setSources(true)  // need to run cmd or gitbash or powershall 1.mvn compile
                //2. PLAYWRIGHT_JAVA_SRC="src/main/java/" mvn test -Ptrace
                    //view the trace 3. mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="show-trace trace.zip"
        );
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

        browserContext.tracing().stop(
                new Tracing.StopOptions()
                        .setPath(Paths.get("Trace.zip"))
        );

        browserContext.close();
        playwright.close();


    }
}
