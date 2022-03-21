//package com.saucelabs.pages.both;
//
//import io.appium.java_client.AppiumDriver;
//// Java V7
////import io.appium.java_client.MobileElement;
//
//import io.appium.java_client.android.AndroidDriver;
//import io.appium.java_client.pagefactory.AndroidFindBy;
//import io.appium.java_client.pagefactory.AppiumFieldDecorator;
//import io.appium.java_client.pagefactory.iOSXCUITFindBy;
//
////import org.openqa.selenium.By;
//import org.openqa.selenium.By;
//import org.openqa.selenium.TimeoutException;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.remote.RemoteWebDriver;
//import org.openqa.selenium.support.PageFactory;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.time.Duration;
//
//public class CatalogPage extends BasePage {
//
//
////    By productTitleLocator = By.id("com.saucelabs.mydemoapp.android:id/productTV");
//
//    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/productTV")
//    @iOSXCUITFindBy(id = "Products")
//    private WebElement productTitle;
//
//    public CatalogPage(AppiumDriver driver) {
//        super(driver);
//        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
//    }
//
//    public Boolean isOnCatalogPage() {
//        try {
////            WebDriverWait wait = new WebDriverWait(getDriver(), 10);
//            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
//            wait.until(ExpectedConditions.visibilityOf(productTitle));  //.visibilityOfElementLocated(
//        } catch (TimeoutException exception) {
//            return false;
//        }
//        return true;
//
//    }
//
//}
