package page_object_manager;

import org.openqa.selenium.WebDriver;
import page_object_model.HomePage;
import page_object_model.LoginCard;

public class PageObjectManager {

    public WebDriver driver;

    public PageObjectManager(WebDriver driver){

        this.driver = driver;
    }

    private HomePage homePage;

    public HomePage getHomePage(){

        return homePage = homePage == null ? new HomePage(driver) : homePage;
    }

    private LoginCard loginCard;

    public LoginCard getLoginCard() {

        return loginCard = loginCard == null ? new LoginCard(driver) : loginCard;
    }
}
