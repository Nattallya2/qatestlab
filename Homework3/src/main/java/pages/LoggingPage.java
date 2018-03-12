package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class LoggingPage {
    private EventFiringWebDriver driver;

    private By emailInput = By.id("email");
    private By passInput = By.id("passwd");
    private By loginBtn = By.name("submitLogin");
    private String email = "webinar.test@gmail.com";
    private String password = "Xcg7299bnSmMuRLp9ITw";
    private String adminurl = "http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/";

    public LoggingPage(EventFiringWebDriver driver){
        this.driver = driver;
    }

    public void open(){
        driver.get(adminurl);
    }

    public void fillEmailInput(){
        driver.findElement(emailInput).sendKeys(email);
    }

    public void fillPassInput(){
        driver.findElement(passInput).sendKeys(password);
    }

    public void clickLoginBtn() {
        driver.findElement(loginBtn).click();
    }
}