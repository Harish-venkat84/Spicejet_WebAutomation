package page_object_model;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Getter
public class HomePage {

    WebDriver driver;

    public HomePage(WebDriver driver){

        this.driver = driver;

        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[text() = 'Login']")
    private List<WebElement> login;

    public WebElement getUserName(String userName) {

        try{
            return driver.findElement(By.xpath("//div[text() = 'Hi "+userName+"']"));
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        return null;
    }

    @FindBys(
            @FindBy(xpath = "//div[text() = 'Hi Harish']//parent::div//parent::div//child::div")
    )private List<WebElement> accountDropdown;

    @FindBy(xpath = "//div[text() = 'Log Out']")
    private WebElement logout;

    @FindBy(xpath = "//div[text() = 'Invalid Username/Password']")
    private WebElement loginErrorMessage;


}
