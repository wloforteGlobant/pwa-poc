package com.loyalty.pwa.appium.tests;

import com.loyalty.pwa.appium.base.BaseClass;
import com.loyalty.pwa.appium.pages.ForgotUserOrPassPage;
import com.loyalty.pwa.appium.pages.ForgotUserPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ForgotUserTest extends BaseClass {

    @Test
    void navigateToForgotUser() {
        ForgotUserOrPassPage forgotUserOrPassPage = new ForgotUserOrPassPage();
        forgotUserOrPassPage.forgotUserName();

        ForgotUserPage forgotUserPage = new ForgotUserPage();
        Assert.assertTrue(forgotUserPage.getTitleText().equalsIgnoreCase("Forgot Username"));
    }

}
