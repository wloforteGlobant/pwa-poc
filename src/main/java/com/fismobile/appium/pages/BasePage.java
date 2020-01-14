package com.fismobile.appium.pages;

import com.fismobile.appium.utility.GlobalParameters;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;
/*
 *
 * @author wloforte
 */
public class BasePage {

    protected static WebDriver driver;
    protected static WebDriverWait wait;
    protected static int timeoutSec;

    public BasePage (WebDriver driver) {
        this.driver = driver;
        timeoutSec = 30;
        this.wait = new WebDriverWait(driver, timeoutSec);
        driver.manage().timeouts().implicitlyWait(timeoutSec, TimeUnit.SECONDS);
        if(GlobalParameters.runType.equalsIgnoreCase("web"))
        {
            PageFactory.initElements(driver, this);
        }else
        {
            PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        }
    }

    public static WebDriver getDriver(){
        return driver;
    }

    WebElement fluentWait(WebDriver driver, By elementIdentifier){
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(60, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);

        return wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(elementIdentifier);
            }
        });
    }
}
