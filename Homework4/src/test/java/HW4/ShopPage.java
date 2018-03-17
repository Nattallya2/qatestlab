package HW4;

import org.openqa.selenium.By;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class ShopPage {
    private EventFiringWebDriver logDriver;
    private By allProductLink = By.xpath("//*[@id=\"content\"]/section/a");
    private By nextLink = By.xpath("//*[@id=\"js-product-list\"]/nav/div[2]/ul/li[3]/a");

    public ShopPage(EventFiringWebDriver logDriver){
        this.logDriver = logDriver;
    }

    public void openShop(){
        logDriver.get("http://prestashop-automation.qatestlab.com.ua/");
    }

    public void clickAllProductLink(){
        logDriver.findElement(allProductLink).click();
    }

    public Boolean itemPresentText (String linkText) {
        Boolean itemPresent = false;
        if (logDriver.findElements(nextLink).size() == 0) {
            if (logDriver.findElements(By.xpath("//*[contains(text(), '" + linkText + "')]")).size() > 0) {
                itemPresent = true;
            }
        } else {
            while (logDriver.findElements(nextLink).size() > 0) {
                if (logDriver.findElements(By.xpath("//*[contains(text(), '" + linkText + "')]")).size() > 0) {
                    itemPresent = true;
                    break;
                }
                logDriver.findElement(nextLink).click();
            }

        }
        return itemPresent;
    }

    public void clickProduct(String linkText){
        logDriver.findElement(By.xpath(("//*[contains(text(), '" + linkText + "')]"))).click();
    }
}
