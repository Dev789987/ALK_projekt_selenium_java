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
import pl.demotywatory.RegisterPage;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

public class RegisterTest2 {
    public WebDriver driver;
    String CSV_PATH = "./data/registerData.csv";
    private CSVReader csvReader;
    String[] csvCell;
    public HomePage homePage;
    public LoginPage loginPage;
    public RegisterPage registerPage;

    @BeforeTest
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.manage().window().maximize();
    }
    @Test (description = "Test rejestracji użytkownika niepełnymi danymi - niewprowadzenie adresu email")
    public void registerTestNoEmail() throws IOException, CsvValidationException, InterruptedException {
        csvReader = new CSVReader(new FileReader(CSV_PATH));
        while((csvCell = csvReader.readNext()) != null) {
            homePage = new HomePage(driver).openPage();
            homePage = homePage.acceptCookies();
            loginPage = homePage.clickSignIn();
            registerPage = loginPage.clickRegisterButton();
            registerPage = registerPage.inputDataNoEmail(csvCell[0],csvCell[2], csvCell[3]);
            Assert.assertEquals(driver.findElement(By.className("errorMessage")).getText(), "Pole wymagane");
        }
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }


}

