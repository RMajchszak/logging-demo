package de.adesso.cccinw4.worker.configuration;

import de.adesso.cccinw4.support.filter.DiagnosticLoggingFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {

    @Bean
    public DiagnosticLoggingFilter createLogFilter() {
        return new DiagnosticLoggingFilter();
    }
}
