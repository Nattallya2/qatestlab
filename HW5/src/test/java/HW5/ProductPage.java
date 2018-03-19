package HW5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {
    private String productPageUrl;
    private WebDriver driver;
    private By addToCartBtn = By.cssSelector(".add-to-cart");
    private By makePurchase = By.cssSelector(".modal-content .btn.btn-primary");
    private By productName = By.xpath("//*[@id=\"main\"]/div[1]/div[2]/h1");
    private By productQuantity = By.cssSelector(".product-quantities span");
    private By productPrice = By.cssSelector(".current-price>span");
    private By detailLink = By.cssSelector("a[href='#product-details']");

    public ProductPage(WebDriver driver){
        this.driver = driver;
        this.productPageUrl = driver.getCurrentUrl();
    }

    public void navigateToProduct() {
        this.driver.get(this.productPageUrl);
    }

    public String getProductName(){
        return driver.findElement(productName).getText();
    }

    public Integer getProductQuantity(){
        if (driver.findElements(detailLink).size() > 0) {
            driver.findElement(detailLink).click();
        }
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(productQuantity)));
        return Integer.parseInt(driver.findElement(productQuantity).getText().replaceAll("[\\D]", ""));
    }

    public String getProductPrice(){
        String price = driver.findElement(productPrice).getText();
        price = price.replace("\u00a0", "").replace("₴", "");
        price = price.trim();
        return price;
    }

    public void clickAddToCartBtn(){
        driver.findElement(addToCartBtn).click();
    }

    public void clickMakePurchaseBtn(){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.presenceOfElementLocated(makePurchase));
        wait.until(ExpectedConditions.visibilityOfElementLocated(makePurchase));
        driver.findElement(makePurchase).click();
    }
}
