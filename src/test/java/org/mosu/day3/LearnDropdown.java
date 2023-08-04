package org.mosu.day3;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.SelectOption;

import java.util.List;

public class LearnDropdown {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();
        BrowserType.LaunchOptions headless = new BrowserType.LaunchOptions().setHeadless(false);
        Page page = playwright.chromium().launch(headless).newPage();
        page.setDefaultNavigationTimeout(60000);
        page.navigate("https://letcode.in/dropdowns");
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
//        page.locator("#fruits").selectOption("2");
        page.selectOption("#fruits","2");
        System.out.println("Selected DD option: " + page.locator("p.subtitle").textContent());

        Locator fruitDD = page.locator("#fruits");
        fruitDD.selectOption("1"); //high preference
        System.out.println("SelectedByValue: " + page.locator("p.subtitle").textContent());

        fruitDD.selectOption(new SelectOption().setIndex(1));
        System.out.println("SelectedByIndex: " + page.locator("p.subtitle").textContent());

        fruitDD.selectOption(new SelectOption().setLabel("Pine Apple"));
        System.out.println("SelectByVisibleText: " + page.locator("p.subtitle").textContent());


        Locator superHeroDD = page.locator("#superheros");
        superHeroDD.selectOption(new String[]{"am","bt"});


        Locator languageDD = page.locator("#lang");
        Locator options = languageDD.locator("option");
        int length = options.count()-1;
        System.out.println("Length: " + length);
        languageDD.selectOption(new SelectOption().setIndex(length));
        System.out.println("LastSelectedOptions: " + page.locator("(//p[@class='subtitle'])[2]").textContent());

        List<String> allDDOptions = options.allTextContents();
        allDDOptions.forEach(System.out::println);

        List<String> allInnerTexts = options.allInnerTexts();
        allInnerTexts.forEach(System.out::println);


        languageDD.selectOption(new SelectOption().setIndex(length),
                new Locator.SelectOptionOptions().setTimeout(3000)); //verify with in timeline dropdown need to be select, we use it.

        languageDD.selectOption(new SelectOption().setIndex(length),
                new Locator.SelectOptionOptions().setForce(false)); // true means without checking any condition dropdown has been select.

        languageDD.selectOption(new SelectOption().setIndex(length),
                new Locator.SelectOptionOptions().setNoWaitAfter(false)); // after page load only find the dropdown element and select

        page.close();
        playwright.close();

    }

}
