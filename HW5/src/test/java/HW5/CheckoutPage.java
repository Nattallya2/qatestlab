package HW5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

public class CheckoutPage {
    private WebDriver driver;
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

    public CheckoutPage(WebDriver driver){
        this.driver = driver;
    }


    public static String generateRandomString (int length){
        Random random = new Random();

        char[] values = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
                'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
                'u', 'v', 'w', 'x', 'y', 'z'};

        String out = "";

        for (int i = 0; i < length; i++) {
            int idx = random.nextInt(values.length);
            out += values[idx];
        }
        return out;
    }


    public void inputName(){
        String name = "Name" + generateRandomString(5);
        driver.findElement(nameField).sendKeys(name);
    }

    public void inputSurname(){
        String surname = "Surname"+ generateRandomString(5);
        driver.findElement(surnameField).sendKeys(surname);
    }

    public void inputEmail(){
        String email = "email" + generateRandomString(5) + "@test.com";
        driver.findElement(emailField).sendKeys(email);
    }

    public void clickContinueBtn1(){
        driver.findElement(continue1Btn).click();
    }

    public void inputAddress(){
        String address = "Street " + String.valueOf((System.currentTimeMillis()));
        driver.findElement(addressField).sendKeys(address);
    }

    public void inputPostcode(){
        String postcode = String.valueOf((long) (10000 + (int)(Math.random()*89999)));
        driver.findElement(postCodeField).sendKeys(postcode);
    }

    public void inputCity(){
        String city = "City" + String.valueOf((System.currentTimeMillis()));
        driver.findElement(deliveryCityField).sendKeys(city);
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
