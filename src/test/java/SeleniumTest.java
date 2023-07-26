import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;

public class SeleniumTest {


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

        driver.quit();
    }


    //test without login. // text >>> Sorry you not logged in
    @Test
    public void testUnauthenticatedUser(){
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:3000/calc");

        WebElement calculator = driver.findElement(By.className("calculator"));
        System.out.println(calculator.getText());
        assertEquals(calculator.getText(),"Sorry you not logged in");

//        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
//
//        WebElement numbtn7 = driver.findElement(By.id("numbtn0"));
//        WebElement numbtn2 = driver.findElement(By.id("numbtn7"));
//        WebElement numbtn8 = driver.findElement(By.id("numbtn1"));
//
//        numbtn7.click();
//        numbtn2.click();
//        numbtn8.click();
//
//        WebElement equalBtn = driver.findElement(By.id("opbtn4"));
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
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        //set username value input
        usernameInput.sendKeys("ankita");

        //check input username with expected username
       assertEquals("ankita",usernameInput.getAttribute("value"));

        WebElement passwordInput = driver.findElement(By.name("password"));

        //set password input
        passwordInput.sendKeys("ankita123");

        //check input username with expected username
        assertEquals("ankita123", passwordInput.getAttribute("value"));

        WebElement submitButton = driver.findElement(By.className("loginbutton"));
        submitButton.click();

//        driver.navigate().to("http://localhost:3000/calc");
//

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        driver.findElement(By.xpath("//a[@href='/calc']")).click();
        driver.get("http://localhost:3000/calc");
        System.out.println( driver.getCurrentUrl());

        //click operation btn and num btns
        driver.findElement(By.id("numbtn0")).click();
        driver.findElement(By.id("numbtn7")).click();
        driver.findElement(By.id("opbtn0")).click();
        driver.findElement(By.id("numbtn1")).click();
        driver.findElement(By.id("opbtn4")).click();

        //locate input boxes
        WebElement display1 = driver.findElement(By.xpath("//*[@id=\"display1\"]"));
        WebElement display2 = driver.findElement(By.xpath("//*[@id=\"display2\"]"));
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
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        //set username value input
        usernameInput.sendKeys("admin");

        //check input username with expected username
        assertEquals("admin",usernameInput.getAttribute("value"));

        WebElement passwordInput = driver.findElement(By.name("password"));

        //set password input
        passwordInput.sendKeys("admin");

        //check input username with expected username
        assertEquals("admin", passwordInput.getAttribute("value"));

        WebElement submitButton = driver.findElement(By.className("loginbutton"));
        submitButton.click();

        driver.findElement(By.xpath("//a[@href='/calc']")).click();
        driver.get("http://localhost:3000/calc");

        // perform click on number btns

        driver.findElement(By.id("numbtn0")).click();
        driver.findElement(By.id("numbtn7")).click();
        driver.findElement(By.id("opbtn0")).click();
        driver.findElement(By.id("numbtn1")).click();
        driver.findElement(By.id("opbtn4")).click();

        //locate input boxes
        WebElement display1 = driver.findElement(By.xpath("//input[@id='display1']"));
//        assertEquals("72+8",display1.getAttribute("value"));

        WebElement display2 = driver.findElement(By.xpath("//input[@id='display2']"));
       String val = display2.getAttribute("value");

        System.out.println("Output of calculation >>>>>"+val);
        assertEquals("=80",val);
        driver.quit();
    }
}







