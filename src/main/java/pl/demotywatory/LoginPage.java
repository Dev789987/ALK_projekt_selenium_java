package pl.demotywatory;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.IOException;

public class LoginPage {
    private WebDriver driver;

    @FindBy(xpath = "//body[1]/div[3]/div[2]/div[3]/div[1]/div[6]/section[1]/article[1]/div[1]/form[1]/table[1]/tbody[1]/tr[4]/td[2]/input[1]")
    private WebElement loginButton;

    @FindBy(xpath = "//body[1]/div[3]/div[2]/div[3]/div[1]/div[6]/section[1]/article[1]/div[1]/form[1]/table[1]/tbody[1]/tr[5]/td[2]/a[1]")
    private WebElement registerButton;

    @FindBy(xpath = "//body[1]/div[3]/div[2]/div[3]/div[1]/div[6]/section[1]/article[1]/div[1]/form[1]/table[1]/tbody[1]/tr[1]/td[2]/input[1]")
    private WebElement inputUserName;

    @FindBy(xpath = "//body[1]/div[3]/div[2]/div[3]/div[1]/div[6]/section[1]/article[1]/div[1]/form[1]/table[1]/tbody[1]/tr[2]/td[2]/input[1]")
    private WebElement inputPassword;

    @FindBy(xpath = "//body[1]/div[3]/div[2]/div[3]/div[1]/div[6]/section[1]/article[1]/div[1]/form[1]/table[1]/tbody[1]/tr[3]/td[2]/input[1]")
    private WebElement noLogoutButton;

    public LoginPage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public RegisterPage clickRegisterButton() {
        registerButton.click();
        return new RegisterPage(driver);
    }

    public MainPage clickLoginButtonAllCorrectData(String userName, String validPassword) throws IOException {
        inputUserName.sendKeys(userName);
        inputPassword.sendKeys(validPassword);
        noLogoutButton.click();
        loginButton.click();
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("./screenshots/LoginTest1/succesLogin.png"));
        return new MainPage(driver);
    }

    public LoginPage clickLoginButtonInvalidPassword(String userName, String invalidPassword) throws IOException {
        inputUserName.sendKeys(userName);
        inputPassword.sendKeys(invalidPassword);
        noLogoutButton.click();
        loginButton.click();
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("./screenshots/LoginTest2/unsuccesLoginInvalidPassword.png"));
        return new LoginPage(driver);
    }
}
