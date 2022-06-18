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
import pl.demotywatory.MainPage;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

public class LoginTest1 {
    public WebDriver driver;
    String CSV_PATH = "./data/validLoginData.csv";
    private CSVReader csvReader;
    String[] csvCell;
    public HomePage homePage;
    public LoginPage loginPage;
    public MainPage mainPage;

    @BeforeTest
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.manage().window().maximize();
    }
    @Test (description = "Test logowania użytkownika poprawnymi danymi")
    public void loginTestCorrectData() throws IOException, CsvValidationException {
        csvReader = new CSVReader(new FileReader(CSV_PATH));
        while((csvCell = csvReader.readNext()) != null) {
            homePage = new HomePage(driver).openPage();
            homePage = homePage.acceptCookies();
            loginPage = homePage.clickSignIn();
            mainPage = loginPage.clickLoginButtonAllCorrectData(csvCell[0], csvCell[1]);
            Assert.assertEquals(driver.findElement(By.xpath("//h2[contains(text(),'Twoje demotywatory')]")).getText(),"Twoje demotywatory");
        }
    }
    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
