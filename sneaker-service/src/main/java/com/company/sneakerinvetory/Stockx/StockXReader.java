package com.company.sneakerinvetory.Stockx;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public class StockXReader {
    private static final String BASE_URL = "https://www.stockx.com/";
    private String SEARCH = BASE_URL + "search/search?s=";
    private final Sneaker sneaker;

    public StockXReader(Sneaker sneaker){
        this.sneaker = sneaker;
    }

    public String getAveragePrice() throws IOException, InterruptedException {

        System.setProperty("webdriver.chrome.driver", "src/main/java/com/company/sneakerinvetory/chromeDriver/chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        WebDriverWait waitDriver = new WebDriverWait(driver, 20);
        driver.navigate().to(SEARCH += sneaker.getSku());
        // wait for first sku link to be visible
        waitDriver.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div[2]/div/div/div[2]/div[2]/div[2]/div[2]/div[1]/div/a"))).click();
        // handle link doesn't exist

        waitDriver.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"menu-button-56\"]"))).click();
        waitDriver.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"menu-list-56\"]/div/div[2]/ul")));

        List<WebElement> available_sizes = driver.findElements(By.xpath("//*[@id=\"menu-list-56\"]/div/div[2]/ul/li"));
        boolean isAvailableSize = false;
        WebElement chosen_size = null;
        for (WebElement size : available_sizes) {
            WebElement size_ele = size.findElement(By.className("title"));
            String formatted_size = size_ele.getText().trim();
            if (formatted_size.equals(sneaker.getSize())) {
                isAvailableSize = true;
                chosen_size = size;
            }
        }
        if (isAvailableSize) {
            chosen_size.click();
            driver.findElementByXPath("//*[@id=\"menu-button-56\"]").click();

            WebElement size_price = driver.findElementByXPath("//*[@id=\"market-summary\"]/div[2]/div/div[1]/div[1]");
            String price = size_price.getText();
            price = price.trim();
            price = price.replace("$", "");
            return price;

        }
        return "not available";
    }

}
