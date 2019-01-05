package de.adesso.cccinw4.primary.configuration;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WorkerFeignConfiguration {

    @Value("${worker.user}")
    private String workerUser;
    @Value("${worker.password}")
    private String workerPassword;

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor(workerUser, workerPassword);
    }

}
