import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WelcomePageTest {

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

    //Navigate to the URL https://qameetup.ccbp.tech/
    //Verify if the App logo is displayed - use Assertions,
    //If the App logo is not displayed, print "App logo is not displayed"
    //Verify the Heading text of the page - use Assertions
    //Expected text: Welcome to Meetup
    //If the Heading text does not match the expected text, print "Heading text does not match"
    //Verify the Description text of the page - use Assertions
    //Expected text: Please register for the topic
    //If the Description text does not match the expected text, print "Description text does not match"
    //Verify if the Meetup image is displayed - use Assertions,
    //If the Meetup image is not displayed, print "Meetup image is not displayed"
    //Close the browser window.

    @Test(priority = 1)
    public void UIOfTheWelcomePage(){
        WebElement LogoEle = driver.findElement(By.cssSelector("img[class*='Website']"));
        Assert.assertTrue(LogoEle.isDisplayed(),"App logo is not displayed");
        WebElement HeadingEle = driver.findElement(By.cssSelector("h1[class*='Heading']"));
        String actualHeading = HeadingEle.getText();
        String expectedHeading = "Welcome to Meetup";
        Assert.assertEquals(actualHeading,expectedHeading,"Heading text does not match");
        WebElement DescriptionEle = driver.findElement(By.cssSelector("p[class*='Description']"));
        String actualDescription = DescriptionEle.getText();
        String expectedDescription = "Please register for the topic";
        Assert.assertEquals(actualDescription,expectedDescription,"Description text does not match");
        WebElement MeetImageEle = driver.findElement(By.cssSelector("img[class*='MeetUpImage']"));
        Assert.assertTrue(MeetImageEle.isDisplayed(),"Meetup image is not displayed");

    }


    //Test the Functionality of the Welcome Page
    //Navigate to the URL https://qameetup.ccbp.tech/
    //Click the "Register" button.
    //Verify the navigation to the registration page - use Assertions,
    //Expected URL: https://qameetup.ccbp.tech/register
    //If the current URL does not match the expected URL, print "URLs do not match"
    //Close the browser window.

    @Test(priority = 2)
    public void FunctionalityOfTheWelcomePage(){
        WebElement RegisterButton = driver.findElement(By.id("registerButton"));
        RegisterButton.click();
        String actualRegisterUrl = driver.getCurrentUrl();
        String expectedRegisterUrl = "https://qameetup.ccbp.tech/register";
        Assert.assertEquals(actualRegisterUrl,expectedRegisterUrl,"URLs do not match");

    }
}
