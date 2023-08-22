import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class SeleniumTest {

//    static WebDriver driver;

//   @Test
//    public void setup() throws MalformedURLException {
//        String nodeURL = "http://192.168.1.6:4444/";
//
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setBrowserName("chrome");
//        capabilities.setPlatform(Platform.WIN11);
//
//        driver = new RemoteWebDriver(new URL(nodeURL),capabilities);
//    }


    @Test
    public void testRegister(){
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:3000/signin");

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        WebElement emailInput = driver.findElement(By.name("email"));
        emailInput.sendKeys("akritigmail.com");

        WebElement usernameInput = driver.findElement(By.name("username"));
        usernameInput.sendKeys("akriti");

        WebElement selectRole = driver.findElement(By.id("2"));
        selectRole.click();

        WebElement passwordInput = driver.findElement(By.name("pasword"));
        passwordInput.sendKeys("akriti123");


        WebElement submitButton = driver.findElement(By.className("registerbtn"));
        submitButton.click();

        driver.quit();
    }

    @Test
    public void testLogin(){
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:3000/login");

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        //locate username input box element by name
        WebElement usernameInput = driver.findElement(By.name("username"));

        //set username value input
        usernameInput.sendKeys("akriti!");

        //check input username with expected username
        assertEquals("akriti!",usernameInput.getAttribute("value"));

        WebElement passwordInput = driver.findElement(By.name("password"));

        //set password input
        passwordInput.sendKeys("akriti1!");

        //check input username with expected username
        assertEquals("akriti1!", passwordInput.getAttribute("value"));

        WebElement submitButton = driver.findElement(By.className("loginbutton"));
        submitButton.click();

        WebElement container = driver.findElement(By.className("calculatorContainer"));
        assertEquals(container.getText(),"Sorry you not logged in");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        driver.quit();

    }

    //test without login. // text >>> Sorry you not logged in
    @Test
    public void testUnauthenticatedUser(){
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:3000/calc");

        WebElement calculator = driver.findElement(By.className("calculatorContainer"));
        System.out.println(calculator.getText());
        assertEquals(calculator.getText(),"Sorry you not logged in");

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
//
//        WebElement numbtn7 = driver.findElement(By.id("numbtn0calc1"));
//        WebElement numbtn2 = driver.findElement(By.id("numbtn7calc1"));
//        WebElement numbtn8 = driver.findElement(By.id("numbtn1calc1"));
//
//        numbtn7.click();
//        numbtn2.click();
//        numbtn8.click();
//
//        WebElement equalBtn = driver.findElement(By.id("opbtn4calc1"));
//        equalBtn.click();
        driver.quit();
    }


   // check calculator access for role user >>>
    @Test public void calculatorForRoleUser() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);

        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:3000/login");

        WebElement usernameInput = driver.findElement(By.name("username"));

//        set username value input
        usernameInput.sendKeys("ankita");

        //check input username with expected username
       assertEquals("ankita",usernameInput.getAttribute("value"));

        WebElement passwordInput = driver.findElement(By.name("password"));

//        set password input
        passwordInput.sendKeys("ankita123");

//        check input username with expected username
        assertEquals("ankita123", passwordInput.getAttribute("value"));

         driver.findElement(By.className("loginbutton")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));

        //click operation btn and num btns
        driver.findElement(By.id("numbtn0calc1")).click();
        driver.findElement(By.id("numbtn7calc1")).click();
        driver.findElement(By.id("opbtn0calc1")).click();
        driver.findElement(By.id("numbtn1calc1")).click();
        driver.findElement(By.id("opbtn4calc1")).click();

        //locate input boxes
        WebElement display1 = driver.findElement(By.xpath("//*[@id=\"inputbox1calc1\"]"));
        WebElement display2 = driver.findElement(By.xpath("//*[@id=\"inputbox2calc1\"]"));
        System.out.println("Expresstion Input string >>>>> "+display1.getAttribute("value"));

        // check the input string of number inputs
        assertEquals("72+8",display1.getAttribute("value"));
        System.out.println("Output of calculation >>>>> "+display2.getAttribute("value"));
        assertEquals("you are not authorised",display2.getAttribute("value"));

           driver.quit();
    }

    @Test public void calculatorForRoleAdmin() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);

        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:3000/login");

        WebElement usernameInput = driver.findElement(By.name("username"));

//        set username value input
        usernameInput.sendKeys("admin");

        //check input username with expected username
        assertEquals("admin",usernameInput.getAttribute("value"));

        WebElement passwordInput = driver.findElement(By.name("password"));

//        set password input
        passwordInput.sendKeys("admin");

//        check input username with expected username
        assertEquals("admin", passwordInput.getAttribute("value"));

        driver.findElement(By.className("loginbutton")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));


        //click operation btn and num btns
        driver.findElement(By.id("numbtn0calc1")).click();
        driver.findElement(By.id("numbtn7calc1")).click();
        driver.findElement(By.id("opbtn0calc1")).click();
        driver.findElement(By.id("numbtn1calc1")).click();

        //locate input boxes
        WebElement display1 = driver.findElement(By.id("inputbox1calc1"));
        assertEquals("72+8",display1.getAttribute("value"));



        driver.findElement(By.id("opbtn4calc1")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));

        // check the input string of number inputs
        WebElement display2 = driver.findElement(By.id("inputbox2calc1"));
        assertEquals("=80",display2.getAttribute("value"));
        driver.quit();
    }
}







