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

public class AddDemotywatorPage {
    private WebDriver driver;
    @FindBy(id = "title")
    private WebElement demotywatorTitle;
    @FindBy(id = "text1")
    private WebElement imageSignature;
    @FindBy(id = "source")
    private WebElement imageSource;
    @FindBy(id = "is_ordinary")
    private WebElement noSignature;
    @FindBy(id = "is_top_title")
    private WebElement topTitle;
    @FindBy(id = "tags")
    private WebElement tags;
    @FindBy(id = "submit")
    private WebElement addDemotywator;
    public AddDemotywatorPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }
    public AddDemotywatorPage addDemotywatorNoImage(String demotywatorTitleText, String demotywatorSignature, String demotywatorSource, String tagi) throws IOException {
        demotywatorTitle.sendKeys(demotywatorTitleText);
        imageSignature.sendKeys(demotywatorSignature);
        imageSource.sendKeys(demotywatorSource);
        noSignature.click();
        topTitle.click();
        tags.sendKeys(tagi);
        addDemotywator.click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Nieprawidłowy plik')]")));
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("./screenshots/AddDemotywatorTest1/addDemotywatorTest1.png"));
        return new AddDemotywatorPage(driver);

    }
}
