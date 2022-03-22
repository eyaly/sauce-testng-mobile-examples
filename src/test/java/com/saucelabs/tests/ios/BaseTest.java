package com.saucelabs.tests.ios;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import static com.saucelabs.tests.Config.env;
import static com.saucelabs.tests.Config.region;

public class BaseTest {
    protected static ThreadLocal<IOSDriver> driver = new ThreadLocal<>();

    private String SAUCE_EU_URL = "https://ondemand.eu-central-1.saucelabs.com/wd/hub";
    private String SAUCE_US_URL = "https://ondemand.us-west-1.saucelabs.com/wd/hub";
    private String SAUCE_CAP = "sauce_";

    @BeforeMethod
    public void setup(Method method) throws MalformedURLException {

        System.out.println("Sauce - BeforeMethod hook");
        String methodName = method.getName();
        URL url;

        if (env.equals("saucelabs")) {

            switch (region) {
                case "us":
                    url = new URL(SAUCE_US_URL);
                    break;
                case "eu":
                default:
                    url = new URL(SAUCE_EU_URL);
                    break;
            }

            boolean isBuildCap = false;
            MutableCapabilities caps = new MutableCapabilities();
            MutableCapabilities sauceOptions = new MutableCapabilities();

            for (Map.Entry<String, String> entry : Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getAllParameters().entrySet()) {
                String k = entry.getKey();
                String v = entry.getValue();

                if (k.startsWith(SAUCE_CAP)) {
                    // Sauce capability
                    String sauceCap = k.replaceFirst(SAUCE_CAP, "");
                    if (sauceCap.equals("build")) {
                        isBuildCap = true;
                    }

                    if (v.contains(" ")) {
                        // handle a list such as in tags cap
                        List<String> capList = Arrays.asList(v.split(" "));
                        sauceOptions.setCapability(sauceCap, capList);
                    } else {
                        sauceOptions.setCapability(sauceCap, v);
                    }
                } else {
                    caps.setCapability(k, v);
                }
            }

//            caps.setCapability("name", methodName);
//            caps.setCapability("username", System.getenv("SAUCE_USERNAME"));
//            caps.setCapability("accessKey", System.getenv("SAUCE_ACCESS_KEY"));

            // W3C
            sauceOptions.setCapability("username", System.getenv("SAUCE_USERNAME"));
            sauceOptions.setCapability("accessKey", System.getenv("SAUCE_ACCESS_KEY"));
            sauceOptions.setCapability("name", methodName);

            if (!isBuildCap) { //handle build cap
                String buildVal = System.getenv("BUILD_TAG");
                System.out.println("Sauce BUILD - " + buildVal);
                sauceOptions.setCapability("build", buildVal == null ? String.valueOf(new Random(System.currentTimeMillis()).nextInt()).replace("-", "") : buildVal);
            }

            caps.setCapability("sauce:options", sauceOptions);

            try {
                driver.set(new IOSDriver(url, caps));
            } catch (Exception e) {
                System.out.println("*** Problem to create the iOS driver " + e.getMessage());
                throw new RuntimeException(e);
            }

        }

        // change the setting to accept alerts with 3 options
        // and select the "Allow While Using App" (iOS 13 and above)
        getDriver().setSetting("acceptAlertButtonSelector",
                "**/XCUIElementTypeButton[`label == \"Allow While Using App\"`]");
    }

    @AfterMethod
    public void teardown(ITestResult result) {
        System.out.println("Sauce - AfterMethod hook");
        try {
            ((JavascriptExecutor) getDriver()).executeScript("sauce:job-result=" + (result.isSuccess() ? "passed" : "failed"));
        } finally {
            System.out.println("Sauce - release driver");
            getDriver().quit();
        }
    }

    public IOSDriver getDriver() {
        return driver.get();
    }

}
