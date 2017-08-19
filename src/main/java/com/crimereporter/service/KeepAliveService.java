package com.crimereporter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class KeepAliveService
{
    private static Logger logger = LoggerFactory.getLogger(KeepAliveService.class);

    @Value(value = "${heroku.app.url}")
    private String url;

    @Value(value = "${heroku.app.maintenance}")
    private Boolean herokuEnabled;

    @Scheduled(cron = "0 */20 * * * *")
    public void ping() throws URISyntaxException
    {
        if (!herokuEnabled) {
            return;
        }

        URI uri = new URI(url);
        RestTemplate template = new RestTemplate();
        template.getForEntity(uri, String.class);
        logger.info(String.format("Keep alive url: %s", uri));
    }
}
