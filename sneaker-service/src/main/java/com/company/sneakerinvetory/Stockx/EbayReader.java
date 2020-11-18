package com.company.sneakerinvetory.Stockx;




import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
// https://stackoverflow.com/questions/16375365/how-to-get-text-between-a-specific-span-with-htmlunit
public class EbayReader {

    WebClient client;
    HtmlPage page;
    String BASE_URL = "https://www.ebay.com";
    private Sneaker sneaker;

    public EbayReader(Sneaker sneaker){
        this.sneaker = sneaker;
    }
    public String getAveragePrice() throws IOException {
        client = new WebClient();
        client.getOptions().setJavaScriptEnabled(false);
        client.getOptions().setCssEnabled(false);
        client.getOptions().setThrowExceptionOnScriptError(false);
        client.waitForBackgroundJavaScriptStartingBefore(1000);
        client.waitForBackgroundJavaScript(1000);
        page = client.getPage(BASE_URL);

        HtmlInput searchBar = page.getFirstByXPath("//*[@id=\"gh-ac\"]");
        searchBar.setValueAttribute(sneaker.getSku() + " " + sneaker.getSize());
        HtmlForm form = searchBar.getEnclosingForm();

        page = client.getPage(form.getWebRequest(null));

        final List<DomElement> spans = page.getElementsByTagName("span");
        String lowest_price = "NaN";

        HtmlAnchor buyNowButton = page.getFirstByXPath("//*[@id=\"s0-14-11-5-1[0]\"]/div[2]/div/div/ul/li[4]/a");
        String buy_url = buyNowButton.getAttribute("href");

        page = client.getPage(buy_url);

        DomElement result_element = page.getFirstByXPath("//*[@id=\"mainContent\"]/div[1]/div/div[2]/div[1]/div[1]/h1/span[1]");
        int num_results = Integer.parseInt(result_element.getTextContent());

        HtmlButton button = page.getFirstByXPath("//button[@class=\"fake-menu-button__button expand-btn expand-btn--small expand-btn--secondary\"]");
        button.click();

        List<HtmlAnchor> filterAnchors = page.getAnchors();
        String filter_url= "";
        for (HtmlAnchor filterAnchor: filterAnchors){
            String url = filterAnchor.getHrefAttribute();
            if (url.contains("sop=15")){
                filter_url = url;
            }
        }
        page = client.getPage(filter_url);
        if (num_results > 0) {
            DomElement price_element = page.getFirstByXPath("//span[@class='s-item__price'][1]");
            lowest_price = price_element.getTextContent();
        }



        return lowest_price;



    }


}
