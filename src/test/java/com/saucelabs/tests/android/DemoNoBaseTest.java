package com.saucelabs.tests.android;

import com.saucelabs.pages.android.CatalogPage;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static com.saucelabs.tests.Config.env;
import static com.saucelabs.tests.Config.region;
import static org.assertj.core.api.Assertions.assertThat;

public class DemoNoBaseTest {

    protected static ThreadLocal<AndroidDriver> driver = new ThreadLocal<>();

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

            caps.setCapability("name", methodName);
            caps.setCapability("username", System.getenv("SAUCE_USERNAME"));
            caps.setCapability("accessKey", System.getenv("SAUCE_ACCESS_KEY"));

            // W3C - not supported yet on RDC
//            sauceOptions.setCapability("username", System.getenv("SAUCE_USERNAME"));
//            sauceOptions.setCapability("accessKey", System.getenv("SAUCE_ACCESS_KEY"));
//            sauceOptions.setCapability("name", methodName);

            if (!isBuildCap) { //handle build cap
                String buildVal = System.getenv("BUILD_NAME");
                sauceOptions.setCapability("build", buildVal == null ? String.valueOf(new Random(System.currentTimeMillis()).nextInt()).replace("-", "") : buildVal);
            }

            //caps.setCapability("sauce:options", sauceOptions);

            try {
                driver.set(new AndroidDriver(url, caps));
            } catch (Exception e) {
                System.out.println("*** Problem to create the android driver " + e.getMessage());
                throw new RuntimeException(e);
            }
        }
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
    public AndroidDriver getDriver() {
        return driver.get();
    }

    @Test
    public void demoTest() throws InterruptedException {
        System.out.println("Sauce - Start demoTest test");
        CatalogPage catalogPage = new CatalogPage(getDriver());
        assertThat(catalogPage.isOnCatalogPage()).as("Verify catalog page").isTrue();
//        catalogPage.addProductToCart("Sauce Lab Fleece T-Shirt");
        catalogPage.addProductToCart("Test.sllTheThings() T-Shirt");
    }

    @Test
    public void demoTest2() throws InterruptedException {
        System.out.println("Sauce - Start demoTest2 test");
        CatalogPage catalogPage = new CatalogPage(getDriver());
        assertThat(catalogPage.isOnCatalogPage()).as("Verify catalog page").isTrue();
//        catalogPage.addProductToCart("Sauce Lab Fleece T-Shirt");
        catalogPage.addProductToCart("Test.sllTheThings() T-Shirt");
    }

}
