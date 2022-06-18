package pl.demotywatory;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private WebDriver driver;
    @FindBy(xpath = "//body/div[@id='sd-cmp']/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/button[2]/span[1]")
    private WebElement acceptCookiesButton;
    @FindBy(id = "mlogin")
    private WebElement singInButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public HomePage openPage() {
        driver.get("https://demotywatory.pl");
        return new HomePage(driver);
    }

    public HomePage acceptCookies() {
        acceptCookiesButton.click();
        return new HomePage(driver);
    }

    public LoginPage clickSignIn() {
        singInButton.click();
        return new LoginPage(driver);
    }
}
