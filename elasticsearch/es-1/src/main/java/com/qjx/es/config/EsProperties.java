package com.qjx.es.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author qinjiaxing on 2023/1/9
 */
@Data
@ConfigurationProperties(prefix = "elasticsearch")
public class EsProperties {
    private String host;
    private Integer port;

}
