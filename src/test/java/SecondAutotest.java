import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class SecondAutotest {
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
    public void firstPayment() throws IOException {
        String currency = driver.findElement(By.id("currency")).getText();
        String totalAmount = driver.findElement(By.id("total-amount")).getText();
        String orderNumber = driver.findElement(By.id("order-number")).getText();
        WebElement cardNumber = driver.findElement(By.id("input-card-number"));
        cardNumber.sendKeys("4000000000000051");
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
        String paymentStatusActual = driver.findElement(By.id("payment-item-status")).getText();
        String paymentStatusExpected = "Payment status CONFIRMED";
        Assert.assertEquals("Statuses do not match", paymentStatusExpected, paymentStatusActual);
        String orderNumberActual = driver.findElement(By.id("payment-item-ordernumber")).getText();
        String orderNumberExpected = "Order number " + orderNumber;
        Assert.assertEquals("Statuses do not match", orderNumberExpected, orderNumberActual);
        String CurrencyActual = driver.findElement(By.xpath("//div[@id='payment-item-total']")).getText();
        String CurrencyExpected = "Total amount " + currency + "   " + totalAmount;
        Assert.assertEquals("Statuses do not match", CurrencyExpected, CurrencyActual);
        String totalAmountActual = driver.findElement(By.id("payment-item-total-amount")).getText();
        Assert.assertEquals("Statuses do not match", totalAmount, totalAmountActual);


        driver.quit();
    }

}