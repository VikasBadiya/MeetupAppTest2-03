import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class RegistrationPageTest {
    public WebDriver driver;

    @BeforeMethod
    public void SetUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://qameetup.ccbp.tech/");
        System.out.println("Browser was Opened");
    }

    @AfterMethod
    public void TearDown(){
        driver.close();
        System.out.println("Browser was Closed");
    }

    //Test the UI of the Registration Page
    //Navigate to the URL https://qameetup.ccbp.tech/
    //Click the "Register" button to navigate to the registration page.
    //Verify if the App logo is displayed - use Assertions,
    //If the App logo is not displayed, print "App logo is not displayed"
    //Verify if the Register image is displayed - use Assertions,
    //If the Register image is not displayed, print "Register image is not displayed"
    //Verify the Heading text of the page - use Assertions
    //Expected text: Let us join
    //If the Heading text does not match the expected text, print "Heading text does not match"
    //Close the browser window.

    @Test(priority = 1)
    public void UIOfTheRegistrationPage(){
        WebElement RegisterButton = driver.findElement(By.id("registerButton"));
        RegisterButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://qameetup.ccbp.tech/register"));
        WebElement MeetUpLogoEle = driver.findElement(By.cssSelector("img[class*='WebsiteLogo']"));
        Assert.assertTrue(MeetUpLogoEle.isDisplayed(),"App logo is not displayed");

        WebElement RegisterImageEle = driver.findElement(By.cssSelector("img[class*='RegisterImage']"));

        Assert.assertTrue(RegisterImageEle.isDisplayed(),"Register image is not displayed");

        WebElement HeadingEle = driver.findElement(By.cssSelector("h1[class*='Heading']"));

        String actualHeadingText = HeadingEle.getText();
        String expectedHeadingText = "Let us join";

        Assert.assertEquals(actualHeadingText,expectedHeadingText,"Heading text does not match");


    }

    //Test the Functionality of the Registration Page with empty name field
    //Navigate to the URL https://qameetup.ccbp.tech/
    //Click the "Register" button to navigate to the registration page.
    //Click the "Register Now" button.
    //Verify the error message - use Assertions,
    //Expected text: Please enter your name
    //If the error message does not match the expected text, print "Error text with empty input field does not match"
    //Close the browser window.

    @Test(priority = 2 )
    public void RegistrationPageWithEmptyNameField(){
        WebElement RegisterButton = driver.findElement(By.id("registerButton"));
        RegisterButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://qameetup.ccbp.tech/register"));

        WebElement RegisterNowButton = driver.findElement(By.cssSelector("button[class*='Register']"));
        RegisterNowButton.click();

        WebElement ErrorMessageEle = driver.findElement(By.cssSelector("p[class*='ErrorMessage']"));

        String actualErrorMessage = ErrorMessageEle.getText();

        String expectedErrorMessage = "Please enter your name";

        Assert.assertEquals(actualErrorMessage,expectedErrorMessage,"Error text with empty input field does not match");

    }

    //Test the Functionality of the Registration Page with valid inputs
    //Navigate to the URL https://qameetup.ccbp.tech/
    //Click the "Register" button to navigate to the registration page.
    //Test the functionality of the Registration Page using various inputs from the given dataset.
    //
    //  Dataset
    //
    //Name	Option Value	Option Text
    //Jack	ARTS_AND_CULTURE	Arts and Culture
    //Jerry	CAREER_AND_BUSINESS	Career and Business
    //John	EDUCATION_AND_LEARNING	Education and Learning
    //Jim	FASHION_AND_BEAUTY	Fashion and Learning
    //Jane	GAMES	Games
    //Enter the name in the "NAME" field
    //Select the corresponding option from the "TOPICS" dropdown.
    //Click the "Register Now" button.
    //Verify the navigation to the registration page - use Assertions,
    //Expected URL: https://qameetup.ccbp.tech/
    //If the current URL does not match the expected URL, print "URLs do not match"
    //Verify the Heading text of the page - use Assertions
    //Expected text: Hello <name>
    //If the Heading text does not match the expected text, print "Heading text does not match"
    //Verify the Description text of the page - use Assertions
    //Expected text: Welcome to <topic>
    //If the Description text does not match the expected text, print "Description text does not match"
    //Close the browser window.

    @DataProvider(name= "dataSet")
    public Object[][]DataSet(){
        return new Object[][]{{"Jack","ARTS_AND_CULTURE","Arts and Culture"},{"Jerry","CAREER_AND_BUSINESS","Career and Business"},{"John","EDUCATION_AND_LEARNING","Education and Learning"},{"Jim","FASHION_AND_BEAUTY","Fashion and Learning"},{"Jane","GAMES","Games"}};

    }
    @Test(priority =3,dataProvider = "dataSet")
    public void RegistrationPageWithValidInputs(  String name ,String value,String text){

        WebElement RegisterButton = driver.findElement(By.id("registerButton"));
        RegisterButton.click();

        WebElement nameInputField = driver.findElement(By.id("name"));
        nameInputField.sendKeys(name);

        Select TopicsEle = new Select(driver.findElement(By.id("topic")));
        TopicsEle.selectByValue(value);


        WebElement RegisterNowButton = driver.findElement(By.cssSelector("button[class*='Register']"));
        RegisterNowButton.click();

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

        wait.until(ExpectedConditions.urlToBe("https://qameetup.ccbp.tech/"));


        WebElement HeadingEle = driver.findElement(By.cssSelector("h1[class*='Heading']"));

        String actualHeadingText = HeadingEle.getText();

        String expectedHeadingText = "Hello " + name;

        Assert.assertEquals(actualHeadingText,expectedHeadingText,"Heading text does not match");


        WebElement DescriptionTextEle = driver.findElement(By.cssSelector("p[class*='Topic']"));

        String actualDescriptionText = DescriptionTextEle.getText();

        String expectedDescriptionText = "Welcome to "+text;

        Assert.assertEquals(actualDescriptionText,expectedDescriptionText,"Description text does not match");


    }




}
