package com.saucelabs.pages.ios;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public abstract class BasePage {

    private IOSDriver driver;

    public IOSDriver getDriver() {
        return driver;
    }

    public BasePage(IOSDriver driver) {
        this.driver = driver;
    }

    public WebElement find(By locator) {
        return driver.findElement(locator);
    }
    public List<MobileElement> findElements(By locator) {
        return (List<MobileElement>)driver.findElements(locator);
    }

    public void click(By locator) {
        find(locator).click();
    }

    public void type(String inputText, By locator) {
        find(locator).sendKeys(inputText);
    }

    public void clickAndType(String inputText, By locator) {
        click(locator);

        Actions a = new Actions(getDriver());
        a.sendKeys(inputText);
        a.perform();
    }

    public Boolean isDisplayed(By locator) {
        try {
            return find(locator).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException exception) {
            return false;
        }
    }

    public Boolean isDisplayed(By locator, long timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (org.openqa.selenium.TimeoutException exception) {
            return false;
        }
        return true;
    }

    public void waitDisplayed(By locator, long timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (org.openqa.selenium.TimeoutException exception) {
            System.out.println("*** The element wasn't diplayed ***");
        }

    }

    public void hideKeyboard(){
        driver.getKeyboard().sendKeys(Keys.RETURN);
    }

    public void switchToNativeViewContext() {
        System.out.println("*** Switch to native view context ***");
        driver.context("NATIVE_APP");
    }

    protected boolean switchToWebViewContext() {
        ArrayList<String> contexts = new ArrayList(driver.getContextHandles());
        for (String context : contexts) {
            System.out.println(context);
            if (context.contains("WEBVIEW")) {
                driver.context(context);
                return true;
            } else {
                System.out.println("*** There is no webview context ***");
            }

        }
        return false;
    }

    protected void waiting(int sec){
        try
        {
            Thread.sleep(sec*1000);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }
}
