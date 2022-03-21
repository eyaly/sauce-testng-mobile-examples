//package com.saucelabs.tests.both;
//
//import com.saucelabs.pages.both.CatalogPage;
//import org.testng.annotations.Test;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//public class DemoTest extends BaseTest {
//
//    @Test
//    public void demoTest() throws InterruptedException {
//        System.out.println("Sauce - Start demoTest test");
//        CatalogPage catalogPage = new CatalogPage(getDriver());
//
//        assertThat(catalogPage.isOnCatalogPage()).as("Verify catalog page").isTrue();
//
//        try
//        {
//            Thread.sleep(2000);
//        }
//        catch(InterruptedException ex)
//        {
//            Thread.currentThread().interrupt();
//        }
//
//    }
//
//}
