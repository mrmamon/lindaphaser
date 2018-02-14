package com.example.a006420.lindaphaser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try  {
                    Document doc = null;
                    try {
                        URL url = new URL("http://www.lindaexchange.com/EN/exchange");
                        doc = Jsoup.parse(url, 3000);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Elements elements = doc.select("table");
                    Elements elementbody = elements.get(0).select("tbody");
                    Elements rows = elementbody.get(0).select("tr");

                    for (Element row : rows) {

                        if (row.select("td").size() == 4) {
                            String country = row.select("td").get(0).text();
                            String currency = row.select("td").get(1).text();
                            String buy = row.select("td").get(2).text();
                            String sell = row.select("td").get(3).text();
                            Log.d("row",country+currency+buy+":"+sell);
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();


    }
}
