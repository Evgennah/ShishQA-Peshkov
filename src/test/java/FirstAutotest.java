import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class FirstAutotest {
    public static WebDriver driver;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://sandbox.cardpay.com/MI/cardpayment2.html?orderXml=PE9SREVSIFdBTExFVF9JRD0nODI5OScgT1JERVJfTlVNQkVSPSc0NTgyMTEnIEFNT1VOVD0nMjkxLjg2JyBDVVJSRU5DWT0nRVVSJyAgRU1BSUw9J2N1c3RvbWVyQGV4YW1wbGUuY29tJz4KPEFERFJFU1MgQ09VTlRSWT0nVVNBJyBTVEFURT0nTlknIFpJUD0nMTAwMDEnIENJVFk9J05ZJyBTVFJFRVQ9JzY3NyBTVFJFRVQnIFBIT05FPSc4NzY5OTA5MCcgVFlQRT0nQklMTElORycvPgo8L09SREVSPg==&sha512=998150a2b27484b776a1628bfe7505a9cb430f276dfa35b14315c1c8f03381a90490f6608f0dcff789273e05926cd782e1bb941418a9673f43c47595aa7b8b0d");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void openPage() {
        String titleActual = driver.getTitle();
        String titleExpected = "Unlimint Payment Page";
        Assert.assertEquals("Title not matching", titleExpected, titleActual);
    }

    @Test
    public void firstPayment() {
        WebElement cardNumber = driver.findElement(By.id("input-card-number"));
        cardNumber.sendKeys("4000000000000002");
        WebElement cardHolder = driver.findElement(By.id("input-card-holder"));
        cardHolder.sendKeys("CI VON");
        WebElement expiresMonth = driver.findElement(By.id("card-expires-month"));
        expiresMonth.sendKeys("01");
        WebElement selectYear = driver.findElement(By.id("card-expires-year"));
        Select selectY = new Select(selectYear);
        selectY.selectByVisibleText("2038");
        WebElement cvs = driver.findElement(By.id("input-card-cvc"));
        cvs.sendKeys("123");
        WebElement payButton = driver.findElement(By.id("action-submit"));
        payButton.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement successButton = driver.findElement(By.id("success"));
        successButton.submit();
        /* что сличаем на финальной странице [@id="payment-status-title"]/span 1.success
        [@id="payment-item-status"]/div[2] 2.confirmed - payment status
        3.order number
        4.currency + amount */

    }

}

