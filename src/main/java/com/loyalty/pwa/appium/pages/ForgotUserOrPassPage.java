package com.loyalty.pwa.appium.pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
/*
 *
 * @author wloforte
 */
public class ForgotUserOrPassPage extends BasePage {

    @FindBy(id = "lbl_forgotUserOrPassTitle")
    @AndroidFindBy(id = "lbl_forgotUserOrPassTitle")
    @iOSXCUITFindBy(id = "lbl_forgotUserOrPassTitle")
    private WebElement lbl_title;

    @FindBy(id = "btn_forgotUser")
    @AndroidFindBy(id = "btn_forgotUser")
    @iOSXCUITFindBy(id = "btn_forgotUser")
    private WebElement btn_forgotUser;

    @FindBy(id = "btn_forgotPass")
    @AndroidFindBy(id = "btn_forgotPass")
    @iOSXCUITFindBy(id = "btn_forgotPass")
    private WebElement btn_forgotPass;

    public ForgotUserPage forgotUserName() {
        clickElement(btn_forgotUser);
        return new ForgotUserPage();
    };

}
