package org.mosu.day4;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

public class CodegenTestScript {

    public static void main(String[] args) {


//        CLI Comments:
//        mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="codegen https://bookcart.azurewebsites.net"
//        Preserve authenticated state:
//        mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="codegen github.com/microsoft/playwright  --save-storage=auth.json"
//        Load authenticated state:
//        mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="codegen --load-storage=auth.json github.com/microsoft/playwright"
//        Emulate viewport size:
//        mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="codegen --viewport-size=800,600 https://bookcart.azurewebsites.net"
//        Emulate devices:
//        mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args='codegen --device="iPhone 13" https://bookcart.azurewebsites.net'
//        Emulate color scheme:
//        mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="codegen --color-scheme=dark https://bookcart.azurewebsites.net"
//        Emulate geolocation, language and timezone:
//        mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args='codegen --timezone="Europe/Rome" --geolocation="41.890221,12.492348" --lang="it-IT" bing.com/maps'
//        PDF:
//        mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="pdf https://bookcart.azurewebsites.net bookcart.pdf"

        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false));
            BrowserContext context = browser.newContext();
            Page page = context.newPage();
            page.navigate("https://bookcart.azurewebsites.net/");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
            page.getByLabel("Username *").fill("Mosu2023");
            page.getByLabel("Username *").press("Tab");
            page.getByLabel("Password *").fill("Welcome@2023");
            page.locator("mat-card-actions").getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Login")).click();
            page.getByPlaceholder("Search books or authors").fill("Roomies");
            page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName("Roomies")).getByText("Roomies").click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add to Cart")).click();
            page.getByRole(AriaRole.BUTTON).filter(new Locator.FilterOptions().setHasText("shopping_cart1")).click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Clear cart")).click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Mosu2023")).click();
            page.getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions().setName("Logout")).click();
        }
    }
}
