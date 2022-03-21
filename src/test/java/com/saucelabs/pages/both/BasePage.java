//package com.saucelabs.pages.both;
//
//import io.appium.java_client.AppiumDriver;
//// for Java v7
////import io.appium.java_client.MobileElement;
//
////import io.appium.java_client.TouchAction;
//
//import io.appium.java_client.touch.WaitOptions;
//import io.appium.java_client.touch.offset.PointOption;
//
//import org.openqa.selenium.*;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.remote.RemoteWebDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.time.Duration;
//import java.util.ArrayList;
//import java.util.List;
//
//public abstract class BasePage {
//
//    private AppiumDriver driver;
//
//    public AppiumDriver getDriver() {
//        return driver;
//    }
//
//    public BasePage(AppiumDriver driver) {
//        this.driver = driver;
//    }
//
//    public WebElement find(By locator) {
//        return (WebElement)driver.findElement(locator);
//    }
//    public List<WebElement> findElements(By locator) {
//        return (List<WebElement>)driver.findElements(locator);
//    }
//
//    public void click(By locator) {
//        find(locator).click();
//    }
//
//    public void type(String inputText, By locator) {
//        find(locator).sendKeys(inputText);
//    }
//
//    public void clickAndType(String inputText, By locator) {
//        click(locator);
//
//        Actions a = new Actions(getDriver());
//        a.sendKeys(inputText);
//        a.perform();
//    }
//
//    public Boolean isDisplayed(By locator) {
//        try {
//            return find(locator).isDisplayed();
//        } catch (NoSuchElementException exception) {
//            return false;
//        }
//    }
//
//    public Boolean isDisplayed(By locator, long timeoutInSeconds) {
//        try {
////            WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
//            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
//        } catch (TimeoutException exception) {
//            return false;
//        }
//        return true;
//    }
//
//    public void waitDisplayed(By locator, long timeoutInSeconds) {
//        try {
//            // is deprecated in Selenium 4
////            WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
//
//            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
//        } catch (TimeoutException exception) {
//            System.out.println("*** The element wasn't diplayed ***");
//        }
//
//    }
//
////    public void switchToNativeViewContext() {
////        System.out.println("*** Switch to native view context ***");
////        driver.context("NATIVE_APP");
////    }
////
////    protected boolean switchToWebViewContext() {
////        ArrayList<String> contexts = new ArrayList(driver.getContextHandles());
////        for (String context : contexts) {
////            System.out.println(context);
////            if (context.contains("WEBVIEW")) {
////                driver.context(context);
////                return true;
////            } else {
////                System.out.println("*** There is no webview context ***");
////            }
////
////        }
////        return false;
////    }
//
//    protected void waiting(int sec){
//        try
//        {
//            Thread.sleep(sec*1000);
//        }
//        catch(InterruptedException ex)
//        {
//            Thread.currentThread().interrupt();
//        }
//    }
//
//}
