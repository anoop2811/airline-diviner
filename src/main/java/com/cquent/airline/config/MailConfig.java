package com.cquent.airline.config;

import org.apache.http.HttpHost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.sendgrid.SendGridProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.sendgrid.SendGrid;

@Configuration
@Profile({"prod", "cloud"})
public class MailConfig {
    @Autowired
    private SendGridProperties properties;

    @Bean
    public SendGrid sendGrid() {
        SendGrid sendGrid = new SendGrid(this.properties.getUsername(),
            this.properties.getPassword());
        if (this.properties.isProxyConfigured()) {
            HttpHost proxy = new HttpHost(this.properties.getProxy().getHost(),
                this.properties.getProxy().getPort());
            sendGrid.setClient(HttpClientBuilder.create().setProxy(proxy)
                .setUserAgent("sendgrid/" + sendGrid.getVersion() + ";java").build());
        }
        return sendGrid;
    }
}