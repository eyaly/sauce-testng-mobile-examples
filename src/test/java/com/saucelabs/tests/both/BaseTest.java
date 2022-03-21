//package com.saucelabs.tests.both;
//
//import io.appium.java_client.AppiumDriver;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.MutableCapabilities;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.remote.RemoteWebDriver;
//import org.testng.ITestResult;
//import org.testng.Reporter;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeMethod;
//
//import java.lang.reflect.Method;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.*;
//
//import static com.saucelabs.tests.Config.env;
//import static com.saucelabs.tests.Config.region;
//
//public class BaseTest {
//    protected static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();
//
//    private String SAUCE_EU_URL = "https://ondemand.eu-central-1.saucelabs.com/wd/hub";
//    private String SAUCE_US_URL = "https://ondemand.us-west-1.saucelabs.com/wd/hub";
//    private String SAUCE_CAP = "sauce_";
//
//    @BeforeMethod
//    public void setup(Method method) throws MalformedURLException {
//
//        System.out.println("Sauce - BeforeMethod hook");
//        String methodName = method.getName();
//        String username = System.getenv("SAUCE_USERNAME");
//        String accesskey = System.getenv("SAUCE_ACCESS_KEY");
//        URL url;
//
//        if (env.equals("saucelabs")) {
//
//            switch (region) {
//                case "us":
//                    url = new URL(SAUCE_US_URL);
//                    break;
//                case "eu":
//                default:
//                    url = new URL(SAUCE_EU_URL);
//                    break;
//            }
//
//            // Changes since Sauce is not W3C and we use Appium Java v8
////            String sauceUrl = "@ondemand.eu-central-1.saucelabs.com:443";
////            String SAUCE_REMOTE_URL = "https://" + username + ":" + accesskey + sauceUrl + "/wd/hub";
////            url = new URL(SAUCE_REMOTE_URL);
//
//            // staging url
//            //url = new URL("https://oauth-eyalsaucelabs-f4d52:929696df-aae3-4842-954e-217b2856e541@ondemand.staging.saucelabs.net:443/wd/hub");
//            boolean isBuildCap = false;
//            MutableCapabilities caps = new MutableCapabilities();
//            MutableCapabilities sauceOptions = new MutableCapabilities();
////            Map<String, Object> sauceOptions = new HashMap<>();
//
//            for (Map.Entry<String, String> entry : Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getAllParameters().entrySet()) {
//                String k = entry.getKey();
//                String v = entry.getValue();
//
//                if (k.startsWith(SAUCE_CAP)) {
//                    // Sauce capability
//                    String sauceCap = k.replaceFirst(SAUCE_CAP, "");
//                    if (sauceCap.equals("build")) {
//                        isBuildCap = true;
//                    }
//
//                    if (v.contains(" ")) {
//                        // handle a list such as in tags cap
//                        List<String> capList = Arrays.asList(v.split(" "));
//                        sauceOptions.setCapability(sauceCap, capList);
//                    } else {
//                        sauceOptions.setCapability(sauceCap, v);
//                    }
//                } else {
//                    caps.setCapability(k, v);
//                }
//            }
//
//            // not for Appium java V8
////            caps.setCapability("name", methodName);
////            caps.setCapability("username", System.getenv("SAUCE_USERNAME"));
////            caps.setCapability("accessKey", System.getenv("SAUCE_ACCESS_KEY"));
//            // emusim and stagin
//            sauceOptions.setCapability("username", System.getenv("SAUCE_USERNAME"));
//            sauceOptions.setCapability("accessKey", System.getenv("SAUCE_ACCESS_KEY"));
//            sauceOptions.setCapability("name", methodName);
//
////            if (!isBuildCap){ //handle build cap
////                String buildVal = System.getenv("BUILD_NAME");
////                sauceOptions.setCapability("build", buildVal == null ? String.valueOf(new Random(System.currentTimeMillis()).nextInt()).replace("-", "") : buildVal);
////            }
//
//            caps.setCapability("sauce:options", sauceOptions);
//
////            if (!isBuildCap) { //handle build cap
////                String buildVal = System.getenv("BUILD_NAME");
////                caps.setCapability("build", buildVal == null ? String.valueOf(new Random(System.currentTimeMillis()).nextInt()).replace("-", "") : buildVal);
////            }
//
//            try {
//                driver.set(new AppiumDriver(url, caps));
//            } catch (Exception e) {
//                System.out.println("*** Problem to create the appium driver " + e.getMessage());
//                throw new RuntimeException(e);
//            }
//        }
//    }
//
//    @AfterMethod
//    public void teardown(ITestResult result) {
//        System.out.println("Sauce - AfterMethod hook. Does the test pass?  " + result.isSuccess());
//        try {
//            ((JavascriptExecutor) getDriver()).executeScript("sauce:job-result=" + (result.isSuccess() ? "passed" : "failed"));
//        } finally {
//            System.out.println("Sauce - release driver");
//            getDriver().quit();
//        }
//    }
//
//    public AppiumDriver getDriver() {
//        return driver.get();
//    }
//
//}
