package com.loyalty.pwa.appium.base;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.loyalty.pwa.appium.utility.GlobalParameters;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.*;

import io.appium.java_client.AppiumDriver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.File;
import java.io.FileReader;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import com.loyalty.pwa.appium.base.TestConstants;
/*
 *
 * @author wloforte
 */
public class BaseClass {

    private static WebDriver driver;
    private Logger logger = LogManager.getLogger(BaseClass.class);

    @Parameters({TestConstants.PLATFORM,TestConstants.RUNON})
    @BeforeClass
    public void setup(@Optional(TestConstants.DEFAULT_PLATFORM) String platform, @Optional(TestConstants.DEFAULT_RUNON) String runOn) {
        logger.info("Platform: " + platform);
        GlobalParameters.runType = platform;
        String path = System.getProperty("user.dir");
        switch (platform) {
            case "web":
                if(runOn.equalsIgnoreCase("chrome")) {
                    logger.debug("Chrome Browser is opening...");
                    System.setProperty("webdriver.chrome.driver", path+"/drivers/web/chromedriver");
                    driver= new ChromeDriver();
                }else if(runOn.equalsIgnoreCase("firefox")) {
                    logger.debug("Firefox Browser is opening...");
                    System.setProperty("webdriver.gecko.driver", path+"/drivers/web/geckodriver");
                    driver= new FirefoxDriver();
                }else if(runOn.equalsIgnoreCase("safari")) {
                    logger.debug("Safari Browser is opening...");
                    driver= new SafariDriver();
                }
                //TODO IMPLEMENT EDGE
                driver.manage().window().maximize();
                driver.get(FileReaderManager.getInstance().getConfigReader().getAppUrl());
                driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
                logger.debug("Web driver started. Thread ID = " + Thread.currentThread().getId());
                break;

            case "mobile":
                logger.debug("Mobile Device: " + runOn + " is opening.....");
                DesiredCapabilities caps = setupDevice(runOn + ".json");
                try {
                    driver = new AppiumDriver(new URL(FileReaderManager.getInstance().getConfigReader().getAppiumUrl()), caps);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                driver.get(FileReaderManager.getInstance().getConfigReader().getAppUrl());
                logger.debug("Appium driver started. Thread ID = " + Thread.currentThread().getId());
                break;
            default:
                System.out.println("Incorrect Platform...");
                break;
        }
    }

    private DesiredCapabilities setupDevice(String configurationFile) {
        File deviceConfigFile = new File("src/test/resources/configurations/" + configurationFile);
        JSONParser parser = new JSONParser();
        Object obj = null;
        try {
            obj = parser.parse(new FileReader(deviceConfigFile));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = (JSONObject) obj;
        JSONObject capabilities = (JSONObject) jsonObject.get("deviceCapabilities");
        DesiredCapabilities caps = new DesiredCapabilities();
        for (Iterator iterator = capabilities.keySet().iterator(); iterator.hasNext();) {
            String capabilityName = (String) iterator.next();
            String capabilityValue = capabilities.get(capabilityName).toString();
            caps.setCapability(capabilityName, capabilityValue);
        }
        return caps;
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }


    public WebDriver getDriver() {
        return this.driver;
    }

}
