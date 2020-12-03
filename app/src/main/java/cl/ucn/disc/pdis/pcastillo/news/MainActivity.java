/*
 * Copyright 2020 <Pablo Castillo, pablo.castillo01@alumnos.ucn.cl>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NON INFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package cl.ucn.disc.pdis.pcastillo.news;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import cl.ucn.disc.pdis.pcastillo.news.model.News;
import cl.ucn.disc.pdis.pcastillo.news.services.ContracsImplNewsApi;
import cl.ucn.disc.pdis.pcastillo.news.services.Contracts;

/**
 * The main class
 * @author Pablo Castillo Rojas
 */
public final class MainActivity extends AppCompatActivity {

    /**
     * The Logger
     */
    private static final Logger log = LoggerFactory.getLogger(MainActivity.class);

    /**
     * ListView
     */
    protected ListView listView;

    /**
     * OnCreate
     *
     * @param savedInstanceState used to reload the app
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        log.debug("onCreate ..");
        setContentView(R.layout.activity_main);

        // Get ListView from layout
        this.listView = findViewById(R.id.am_lv_news);

        // Click one item
        this.listView.setOnItemClickListener((parent, view, position, id) -> {
            log.debug("Position: {}, Id: {}.", position, id);
        });

        // Running in background thread
        AsyncTask.execute(()->{

            // Using the contracts to get the news ..
            Contracts contracts = new ContracsImplNewsApi("...");

            // Get the News from NewsApi (internet)
            List<News> news = contracts.retrieveNews(20);

            // Build the simple adapter to populate the list
            ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, news);

            // ..update the listview in UiThread
            runOnUiThread(() -> {
                this.listView.setAdapter(adapter);
            });
        });
    }
}