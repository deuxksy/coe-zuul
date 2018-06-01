package com.example.coezuul;

import com.example.coezuul.filter.SimpleFilter;
import com.example.coezuul.locator.CoeRouterLocator;
import com.example.coezuul.provider.ZuulFallbackProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@EnableZuulProxy
@SpringBootApplication
public class CoeZuulApplication {

    @Autowired
    ZuulProperties zuulProperties;

    @Autowired
    ServerProperties serverProperties;

    @Bean
    public FallbackProvider zuulFallbackProvider() {
        return new ZuulFallbackProvider();
    }

    @Bean
    public SimpleFilter simpleFilter() {
        return new SimpleFilter();
    }

    @Bean
    public CoeRouterLocator coeRouterLocator() {
        return new CoeRouterLocator(serverProperties.getServlet().getServletPrefix(), zuulProperties);
    }

    public static void main(String[] args) {
        SpringApplication.run(CoeZuulApplication.class, args);
    }
}
