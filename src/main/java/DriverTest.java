import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DriverTest {

    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();

        driver.get("https://www.litres.ru/dzhon-keho/podsoznanie-mozhet-vse/citaty/");

        List<String> quotesPotok;
        quotesPotok = returnQuotes(driver);

        for (String singleQuote : quotesPotok) {
            System.out.println(singleQuote);
            System.out.println();
        }

        driver.quit();


    }


    public static List<String> returnQuotes(WebDriver driver) throws InterruptedException {
        String l_moreQuotesButton = "//a[@id='quotes__more-button']";
        String quote = "//*[@class='quote__text']/p";
        List<String> quotes = new ArrayList<String>();
        WebElement moreQuotesButton = driver.findElement(By.xpath(l_moreQuotesButton));
        for (int i = 0; i < 20; i++) {
            if (moreQuotesButton.isDisplayed() && moreQuotesButton.isEnabled()) {
                moreQuotesButton.click();
                Thread.sleep(1000);
            }
        }

        List<WebElement> quotesElements = driver.findElements(By.xpath(quote));
        for (WebElement item : quotesElements){
            quotes.add(item.getText());

        }
        return quotes;

    }


}
