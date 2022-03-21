package com.saucelabs.tests.ios;

import com.saucelabs.tests.ios.BaseTest;
import org.testng.annotations.Test;
import com.saucelabs.pages.ios.CatalogPage;

import static org.assertj.core.api.Assertions.assertThat;

public class DemoTest extends BaseTest {

    @Test
    public void demoTest_selectProduct() throws InterruptedException {
        System.out.println("Sauce - Start demoTest test");
        CatalogPage catalogPage = new CatalogPage(getDriver());
        assertThat(catalogPage.isOnCatalogPage()).as("Verify catalog page").isTrue();
        catalogPage.addProductToCart("Sauce Labs Backpack");
    }

    @Test
    public void demoTest_scrollAndSelectProduct() throws InterruptedException {
        System.out.println("Sauce - Start demoTest2 test");
        CatalogPage catalogPage = new CatalogPage(getDriver());
        assertThat(catalogPage.isOnCatalogPage()).as("Verify catalog page").isTrue();
        catalogPage.addProductToCart("Test.allTheThings() T-Shirt");
    }

}
