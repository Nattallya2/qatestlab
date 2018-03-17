package HW4;

import org.openqa.selenium.By;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class ProductShopPage {
    private EventFiringWebDriver logDriver;
    private By productName = By.className("h1");
    private By currentPrice = By.cssSelector("[itemprop='price']");
    private By productQuantuty = By.cssSelector(".product-quantities span");

    public ProductShopPage(EventFiringWebDriver logdriver){
        this.logDriver = logdriver;
    }

    public String getProductName(){
        return logDriver.findElement(productName).getAttribute("textContent");
    }

    public String getProductPrice(){
        String price = logDriver.findElement(currentPrice).getAttribute("textContent").toString();
        price = price.replace("₴", "").replace("\u00a0","").trim();
        return price;
    }

    public String getProductQuantity(){
        return logDriver.findElement(productQuantuty).getAttribute("textContent").toString().replace(" Товары", "").trim();
    }

}
