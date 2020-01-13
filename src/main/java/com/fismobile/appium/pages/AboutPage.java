package com.fismobile.appium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.FindBy;
import com.fismobile.appium.utility.GlobalParameters;
/*
 *
 * @author wloforte
 */
public class AboutPage {

    @FindBy(id = "i_am_an_id")
    private WebElement title;

    @FindBy(id = "i am a link")
    private WebElement span;

    @FindBy(id = "comments")
    private WebElement txt_comments;

    public AboutPage(WebDriver driver) {

        if(GlobalParameters.runType.equalsIgnoreCase("web"))
        {
            PageFactory.initElements(driver, this);
        }else
        {
            PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        }

    }

    public String getTitle() {
        return title.getText();
    }

    public String getSpan() { return span.getText(); }

    public String getComments() { return txt_comments.getAttribute("value"); }

    public void writeComment() { txt_comments.sendKeys("HOLA SOY UN COMMENT"); }
}
