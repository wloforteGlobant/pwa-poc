package com.fismobile.appium.managers;

import com.fismobile.appium.pages.AboutPage;
import org.openqa.selenium.WebDriver;

public class PageObjectManager {
    private WebDriver driver;
    private AboutPage aboutPage;

    public PageObjectManager() {
        BaseClass obj = new BaseClass();
        this.driver = obj.getDriver();
    }

    public AboutPage getAboutPage() {
        if (aboutPage == null) {
            return aboutPage = new AboutPage(driver);
        }
        return aboutPage;
    }
}
