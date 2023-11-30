import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class OnAirEntTest {
    private WebDriver driver;

    @Test
    void checkSearchFunctionality() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\vocal\\Documents\\Selenium jars and drivers\\drivers\\edge\\msedgedriver.exe");
        driver = new EdgeDriver();
        driver.manage().window().maximize();

        driver.get("https://www.newegg.com/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement searchInput = driver.findElement(By.xpath("//input [@title='Search Site']"));
        searchInput.sendKeys("iphone 13");
        searchInput.sendKeys(Keys.RETURN);

        WebElement viewDetailsBtn = driver.findElement(By.xpath("//div[@id='75-113-812']/div[2]/div[2]/div[1]/button"));
        viewDetailsBtn.click();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement searchResults = driver.findElement(By.xpath("//h1[@class='product-title']"));
        String actualResults = searchResults.getText().toLowerCase();
        Assert.assertTrue(actualResults.contains("iphone 13"), "Search results do not match the search word. Actual results: " + actualResults);

        driver.quit();
    }

    @Test
    void checkAddToCartFunctionality() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\vocal\\Documents\\Selenium jars and drivers\\drivers\\edge\\msedgedriver.exe");
        driver = new EdgeDriver();
        driver.manage().window().maximize();

        driver.get("https://www.newegg.com/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement searchInput = driver.findElement(By.xpath("//input [@title='Search Site']"));
        searchInput.sendKeys("iphone 13");
        searchInput.sendKeys(Keys.RETURN);

        WebElement viewDetailsBtn = driver.findElement(By.xpath("////div[@id='75-113-812']/div[2]/div[2]/div[1]/button"));
        viewDetailsBtn.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement addToCartBtn = driver.findElement(By.xpath("//div[@class='nav-col']/button"));
        addToCartBtn.click();
        WebElement noThanksBtn = driver.findElement(By.xpath("//button[contains(text(),'No, thanks')]"));
        noThanksBtn.click();


        WebElement messageTitle = driver.findElement(By.xpath("//div[@class='message-title']"));
        String actualResults = messageTitle.getText().toLowerCase();
        Assert.assertTrue(actualResults.contains("item has been added to cart."), "Search results do not match the search word. Actual results: " + actualResults);
        WebElement viewCartBtn = driver.findElement(By.xpath("//button[contains(text(),'View Cart')]"));
        viewCartBtn.click();

        WebElement sumInCart = driver.findElement(By.xpath("//li[@class='summary-content-total']/span/strong"));
        String sumInCartText = sumInCart.getText();
        double sumInCartDouble = Double.parseDouble(sumInCartText);

        Assert.assertTrue(sumInCartDouble > 0, "Test Failed: Cart total is not greater than 0");
        System.out.println("Test Passed: Cart total is greater than 0");

        driver.quit();
    }
}
