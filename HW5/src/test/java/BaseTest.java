import HW5.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

public class BaseTest {
    private WebDriver driver;
    private InitWebDriver.BrowserVersion browserVersion;

    @BeforeMethod
    @Parameters("browser")
    public void createDriver(@Optional("Firefox") String browser){
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
        Reporter.setEscapeHtml(false);
    }

    @Test
    public void checkOpenedVersion(){
        ShopMainPage shopMainPage = new ShopMainPage(driver);
        log("Open the main Page of the shop");
        shopMainPage.mainPage();
        log(driver.getCurrentUrl());
        log("Check Browser version");
        Assert.assertTrue(shopMainPage.isCorrectBrowser(browserVersion), "Incorrect browser Version was opened");
        log( browserVersion + " was opened");
    }

    @Test
    public void orderProduct(){
        ShopMainPage shopMainPage = new ShopMainPage(driver);
        shopMainPage.mainPage();
        log("Get all product list");
        shopMainPage.goToAllProductsList();
        log("Choose a random product: ");
        shopMainPage.randomProduct();
        log(driver.getTitle());

        log("Get productName, productPrice, productQuantity:");
        ProductPage productPage = new ProductPage(driver);
        String productName = productPage.getProductName();
        log(" - " + productName);
        String productPrice = productPage.getProductPrice();
        log(" - " + productPrice);
        Integer productQuantity = productPage.getProductQuantity();
        log(" - " + productQuantity);
        log("Add product to the Cart");
        productPage.clickAddToCartBtn();
        log("Click on make a purchase button");
        productPage.clickMakePurchaseBtn();

        CartPage cartPage = new CartPage(driver);
        log("Check product information before making order:");
        Assert.assertEquals(cartPage.getCartProductName().toLowerCase(), productName.toLowerCase(), "Wrong product name in the cart");
        Assert.assertEquals(cartPage.getCartProductPrice(), productPrice, "Price does not match" );
        Assert.assertEquals(cartPage.getCartProductQuantity(), 1, "Wrong quantity of products in the cart");
        cartPage.clickFinalProductOrderBtn();

        RandomCheckoutData randomCheckoutData = new RandomCheckoutData();
        CheckoutPage checkoutPage = new CheckoutPage(driver, randomCheckoutData);
        log("Fill in personal information: ");
        checkoutPage.inputName();
        log(" - name: " + randomCheckoutData.getName());
        checkoutPage.inputSurname();
        log(" - surname: " + randomCheckoutData.getSurname());
        checkoutPage.inputEmail();
        log(" - Email: " + randomCheckoutData.getEmail());
        checkoutPage.clickContinueBtn1();
        checkoutPage.inputAddress();
        log(" - Address: " + randomCheckoutData.getAddress());
        checkoutPage.inputPostcode();
        log(" - postcode: " + randomCheckoutData.getPostcode());
        checkoutPage.inputCity();
        log(" - city: " + randomCheckoutData.getCity());
        checkoutPage.clickContinueBtn2();
        log("Continue ordering process");
        checkoutPage.clickContinueBtn();
        log("Choose desired payment method");
        checkoutPage.clickPayMethodBtn();
        checkoutPage.clickTermsApprove();
        log("Make an order");
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

    private void log(String message) {
        Reporter.log(message + "<br>");
    }
}
