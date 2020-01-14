
package com.fismobile.appium.tests;

import com.fismobile.appium.managers.BaseClass;
import com.fismobile.appium.pages.AboutPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/*
 *
 * @author wloforte
 */
public class AboutPageTest extends BaseClass {

    @Test
    void test1(){
        AboutPage aboutPage = new AboutPage(getDriver());
        System.out.println("------------------------TEST 1--------------------");
        Assert.assertTrue(aboutPage.getTitle().equalsIgnoreCase("I am a div"));
    }

    @Test
    void test2(){
        AboutPage aboutPage = new AboutPage(getDriver());
        System.out.println("------------------------TEST 2--------------------");
        aboutPage.writeComment();
        Assert.assertTrue(aboutPage.getComments().equalsIgnoreCase("HOLA SOY UN COMMENT"));
    }


}
