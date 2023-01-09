package com.qjx.es.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author qinjiaxing on 2023/1/9
 */
@Configuration
@EnableConfigurationProperties(EsProperties.class)
public class ESConfig {

    @Bean
    public RestHighLevelClient restHighLevelClient(EsProperties esProperties) {
        return new RestHighLevelClient(
                RestClient.builder(new HttpHost(esProperties.getHost(), esProperties.getPort(), "http")));
    }


}
