package com.loyalty.pwa.appium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
/*
 *
 * @author wloforte
 */
public class AboutPage extends BasePage {

    @FindBy(id = "i_am_an_id")
    private WebElement aboutTitle;

    @FindBy(id = "i am a link")
    private WebElement aboutLink;

    @FindBy(id = "comments")
    private WebElement txtComments;

    public AboutPage(WebDriver driver) { super(driver); }

    public String getTitle() {
        return aboutTitle.getText();
    }

    public void clickLink() { aboutLink.click(); }

    public AnotherPage goToAnother() throws InterruptedException {
        clickLink();
        Thread.sleep(1500);
        return new AnotherPage(driver);
    }

    public String getComments() { return txtComments.getAttribute("value"); }

    public void writeComment() { txtComments.sendKeys("HOLA SOY UN COMMENT"); }

}