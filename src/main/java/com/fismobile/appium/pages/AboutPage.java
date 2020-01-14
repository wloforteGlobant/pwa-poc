package com.fismobile.appium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
/*
 *
 * @author wloforte
 */
public class AboutPage extends BasePage {

    @FindBy(id = "i_am_an_id")
    private WebElement title;

    @FindBy(id = "i am a link")
    private WebElement link;

    @FindBy(id = "comments")
    private WebElement txt_comments;

    public AboutPage(WebDriver driver) { super(driver); }

    public String getTitle() {
        return title.getText();
    }

    public void clickLink() { link.click(); }

    public AnotherPage goToAnother() throws InterruptedException {
        clickLink();
        Thread.sleep(1500);
        return new AnotherPage(driver);
    }

    public String getComments() { return txt_comments.getAttribute("value"); }

    public void writeComment() { txt_comments.sendKeys("HOLA SOY UN COMMENT"); }
}
