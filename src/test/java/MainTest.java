import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class MainTest {

    @BeforeMethod
    public void setUp() {

    }

    @Test
    public void testLoginError() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\dmitr\\Downloads\\chromedriver_win32\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        try {
            driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);//ищи каждый элемент не более 10 сек
            driver.manage().window().maximize();

            driver.get("https://www.browserstack.com/users/sign_in");

            WebElement username = driver.findElement(By.id("user_email_login"));
            WebElement password = driver.findElement(By.id("user_password"));
            WebElement login = driver.findElement(By.name("commit"));

            username.sendKeys("abc@gmail.com");
            password.sendKeys("your_password");
            login.click();

            WebElement error = driver.findElement(By.id("bs-alert-text-id"));
            Assert.assertEquals(error.getText(), "There have been several failed attempts to sign in from this account. Please wait a while and try again later.");
        } finally {
            driver.quit();
        }
    }

    @Test
    public void testForgotPasswordPage() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\dmitr\\Downloads\\chromedriver_win32\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        try {
            driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);//ищи каждый элемент не более 10 сек
            driver.manage().window().maximize();

            driver.get("https://www.browserstack.com/users/sign_in");

            WebElement link = driver.findElement(By.linkText("Forgot password?"));
            link.click();

            Assert.assertEquals(driver.getCurrentUrl(), "https://www.browserstack.com/users/password/new");
        } finally {
            driver.quit();
        }
    }
}
