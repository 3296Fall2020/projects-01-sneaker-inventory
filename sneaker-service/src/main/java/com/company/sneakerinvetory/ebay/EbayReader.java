package com.company.sneakerinvetory.ebay;




import com.gargoylesoftware.htmlunit.IncorrectnessListener;
import com.gargoylesoftware.htmlunit.ScriptException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.gargoylesoftware.htmlunit.javascript.JavaScriptErrorListener;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
// https://stackoverflow.com/questions/16375365/how-to-get-text-between-a-specific-span-with-htmlunit for xpath and how to DOM element like span

public class EbayReader {
    protected final Log logger = LogFactory.getLog(getClass());
    WebClient client;
    HtmlPage page;
    String BASE_URL = "https://www.ebay.com";
    private SneakerForm sneakerForm;

    public EbayReader(SneakerForm sneakerForm) {
        this.sneakerForm = sneakerForm;
    }

    public class DummyJavascriptErrorListener implements JavaScriptErrorListener {

        @Override
        public void timeoutError(HtmlPage arg0, long arg1, long arg2) {
            // TODO Auto-generated method stub
        }

        @Override
        public void scriptException(HtmlPage arg0, ScriptException arg1) {
            // TODO Auto-generated method stub
        }

        @Override
        public void malformedScriptURL(HtmlPage arg0, String arg1, MalformedURLException arg2) {
            // TODO Auto-generated method stub
        }

        @Override
        public void loadScriptError(HtmlPage arg0, URL arg1, Exception arg2) {
            // TODO Auto-generated method stub

        }
    }

    public class DummyIncorrectnessListener implements IncorrectnessListener {

        @Override
        public void notify(String s, Object o) {
            // nothing to do ...
        }
    }

    public String getAveragePrice() {

        try {
            client = new WebClient();
            client.getOptions().setJavaScriptEnabled(false);
            client.getOptions().setCssEnabled(false);
            client.getOptions().setThrowExceptionOnScriptError(false);
            client.getOptions().setUseInsecureSSL(true);
            client.setJavaScriptErrorListener(new DummyJavascriptErrorListener());
            client.setIncorrectnessListener(new DummyIncorrectnessListener());
            // client.waitForBackgroundJavaScriptStartingBefore(1000);
            // client.waitForBackgroundJavaScript(1000);
            page = client.getPage(BASE_URL);


            HtmlInput searchBar = page.getFirstByXPath("//*[@id=\"gh-ac\"]");
            searchBar.setValueAttribute(sneakerForm.getSku() + " " + sneakerForm.getSize()); // try search with sie
            HtmlForm form = searchBar.getEnclosingForm();

            page = client.getPage(form.getWebRequest(null));

            final List<DomElement> spans = page.getElementsByTagName("span");
            String lowest_price = "NaN";

            HtmlAnchor buyNowButton = page.getFirstByXPath("//*[@id=\"s0-14-11-5-1[0]\"]/div[2]/div/div/ul/li[4]/a");
            String buy_url = buyNowButton.getAttribute("href");

            page = client.getPage(buy_url);

            // get number of results based on search query.... should be greater than 0 if not then return "no results, bad name or sku"
            DomElement result_element = page.getFirstByXPath("//*[@id=\"mainContent\"]/div[1]/div/div[2]/div[1]/div[1]/h1/span[1]");
            int num_results = Integer.parseInt(result_element.getTextContent());

            if (num_results < 1) { // if no results... try search without size
                searchBar = page.getFirstByXPath("//*[@id=\"gh-ac\"]");
                searchBar.setValueAttribute(sneakerForm.getSku());
                form = searchBar.getEnclosingForm();

                page = client.getPage(form.getWebRequest(null));

                final List<DomElement> spans_again = page.getElementsByTagName("span");
                lowest_price = "NaN";

                buyNowButton = page.getFirstByXPath("//*[@id=\"s0-14-11-5-1[0]\"]/div[2]/div/div/ul/li[4]/a");
                buy_url = buyNowButton.getAttribute("href");

                page = client.getPage(buy_url);

                // get number of results based on search query.... should be greater than 0 if not then return "no results, bad name or sku"
                result_element = page.getFirstByXPath("//*[@id=\"mainContent\"]/div[1]/div/div[2]/div[1]/div[1]/h1/span[1]");
                num_results = Integer.parseInt(result_element.getTextContent());
            }
            if (num_results > 0) {
                //filter button.. expand to get options
                HtmlButton button = page.getFirstByXPath("//button[@class=\"fake-menu-button__button expand-btn expand-btn--small expand-btn--secondary\"]");
                button.click();

                List<HtmlAnchor> filterAnchors = page.getAnchors();
                String filter_url = "";
                for (HtmlAnchor filterAnchor : filterAnchors) {
                    String url = filterAnchor.getHrefAttribute();
                    if (url.contains("sop=15")) { // sop=15 href for lowest value first
                        filter_url = url;
                    }
                }
                page = client.getPage(filter_url); //go to new link sorted with lowest value first


                DomElement price_element = page.getFirstByXPath("//span[@class='s-item__price'][1]"); //find price of first element in table
                lowest_price = price_element.getTextContent();

                if (!lowest_price.contains("$")){
                    lowest_price =  "$" + lowest_price;
                }
                return lowest_price;
            } else {
                return "no results: bad name, unavailable size, or bad sku";
            }


        } catch (Exception e) {
            return "NaN";
        }

    }

}
