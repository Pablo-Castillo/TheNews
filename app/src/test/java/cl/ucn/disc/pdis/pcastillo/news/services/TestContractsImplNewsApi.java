/*
 * Copyright 2020 <Pablo Castillo, pablo.castillo01@alumnos.ucn.cl>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package cl.ucn.disc.pdis.pcastillo.news.services;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import cl.ucn.disc.pdis.pcastillo.news.model.News;

public final class TestContractsImplNewsApi {

    /**
     * The logger
     */
    private static final Logger log = LoggerFactory.getLogger(TestContractsImplNewsApi.class);

    /**
     * Test of retrieve News
     */
    @Test
    public void testRetrieveNews() {

        log.debug("Testing ..");

        // The Contracts
        String apiKey = "...";
        Contracts contracts = new ContracsImplNewsApi(apiKey);

        // The list of news
        int size = 20;
        List<News> news = contracts.retrieveNews(size);

        // Validations
        Assertions.assertNotNull(news, "List was null!!");
        Assertions.assertEquals(size, news.size(), "Wrong size");

        // Show the news
        for (News n :news) {

            log.debug("News: {}.", ToStringBuilder.reflectionToString(n, ToStringStyle.MULTI_LINE_STYLE));
        }

        log.debug(".. Done!!");
    }
}
