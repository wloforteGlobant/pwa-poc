package com.loyalty.pwa.appium.pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
/*
 *
 * @author wloforte
 */
public class ForgotUserPage extends BasePage {

    @FindBy(id = "lbl_forgotUserTitle")
    @AndroidFindBy(id = "lbl_forgotUserTitle")
    @iOSXCUITFindBy(id = "lbl_forgotUserTitle")
    private WebElement lbl_title;

    @FindBy(id = "lbl_email_forgotUser")
    @AndroidFindBy(id = "lbl_email_forgotUser")
    @iOSXCUITFindBy(id = "lbl_email_forgotUser")
    private WebElement lbl_email;

    @FindBy(id = "txt_email_forgotUser")
    @AndroidFindBy(id = "txt_email_forgotUser")
    @iOSXCUITFindBy(id = "txt_email_forgotUser")
    private WebElement txt_email;

    @FindBy(id = "lbl_forgotUserError")
    @AndroidFindBy(id = "lbl_forgotUserError")
    @iOSXCUITFindBy(id = "lbl_forgotUserError")
    private WebElement lbl_forgotUserError;

    @FindBy(id = "btn_send_forgotUser")
    @AndroidFindBy(id = "btn_send_forgotUser")
    @iOSXCUITFindBy(id = "btn_send_forgotUser")
    private WebElement btn_send;

    @FindBy(xpath = "//h1[contains(text(), 'Done!')]")
    @AndroidFindBy(id = "//h1[contains(text(), 'Done!')]")
    @iOSXCUITFindBy(id = "//h1[contains(text(), 'Done!')]")
    private WebElement lbl_success;

    @FindBy(xpath = "//h1[contains(text(), 'Ooops!')]")
    @AndroidFindBy(id = "//h1[contains(text(), 'Ooops!')]")
    @iOSXCUITFindBy(id = "//h1[contains(text(), 'Ooops!')]")
    private WebElement lbl_failed;

    @FindBy(id = "btn_backToLogin_forgotUser")
    @AndroidFindBy(id = "btn_backToLogin_forgotUser")
    @iOSXCUITFindBy(id = "btn_backToLogin_forgotUser")
    private WebElement btn_backToLogin;

    public String getTitleText() { return lbl_title.getText(); }

    public void sendEmail(String email) {
        sendText(txt_email, email);
        clickElement(btn_send);
    }

    public Boolean success() {
        sendEmail("ss@ss.com");
        fluentWait(appiumDriver, btn_backToLogin);
        if (lbl_success.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

}
