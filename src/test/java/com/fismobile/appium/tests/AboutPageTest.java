
package com.fismobile.appium.tests;

import com.fismobile.appium.managers.BaseClass;
import com.fismobile.appium.managers.PageObjectManager;
import com.fismobile.appium.pages.AboutPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/*
 *
 * @author wloforte
 */
public class AboutPageTest extends BaseClass {
    AboutPage aboutPage;
    PageObjectManager pageObjectManager;

    public AboutPageTest() {
        pageObjectManager = new PageObjectManager();
        aboutPage = pageObjectManager.getAboutPage();
    }

    @Test
    void test1(){
        System.out.println("------------------------TEST--------------------");
        Assert.assertTrue(aboutPage.getTitle().equalsIgnoreCase("I am a div"));
    }

    @Test
    void test2(){
        System.out.println("------------TEST 2---------");
        aboutPage.writeComment();
        System.out.println(aboutPage.getComments());
        Assert.assertTrue(aboutPage.getComments().equalsIgnoreCase("HOLA SOY UN COMMENT"));
    }


}
