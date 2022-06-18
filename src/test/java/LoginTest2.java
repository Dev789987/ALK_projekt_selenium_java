import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pl.demotywatory.HomePage;
import pl.demotywatory.LoginPage;


import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

public class LoginTest2 {
    public WebDriver driver;
    String CSV_PATH = "./data/validLoginData.csv";
    private CSVReader csvReader;
    String[] csvCell;
    public HomePage homePage;
    public LoginPage loginPage;
    @BeforeTest
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.manage().window().maximize();
    }
@Test(description = "Test logowanie użytkonika - niepoprawne hasło")
public void loginTestInvalidPassword() throws IOException, CsvValidationException {
    csvReader = new CSVReader(new FileReader(CSV_PATH));
    while((csvCell = csvReader.readNext()) != null) {
        homePage = new HomePage(driver).openPage();
        homePage = homePage.acceptCookies();
        loginPage = homePage.clickSignIn();
        loginPage = loginPage.clickLoginButtonInvalidPassword(csvCell[0], csvCell[2]);
        Assert.assertTrue(driver.findElement(By.id("messages")).isDisplayed());
    }
}
    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}