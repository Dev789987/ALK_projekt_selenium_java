import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pl.demotywatory.AddDemotywatorPage;
import pl.demotywatory.HomePage;
import pl.demotywatory.LoginPage;
import pl.demotywatory.MainPage;

import java.io.IOException;
import java.time.Duration;

public class AddDemotywatorTest1 {
    public WebDriver driver;
    public HomePage homePage;
    LoginPage loginPage;
    MainPage mainPage;
    public AddDemotywatorPage addDemotywatorPage;

    @BeforeTest
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.manage().window().maximize();
    }
    @Test
    public void addDemotywatorNoImage () throws IOException {
        homePage = new HomePage(driver).openPage();
        homePage = homePage.acceptCookies();
        loginPage = homePage.clickSignIn();
        mainPage = loginPage.clickLoginButtonAllCorrectData("Mariantesterski","Qazxswedc11@");
        addDemotywatorPage = mainPage.clickAddDemotywator();
        addDemotywatorPage = addDemotywatorPage.addDemotywatorNoImage("Demotywator testowy", "Testowy podpis", "Testowe źródło", "Testowe tagi");
        Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Nieprawidłowy plik')]")).isDisplayed());
    }
    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}


