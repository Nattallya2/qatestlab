package HW5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderConfirm {
    private WebDriver driver;
    private By orderConfirmationMessage = By.cssSelector(".h1.card-title");
    private By productQuantity = By.cssSelector(".col-xs-2");
    private By productName = By.cssSelector("#order-items > div > div > div.col-sm-4.col-xs-9.details > span");
    private By productPrice = By.xpath("//*[@id=\"order-items\"]/div/table[2]/tbody/tr[1]/td[2]");

    public OrderConfirm(WebDriver driver){
        this.driver = driver;
    }

    public String getOrderConfirmationTitle(){
        return driver.findElement(orderConfirmationMessage).getText().substring(1);
    }

    public int getProductQuantity(){
        return Integer.parseInt(driver.findElement(productQuantity).getText());
    }

    public String getProductName(){
        return driver.findElement(productName).getText();
    }

    public String getProductPrice(){
        String itemPrice = driver.findElement(productPrice).getText();
        itemPrice = itemPrice.replace("\u00a0", "").replace("₴", "");
        itemPrice = itemPrice.trim();
        return itemPrice;
    }
}
