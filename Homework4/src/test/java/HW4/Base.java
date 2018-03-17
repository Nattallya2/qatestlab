package HW4;

import HW4.EventHandler;
import HW4.InitWebDriver;
import HW4.LoginAdmin;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Reporter;
import org.testng.annotations.*;
import org.testng.asserts.Assertion;

public class Base {
    private EventFiringWebDriver logDriver;

    @BeforeClass
    @Parameters("browser")
    public void createDriver(@Optional("Chrome") String browser){
        System.out.println("createDriver");
        WebDriver driver = null;
        if (browser.equals("Chrome")){
            driver = InitWebDriver.initChromeDriver();
        } else if (browser.equals("Firefox")){
            driver = InitWebDriver.initFirefoxDriver();
        } else if (browser.equals("IE")){
            driver = InitWebDriver.initIEDriver();
        }
        logDriver = new EventFiringWebDriver(driver);
        logDriver.register(new EventHandler());
        Reporter.setEscapeHtml(false);
    }

    @DataProvider
    public Object[][] successLogin() {
        return new Object[][]{
                {"webinar.test@gmail.com", "Xcg7299bnSmMuRLp9ITw"}
        };
    }

    @Test(dataProvider = "successLogin")
    public void createProduct(String login, String password){
        LoginAdmin loginAdmin = new LoginAdmin(logDriver);
        log("Open admin page");
        loginAdmin.open();
        log("Login,password fields filled");
        loginAdmin.logInData(login,password);
        log("Get to the products tab");
        new AdminDashboard(logDriver).selectProductsTab();

        String productName = "Product_" + String.valueOf((long) (System.currentTimeMillis()*Math.random()));
        String productQuantity = String.valueOf((int) (100*Math.random()));
        String productPrice = String.format("%.2f",100*Math.random()).replace(".", ",");


        AddProducts addProducts = new AddProducts(logDriver);
        log("Click the New product button");
        addProducts.clickAddProductBtn();
        log("Fill in with product name");
        addProducts.fillProductName(productName);
        log("Fill in with product Quantity");
        addProducts.fillQuantity(productQuantity);
        log("Fill in with product Price");
        addProducts.fillProductPriceTab(productPrice);
        log("Activate product and wait message");
        addProducts.activateProductBtn();
        log("Click save button");
        addProducts.submitButton();

        ShopPage shopPage = new ShopPage(logDriver);
        log("Go to the shop page");
        shopPage.openShop();
        log("Choose all products link");
        shopPage.clickAllProductLink();

        Assertion assertion = new Assertion();
        log("Check if product was found");
        assertion.assertTrue(shopPage.itemPresentText(productName), "Product" + productName + "was not found");
        shopPage.clickProduct(productName);

        ProductShopPage productShopPage = new ProductShopPage(logDriver);
        log("Check if product name is the same");
        assertion.assertTrue(
                productShopPage.getProductName().equals(productName),
                "Product name " + productShopPage.getProductName() + " is not equal to " + productName
        );
        log("Check if product price is the same");
        assertion.assertTrue(
                productShopPage.getProductPrice().equals(productPrice),
                "Product price " + productShopPage.getProductPrice() + " is not equal to " + productPrice
        );
        log("Check if product quantity is the same");
        assertion.assertTrue(
                productShopPage.getProductQuantity().equals(productQuantity),
                "Product quantity " + productShopPage.getProductQuantity() + " is not equal to " + productQuantity
        );
    }

    @AfterClass
    public void tearDown(){
        logDriver.quit();
    }

    private void log(String message) {
        Reporter.log(message + "<br>");
    }
}
