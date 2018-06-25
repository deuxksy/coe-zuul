package com.sds.act.coe.zuul;

import com.sds.act.coe.zuul.filter.SimpleFilter;
import com.sds.act.coe.zuul.locator.CoeRouterLocator;
import com.sds.act.coe.zuul.provider.ZuulFallbackProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@EnableZuulProxy
@EnableHystrix
@SpringBootApplication
public class CoeZuulApplication {

    @Autowired
    ZuulProperties zuulProperties;

    @Autowired
    ServerProperties serverProperties;

    public static void main(String[] args) {
        SpringApplication.run(CoeZuulApplication.class, args);
    }

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
}
