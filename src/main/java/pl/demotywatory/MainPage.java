package pl.demotywatory;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class MainPage {
    private WebDriver driver;
    @FindBy(id = "madd")
    private WebElement addDemotywatorButton;



    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public AddDemotywatorPage clickAddDemotywator(){
        addDemotywatorButton.click();
        return new AddDemotywatorPage(driver);
    }
}
