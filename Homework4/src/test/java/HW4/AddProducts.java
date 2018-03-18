package HW4;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddProducts {
    private EventFiringWebDriver logDriver;
    private By addProduct = By.className("m-b-2");

    private By productNameTab = By.xpath("//*[@id=\"form_step1_name_1\"]");
    private By productQuantityTab = By.xpath("//*[@id=\"form_step1_qty_0_shortcut\"]");
    private By productPriceTab = By.xpath("//*[@id=\"form_step1_price_shortcut\"]");
    private By activateProduct = By.className("switch-input");
    private By activationNotification = By.cssSelector("#growls .growl-notice .growl-close");
    private By submitBtn = By.xpath("//*[@id=\"form\"]/div[4]/div[2]/div/button[1]");

    public AddProducts(EventFiringWebDriver logDriver){
        this.logDriver = logDriver;
    }

    public void clickAddProductBtn(){
        logDriver.findElement(addProduct).click();
    }

    public void fillProductName(String productName){
        logDriver.findElement(productNameTab).sendKeys(productName);
    }

    public void fillQuantity(String productQuntity){
        logDriver.findElement(productQuantityTab).clear();
        logDriver.findElement(productQuantityTab).sendKeys(productQuntity);
    }

    public void fillProductPriceTab(String productPrice){
        logDriver.findElement(productPriceTab).sendKeys(Keys.CONTROL,"a");
        logDriver.findElement(productPriceTab).sendKeys(Keys.CONTROL,"x");
        logDriver.findElement(productPriceTab).sendKeys(productPrice);
    }

    public void successfullNotificationAppearAndClose(){
        WebDriverWait wait = new WebDriverWait(logDriver,5);
        wait.until(ExpectedConditions.presenceOfElementLocated(activationNotification));
        logDriver.findElement(activationNotification).click();
    }

    public void activateProductBtn(){
        logDriver.findElement(activateProduct).click();
        successfullNotificationAppearAndClose();
    }

    public void submitButton(){
       logDriver.findElement(submitBtn).submit();
       successfullNotificationAppearAndClose();
    }
}
