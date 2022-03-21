package com.saucelabs.tests.android;

import com.saucelabs.pages.android.CatalogPage;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DemoTest extends BaseTest {

    @Test
    public void demoTest_SelectProduct() throws InterruptedException {
        System.out.println("Sauce - Start demoTest test");
        CatalogPage catalogPage = new CatalogPage(getDriver());
        assertThat(catalogPage.isOnCatalogPage()).as("Verify catalog page").isTrue();
        catalogPage.addProductToCart("Backpack");
//        catalogPage.addProductToCart("Test.sllTheThings() T-Shirt");
    }

    @Test
    public void demoTest_ScrollAndSelectProduct() throws InterruptedException {
        System.out.println("Sauce - Start demoTest test");
        CatalogPage catalogPage = new CatalogPage(getDriver());
        assertThat(catalogPage.isOnCatalogPage()).as("Verify catalog page").isTrue();
//        catalogPage.addProductToCart("Sauce Lab Fleece T-Shirt");
        catalogPage.addProductToCart("Test.allTheThings");
    }

}
