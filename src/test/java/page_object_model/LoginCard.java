package page_object_model;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class LoginCard {

    WebDriver driver;

    public LoginCard(WebDriver driver){

        this.driver = driver;

        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[text() = 'Email']")
    private WebElement emailRadioBtn;

    @FindBy(xpath = "//input[@type='email']")
    private WebElement emailInputField;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordInputField;

    @FindBy(xpath = "//div[@data-testid='login-cta']")
    private WebElement loginBtn;



}
