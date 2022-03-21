package com.saucelabs.pages.ios;

import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


import static org.assertj.core.api.Assertions.assertThat;

public class CatalogPage extends BasePage {


    By productsScreenLocator = By.id("products screen");
    By productScreenLocator = By.id("product screen");

    public CatalogPage(IOSDriver driver) {
        super(driver);
    }

    public Boolean isOnCatalogPage() {
        return isDisplayed(productsScreenLocator, 10);
    }

    public void addProductToCart(String needle){
        WebElement product = getProduct(needle);
        if (product !=null)
            product.click();
        else
            System.out.println("Can't find product Backpack");

        // Verify we select a product
        assertThat(isDisplayed(productScreenLocator, 10)).as("Verify product page").isTrue();
    }
//
//    private MobileElement getProduct(String needle){
//
//        // swipe once if needed
//        //for (int i = 0; i < 2; i++) {
//        List<MobileElement> productsList = findElements(productLocator);
//        MobileElement needleFirst = productsList.stream().filter(elm -> elm.findElement(ProductName).getText().contains(needle)).findFirst().orElse(null);
//
////            MobileElement needleFirst = getDriver().findElementsByIosClassChain(catalogListLocator).stream().filter(elm -> elm..(productLocator).getText().contains(needle)).findFirst().orElse(null);
//            if (needleFirst != null)
//                return needleFirst;
//            // swipe
//            //swipeElement(find(allProductsLocator), "UP");
//        //}
//        return null;
//    }


    public Boolean isDisplayed(By locator, long timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), timeoutInSeconds);
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (org.openqa.selenium.TimeoutException exception) {
            return false;
        }
        return true;
    }

    public Boolean isElementDisplayed(WebElement elem, long timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), timeoutInSeconds);
            wait.until(ExpectedConditions.visibilityOf(elem));
        } catch (org.openqa.selenium.TimeoutException exception) {
            return false;
        }
        return true;
    }

    private WebElement getProduct(String needle){
        IOSDriver driver = getDriver();
        String selector = "**/XCUIElementTypeOther[`label == \"" + needle + "\"`]";
        // in iOS - XCUITest Gives back all elements  in the screen
        WebElement productElement = driver.findElementByIosClassChain(selector);
        // swipe if needed
        for (int i = 0; i < 2; i++) {
            if (isElementDisplayed(productElement,1))
                return productElement;
            // swipe
            swipeElementUP(driver.findElement(productsScreenLocator));
        }
        return null;
    }

    /**
     * From: http://appium.io/docs/en/writing-running-appium/tutorial/swipe/simple-element/
     * Performs swipe inside an element
     *
     * @param el  the element to swipe
     * @version java-client: 7.3.0
     **/
    public void swipeElementUP(WebElement el) {
        IOSDriver driver = getDriver();
        System.out.println("swipeElementAndroid() "); // always log your actions

        // Animation default time:
        //  - Android: 300 ms
        //  - iOS: 200 ms
        // final value depends on your app and could be greater
        final int ANIMATION_TIME = 500; // ms
        final int PRESS_TIME = 200; // ms

        PointOption pointOptionStart, pointOptionEnd;

        // init screen variables
        Rectangle rect = el.getRect();

        pointOptionStart = PointOption.point(rect.x + rect.width / 2,
                rect.y + (int)(rect.height * 0.9));
        pointOptionEnd = PointOption.point(rect.x + rect.width / 2,
                rect.y + (int)(rect.height * 0.1)  );

        // execute swipe using TouchAction
        try {
            new TouchAction(driver)
                    .press(pointOptionStart)
                    // a bit more reliable when we add small wait
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                    .moveTo(pointOptionEnd)
                    .release().perform();
        } catch (Exception e) {
            System.err.println("swipeElementAndroid(): TouchAction FAILED\n" + e.getMessage());
            return;
        }

        // always allow swipe action to complete
        try {
            Thread.sleep(ANIMATION_TIME);
        } catch (InterruptedException e) {
            // ignore
        }
    }

}
