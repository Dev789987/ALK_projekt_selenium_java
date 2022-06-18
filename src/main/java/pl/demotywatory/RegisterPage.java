package pl.demotywatory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class RegisterPage {
    private WebDriver driver;
    @FindBy(id = "username")
    private WebElement inputLogin;
    @FindBy(id = "email")
    private WebElement inputEmailAdress;
    @FindBy(id = "password")
    private WebElement inputPassword;
    @FindBy(id = "password2")
    private WebElement passwordConfirmation;
    @FindBy(id = "rulesaccepted")
    private WebElement regulationCheckbox;
    @FindBy(id = "rejestruj")
    private WebElement registerButton;
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public RegisterPage inputDataNoLogin(String emailAdress, String password, String confirmPassword) throws IOException {
        inputEmailAdress.sendKeys(emailAdress);
        inputPassword.sendKeys(password);
        passwordConfirmation.sendKeys(confirmPassword);
        regulationCheckbox.click();
        registerButton.click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.className("errorMessage")));
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("./screenshots/RegisterTest1/noLogin.png"));
        return new RegisterPage(driver);
    }

    public RegisterPage inputDataNoEmail(String login, String password, String confirmPassword) throws IOException {
        inputLogin.sendKeys(login);
        inputPassword.sendKeys(password);
        passwordConfirmation.sendKeys(confirmPassword);
        regulationCheckbox.click();
        registerButton.click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.className("errorMessage")));
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("./screenshots/RegisterTest2/noEmailAdress.png"));
        return new RegisterPage(driver);
    }

    public RegisterPage inputDataNoConfirmPassword(String login, String emailAdress, String password) throws IOException {
        inputLogin.sendKeys(login);
        inputEmailAdress.sendKeys(emailAdress);
        inputPassword.sendKeys(password);
        regulationCheckbox.click();
        registerButton.click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.className("errorMessage")));
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("./screenshots/RegisterTest3/noConfirmPassword.png"));
        return new RegisterPage(driver);
    }

}
