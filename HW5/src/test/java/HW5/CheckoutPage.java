package HW5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
    private WebDriver driver;
    private RandomCheckoutData randomCheckoutData;

    private By nameField = By.xpath("//*[@id=\"customer-form\"]/section/div[2]/div[1]/input");
    private By surnameField = By.xpath("//*[@id=\"customer-form\"]/section/div[3]/div[1]/input");
    private By emailField = By.xpath("//*[@id=\"customer-form\"]/section/div[4]/div[1]/input");
    private By continue1Btn = By.xpath("//*[@id=\"customer-form\"]/footer/button");
    private By addressField = By.xpath("//*[@id=\"delivery-address\"]/div/section/div[5]/div[1]/input");
    private By postCodeField = By.xpath("//*[@id=\"delivery-address\"]/div/section/div[7]/div[1]/input");
    private By deliveryCityField = By.xpath("//*[@id=\"delivery-address\"]/div/section/div[8]/div[1]/input");
    private By continue2Btn = By.xpath("//*[@id=\"delivery-address\"]/div/footer/button");
    private By deliveryBtn = By.xpath("//*[@id=\"js-delivery\"]/button");
    private By paymentChoice = By.cssSelector("#payment-option-1");
    private By termsApproveCheckbox = By.xpath("//*[@id=\"conditions_to_approve[terms-and-conditions]\"]");
    private By submitOrderBtn = By.xpath("//*[@id=\"payment-confirmation\"]/div[1]/button");

    public CheckoutPage(WebDriver driver, RandomCheckoutData randomCheckoutData){
        this.driver = driver;
        this.randomCheckoutData = randomCheckoutData;
    }

    public void inputName(){
        driver.findElement(nameField).sendKeys(randomCheckoutData.getName());
    }

    public void inputSurname(){
        driver.findElement(surnameField).sendKeys(randomCheckoutData.getSurname());
    }

    public void inputEmail(){
        driver.findElement(emailField).sendKeys(randomCheckoutData.getEmail());
    }

    public void clickContinueBtn1(){
        driver.findElement(continue1Btn).click();
    }

    public void inputAddress(){
        driver.findElement(addressField).sendKeys(randomCheckoutData.getAddress());
    }

    public void inputPostcode(){
        driver.findElement(postCodeField).sendKeys(randomCheckoutData.getPostcode());
    }

    public void inputCity(){
        driver.findElement(deliveryCityField).sendKeys(randomCheckoutData.getCity());
    }

    public void clickContinueBtn2(){
        driver.findElement(continue2Btn).click();
    }

    public void clickContinueBtn(){
        driver.findElement(deliveryBtn).click();
    }

    public void clickPayMethodBtn(){
        driver.findElement(paymentChoice).click();
    }

    public void clickTermsApprove(){
        driver.findElement(termsApproveCheckbox).click();
    }

    public void clickOrderBtn(){
        driver.findElement(submitOrderBtn).click();
    }
}
