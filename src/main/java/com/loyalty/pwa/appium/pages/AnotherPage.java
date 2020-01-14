package com.loyalty.pwa.appium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
/*
 *
 * @author wloforte
 */
public class AnotherPage extends BasePage {

    @FindBy(id = "i_am_an_id")
    private WebElement anotherDiv;

    public AnotherPage(WebDriver driver) { super(driver);}

    public WebElement getDiv() { return anotherDiv; }

    public String getDivText() { return anotherDiv.getText(); }

}