package com.company.sneakerinvetory.Stockx;




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

public class EbayReader {

    private String AveragePrice;
    private Sneaker sneaker;
    private JsonObject jsonObject;
    String BASE_SEARCH_URL = "https://svcs.ebay.com/services/search/FindingService/v1";
    public EbayReader(Sneaker sneaker){
        this.sneaker = sneaker;
    }

    // tradditional selling api

    public String getGetAveragePrice() throws IOException {
        jsonObject = Json.createObjectBuilder()
                .add("jsonns.xsi", "https://www.w3.org/2001/XMLSchema-instance")
                .add("jsonns.xs", "https://www.w3.org/2001/XMLSchema")
                .add("jsonns.tns","https://www.ebay.com/marketplace/search/v1/services")
                .add("findItemsByKeywordsRequest", Json.createObjectBuilder()
                        .add("keywords", sneaker.getName() + " " + sneaker.getSku()))
                .build();
        String request = jsonObject.toString();
        URL url = new URL("https://svcs.ebay.com/services/search/FindingService/v1");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        //connection.setRequestProperty("Content-Type", "application/json; utf-8");
        connection.setDoOutput(true);
        try{
            OutputStream outputStream = connection.getOutputStream();
            byte[] input = request.getBytes("utf-8");
            outputStream.write(input, 0, input.length);
        }catch (Exception e){
            e.printStackTrace();
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder builder = new StringBuilder();
        String response = "";
        while ((response = br.readLine()) != null){
            builder.append(response);
        }

        return builder.toString();

    }


}
