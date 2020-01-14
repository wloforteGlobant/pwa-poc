package com.fismobile.appium.tests;

import com.fismobile.appium.base.BaseClass;
import com.fismobile.appium.pages.AboutPage;
import com.fismobile.appium.pages.AnotherPage;
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
