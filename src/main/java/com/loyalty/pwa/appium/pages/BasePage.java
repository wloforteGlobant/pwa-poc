package com.loyalty.pwa.appium.pages;

import com.loyalty.pwa.appium.base.BaseClass;
import com.loyalty.pwa.appium.base.TestConstants;
import com.loyalty.pwa.appium.utility.GlobalParameters;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;
import java.util.function.Function;
/*
 *
 * @author wloforte
 */
public class BasePage {

    protected static WebDriver webDriver;
    protected static AppiumDriver appiumDriver;
    protected static WebDriverWait wait;
    protected static int timeoutSec;

    public BasePage () {
        BaseClass baseClass = new BaseClass();
        webDriver = baseClass.getDriver();
        appiumDriver = baseClass.getAppiumDriver();
        if(GlobalParameters.runType.equalsIgnoreCase(TestConstants.WEB_PLATFORM)) {
            PageFactory.initElements(webDriver, this);
        } else {
            PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
        }
    }

    protected WebElement fluentWait(WebDriver driver, WebElement webElement){
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(TestConstants.TIMEOUT_SECONDS))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);

        return wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return webElement;
            }
        });
    }

    protected void clickElement (WebElement webElement) {
        webElement.click();
    }

    protected void sendText(WebElement webElement, String text) {
        webElement.sendKeys(text);
    }

    protected void switchDriverContext() {
        /* TBD */
        Set<String> driverContext = appiumDriver.getContextHandles();
        System.out.println(driverContext);
    }

}