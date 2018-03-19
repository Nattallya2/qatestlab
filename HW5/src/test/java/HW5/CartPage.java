package HW5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    private WebDriver driver;
    private By productName = By.cssSelector(".product-line-info>a.label");
    private By productQuantity = By.cssSelector("#cart-subtotal-products > span.label.js-subtotal");
    private By productPrice = By.cssSelector(".product-price");
    private By finalProductOrderBtn = By.cssSelector(".text-xs-center>a.btn.btn-primary");


    public CartPage(WebDriver driver){
        this.driver = driver;
    }

    public String getCartProductName(){
        return driver.findElement(productName).getText();
    }

    public int getCartProductQuantity(){
        return Integer.parseInt(driver.findElement(productQuantity).getText().replaceAll("[\\D]", ""));
    }

    public String getCartProductPrice(){
        String price = driver.findElement(productPrice).getText();
        price = price.replace("\u00a0", "").replace("₴", "");
        price = price.trim();
        return price;
    }

    public void clickFinalProductOrderBtn(){
        driver.findElement(finalProductOrderBtn).click();
    }

}
