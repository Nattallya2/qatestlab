package HW4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class LoginAdmin {
    private EventFiringWebDriver logDriver;
    private By emailInput = By.cssSelector("input#email.form-control");
    private By passInput = By.id("passwd");
    private By loginBtn = By.name("submitLogin");
    private String adminurl = "http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/";

    public LoginAdmin(EventFiringWebDriver logDriver){
        this.logDriver = logDriver;
    }

    public void open(){
        logDriver.get(adminurl);
    }

    public void fillEmailInput(String login){
        logDriver.findElement(emailInput).sendKeys(login);
    }

    public void fillPassInput(String password){
        logDriver.findElement(passInput).sendKeys(password);
    }

    public void clickLoginBtn() {
        logDriver.findElement(loginBtn).click();
    }

    public void logInData(String login, String password){
        fillEmailInput(login);
        fillPassInput(password);
        clickLoginBtn();
    }
}
