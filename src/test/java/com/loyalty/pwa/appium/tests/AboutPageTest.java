
package com.loyalty.pwa.appium.tests;

import com.loyalty.pwa.appium.base.BaseClass;
import com.loyalty.pwa.appium.pages.AboutPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/*
 *
 * @author wloforte
 */
public class AboutPageTest extends BaseClass {

    @Test
    void test1() {
        AboutPage aboutPage = new AboutPage(getDriver());
        Assert.assertTrue(aboutPage.getTitle().equalsIgnoreCase("I am a div"));
    }

    @Test
    void test2() {
        AboutPage aboutPage = new AboutPage(getDriver());
        aboutPage.writeComment();
        Assert.assertTrue(aboutPage.getComments().equalsIgnoreCase("HOLA SOY UN COMMENT"));
    }


}
