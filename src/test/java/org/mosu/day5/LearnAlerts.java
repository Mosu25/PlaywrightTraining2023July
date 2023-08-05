package org.mosu.day5;

import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Dialog;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.LoadState;

import java.util.function.Consumer;

public class LearnAlerts {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();
        Page page = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)).newPage();
        page.setDefaultNavigationTimeout(60000);
        page.navigate("https://letcode.in/alert");
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);

        Consumer<Dialog> alert = new Consumer<Dialog>() {
            @Override
            public void accept(Dialog dialog) {
                dialog.accept("Mosu");
                page.offDialog(this);  //OffDialog Close then only we use on more A
            }
        };

        page.onDialog(alert);
        page.locator("#prompt").click();

        page.onDialog(a -> {
            System.out.println(a.message()); // print the alert message
            System.out.println(a.defaultValue()); // get the default value of already available input box
            System.out.println(a.type()); // which type alert
            a.accept("M"); // Send the value to alert input box
//            a.accept();  // accept the alert
        });


        //at a time only one onDialog handle only we able to use ,
        // if we need to one more time handle the alert OffDialog and do it.


//        page.locator("#accept").click(); // by default playwright dismiss the alert
//        page.locator("#confirm").click();
        page.locator("#prompt").click();
//        page.locator("#modern").click();


        page.close();
        playwright.close();

    }

}
