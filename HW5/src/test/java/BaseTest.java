import HW5.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class BaseTest {
    private WebDriver driver;
    private InitWebDriver.BrowserVersion browserVersion;

    @BeforeMethod
    @Parameters("browser")
    public void createDriver(@Optional("MobileAndroid") String browser){
        WebDriver webDriver = null;
        if (browser.equals("Chrome")){
            webDriver = InitWebDriver.initChromeDriver();
            browserVersion = browserVersion.DESKTOP_VERSION;
        } else if (browser.equals("Firefox")){
            webDriver = InitWebDriver.initFirefoxDriver();
            browserVersion = browserVersion.DESKTOP_VERSION;
        } else if (browser.equals("IE")){
            webDriver = InitWebDriver.initIEDriver();
            browserVersion = browserVersion.DESKTOP_VERSION;
        } else if (browser.equals("remoteChrome")){
            webDriver = InitWebDriver.initRemoteChrome();
            browserVersion = browserVersion.DESKTOP_VERSION;
        } else if (browser.equals("MobileAndroid")){
            webDriver = InitWebDriver.initMobileAndroid();
            browserVersion = browserVersion.MOBILE_VERSION;
        }
    driver = new EventFiringWebDriver(webDriver).register(new MyEventHandler());
    }

    @Test
    public void checkOpenedVersion(){
        ShopMainPage shopMainPage = new ShopMainPage(driver);
        shopMainPage.mainPage();
        Assert.assertTrue(shopMainPage.isCorrectBrowser(browserVersion), "Incorrect browser Version was opened");
    }

    @Test
    public void orderProduct(){
        ShopMainPage shopMainPage = new ShopMainPage(driver);
        shopMainPage.mainPage();
        shopMainPage.goToAllProductsList();
        shopMainPage.randomProduct();

        ProductPage productPage = new ProductPage(driver);
        String productName = productPage.getProductName();
        String productPrice = productPage.getProductPrice();
        Integer productQuantity = productPage.getProductQuantity();
        productPage.clickAddToCartBtn();
        productPage.clickMakePurchaseBtn();

        CartPage cartPage = new CartPage(driver);
        Assert.assertEquals(cartPage.getCartProductName().toLowerCase(), productName.toLowerCase(), "Wrong product name in the cart");
        Assert.assertEquals(cartPage.getCartProductPrice(), productPrice, "Price does not match" );
        Assert.assertEquals(cartPage.getCartProductQuantity(), 1, "Wrong quantity of products in the cart");
        cartPage.clickFinalProductOrderBtn();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.inputName();
        checkoutPage.inputSurname();
        checkoutPage.inputEmail();
        checkoutPage.clickContinueBtn1();
        checkoutPage.inputAddress();
        checkoutPage.inputPostcode();
        checkoutPage.inputCity();
        checkoutPage.clickContinueBtn2();
        checkoutPage.clickContinueBtn();
        checkoutPage.clickPayMethodBtn();
        checkoutPage.clickTermsApprove();
        checkoutPage.clickOrderBtn();

        OrderConfirm orderConfirm = new OrderConfirm(driver);
        Assert.assertEquals(
                "Ваш заказ подтверждён".toLowerCase(),
                orderConfirm.getOrderConfirmationTitle().toLowerCase(),
                "Wrong confirmation message"
        );
        Assert.assertEquals(1, orderConfirm.getProductQuantity(), "Product quantity mismatch");
        Assert.assertTrue(
                orderConfirm.getProductName().toLowerCase().startsWith(productName.toLowerCase()),
                "Product name mismatch"
        );
        Assert.assertEquals(productPrice, orderConfirm.getProductPrice(), "Product price mismatch");

        productPage.navigateToProduct();
        productQuantity -= 1;
        Assert.assertEquals(
                productQuantity,
                productPage.getProductQuantity(),
                "Product quantity mismatch"
        );
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
