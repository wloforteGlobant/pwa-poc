package com.loyalty.pwa.appium.tests;

import com.loyalty.pwa.appium.base.BaseClass;
import com.loyalty.pwa.appium.pages.AboutPage;
import com.loyalty.pwa.appium.pages.AnotherPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AnotherPageTest extends BaseClass {

    @Test
    void test1() throws InterruptedException {
        AboutPage aboutPage = new AboutPage(getDriver());
        aboutPage.goToAnother();

        AnotherPage anotherPage = new AnotherPage(getDriver());
        Assert.assertTrue(anotherPage.getDivText().equalsIgnoreCase("I am another div"));
    }
}
