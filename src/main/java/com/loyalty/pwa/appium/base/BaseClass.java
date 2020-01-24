package com.loyalty.pwa.appium.base;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;

import com.loyalty.pwa.appium.utility.GlobalParameters;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
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
/*
 *
 * @author wloforte
 */
public class BaseClass {

    private static WebDriver webDriver;
    private static AppiumDriver appiumDriver;
    private Logger logger = LogManager.getLogger(BaseClass.class);
    private AppiumDriverLocalService service;
    private AppiumServiceBuilder appiumServiceBuilder;
    private DesiredCapabilities appiumCapabilities;
    private String path = System.getProperty("user.dir");

    @Parameters({TestConstants.PLATFORM,TestConstants.RUNON})
    @BeforeClass
    public void setup(@Optional(TestConstants.WEB_PLATFORM) String platform, @Optional(TestConstants.CHROME_BROWSER) String runOn) {
        logger.info("Platform: " + platform);
        GlobalParameters.runType = platform;
        switch (platform) {
            case TestConstants.WEB_PLATFORM:
                switch (runOn) {
                    case TestConstants.CHROME_BROWSER:
                        logger.debug("Chrome Browser is opening...");
                        System.setProperty(TestConstants.CHROME_PROPERTY, path + TestConstants.CHROME_PATH);
                        webDriver = new ChromeDriver();
                        break;
                    case TestConstants.SAFARI_BROWSER:
                        logger.debug("Safari Browser is opening...");
                        webDriver = new SafariDriver();
                        break;
                    case TestConstants.FIREFOX_BROWSER:
                        logger.debug("Firefox Browser is opening...");
                        System.setProperty(TestConstants.FIREFOX_PROPERTY, path + TestConstants.FIREFOX_PATH);
                        webDriver = new FirefoxDriver();
                        break;
                    case TestConstants.EDGE_BROWSER:
                        logger.debug("Edge is opening...");
                        //TODO IMPLEMENT EDGE
                        break;
                }
                webDriver.get(FileReaderManager.getInstance().getConfigReader().getAppUrl());
                webDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
                logger.debug("Web driver started. Thread ID = " + Thread.currentThread().getId());
                break;
            case TestConstants.MOBILE_PLATFORM:
                logger.debug("Mobile Device: " + runOn + " is opening.....");
                DesiredCapabilities desiredCapabilities = setupDevice(runOn + ".json");
                try {
                    appiumDriver = new AppiumDriver(new URL(FileReaderManager.getInstance().getConfigReader().getAppiumUrl()), desiredCapabilities);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                appiumDriver.get(FileReaderManager.getInstance().getConfigReader().getAppUrl());
                logger.debug("Appium driver started. Thread ID = " + Thread.currentThread().getId());
                break;
            default:
                System.out.println("Incorrect Platform...");
                break;
        }
    }

    @Parameters({TestConstants.PLATFORM})
    @BeforeTest
    public void startAppiumServer(@Optional(TestConstants.WEB_PLATFORM) String platform) {
        if (!isServerRunnning()) {
            if (platform.equalsIgnoreCase(TestConstants.MOBILE_PLATFORM)) {
                appiumCapabilities = new DesiredCapabilities();
                appiumCapabilities.setCapability(TestConstants.APPIUM_CAP_BINARY, path + TestConstants.APPIUM_CHROME_PATH);
                appiumCapabilities.setCapability(TestConstants.APPIUM_CAP_RESET, "false");

                appiumServiceBuilder = new AppiumServiceBuilder();
                appiumServiceBuilder.withIPAddress(TestConstants.APPIUM_IP_ADDRESS);
                appiumServiceBuilder.usingPort(TestConstants.APPIUM_PORT);
                appiumServiceBuilder.withCapabilities(appiumCapabilities);
                appiumServiceBuilder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
                appiumServiceBuilder.withArgument(GeneralServerFlag.LOG_LEVEL,"error");

                service = AppiumDriverLocalService.buildService(appiumServiceBuilder);
                service.start();
                logger.info("APPIUM SERVER STARTED");
            }
        }
    }

    private boolean isServerRunnning() {
        boolean isServerRunning = false;
        ServerSocket serverSocket;
        try {
            InetAddress address = InetAddress.getByName(TestConstants.APPIUM_IP_ADDRESS);
            serverSocket = new ServerSocket(TestConstants.APPIUM_PORT, 0, address);
            serverSocket.close();
        } catch (IOException e) {
            isServerRunning = true;
        } finally {
            serverSocket = null;
        }
        return isServerRunning;
    }

    private DesiredCapabilities setupDevice(String device) {
        File deviceConfigFile = new File("src/test/resources/configurations/" + device);
        JSONParser parser = new JSONParser();
        Object obj = null;
        try {
            obj = parser.parse(new FileReader(deviceConfigFile));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = (JSONObject) obj;
        JSONObject capabilities = (JSONObject) jsonObject.get("deviceCapabilities");
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        for (Iterator iterator = capabilities.keySet().iterator(); iterator.hasNext();) {
            String capabilityName = (String) iterator.next();
            String capabilityValue = capabilities.get(capabilityName).toString();
            desiredCapabilities.setCapability(capabilityName, capabilityValue);
        }
        return desiredCapabilities;
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (GlobalParameters.runType.equalsIgnoreCase(TestConstants.MOBILE_PLATFORM)){
            appiumDriver.quit();
        } else {
            webDriver.quit();
        }
    }

    @AfterTest
    public void stopAppiumServer() {
        if (GlobalParameters.runType.equalsIgnoreCase(TestConstants.MOBILE_PLATFORM)){
            logger.info("APPIUM SERVER STOPPED");
            service.stop();
        } else {
            logger.debug("APPIUM SERVER IS NOT RUNNING");
        }
    }

    public WebDriver getDriver() {
        return this.webDriver;
    }

    public AppiumDriver getAppiumDriver() {
        return this.appiumDriver;
    }

}