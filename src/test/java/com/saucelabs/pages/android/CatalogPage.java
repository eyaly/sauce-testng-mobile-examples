package com.saucelabs.pages.android;


import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class CatalogPage extends BasePage {

    By productsScreenLocator = By.xpath("//*[@content-desc=\"products screen\"]");
    By productScreenLocator = By.xpath("//*[@content-desc=\"product screen\"]");

    public CatalogPage(AndroidDriver driver) {
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
            System.out.println("Can't find product " + needle);

        // Verify we select a product
        assertThat(isDisplayed(productScreenLocator, 10)).as("Verify product page").isTrue();
//        getProduct(needle).findElement(productPicLocator).click();
//        waiting(5);
    }

    private WebElement getProduct(String needle){
        AndroidDriver driver = getDriver();
        By elemLocator =  By.xpath("//android.widget.TextView[contains(@text,'" + needle + "')]");
        // swipe if needed
        for (int i = 0; i < 2; i++) {
            if (isDisplayed(elemLocator,1))
                return driver.findElement(elemLocator);;
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
        AndroidDriver driver = getDriver();
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
                rect.y + (int)(rect.height*0.9));
        pointOptionEnd = PointOption.point(rect.x + rect.width / 2,
                rect.y + (int)(rect.height*0.1) );

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


//
//    private MobileElement getProduct(String needle){
//
//        // swipe once if needed
//        for (int i = 0; i < 2; i++) {
//            MobileElement needleFirst = findElements(catalogListLocator).stream().filter(elm -> elm.findElement(productLocator).getText().contains(needle)).findFirst().orElse(null);
//            if (needleFirst != null)
//                return needleFirst;
//            // swipe
//            swipeElement(find(allProductsLocator), "UP");
//        }
//        return null;
//    }
//


}
