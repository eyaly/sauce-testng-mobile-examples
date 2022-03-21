package com.saucelabs.tests.ios;

import com.google.common.collect.ImmutableMap;
import com.saucelabs.helpers.Utils;
import com.saucelabs.pages.ios.CatalogPage;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PreferencesTest extends BaseTest {

    private static ThreadLocal<Boolean> isFirstRun = new ThreadLocal<Boolean>();

    @Test
    public void enableIosLocationServices() throws InterruptedException {
        System.out.println("Sauce - Start enableIosLocationServices test");

        if (isFirstRun.get() == null){
            isFirstRun.set(Boolean.TRUE);
        }

        // If this is the first execution on the device
        if (isFirstRun.get()) {
            System.out.println("Sauce - Check Location settings");
            getDriver().executeScript("mobile: pressButton", ImmutableMap.of("name", "home"));
            // preferences app
            Utils utils = new Utils();
            utils.enableIosLocationServices(getDriver());
            isFirstRun.set(Boolean.FALSE);
            // back to the app under test
            getDriver().launchApp();
        }

        Utils.waiting(2);
    }

    @Test
    public void enableIosLocationServices2() throws InterruptedException {
        System.out.println("Sauce - Start enableIosLocationServices test");

        if (isFirstRun.get() == null){
            isFirstRun.set(Boolean.TRUE);
        }

        // If this is the first execution on the device
        if (isFirstRun.get()) {
            System.out.println("Sauce - Check Location settings");
            getDriver().executeScript("mobile: pressButton", ImmutableMap.of("name", "home"));
            // preferences app
            Utils utils = new Utils();
            utils.enableIosLocationServices(getDriver());
            isFirstRun.set(Boolean.FALSE);
            // back to the app under test
            getDriver().launchApp();
        }

        Utils.waiting(2);
    }

}
