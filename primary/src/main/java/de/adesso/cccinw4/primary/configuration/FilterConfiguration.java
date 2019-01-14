package de.adesso.cccinw4.primary.configuration;

import de.adesso.cccinw4.support.filter.DiagnosticLoggingFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Konfiguriert einen DiagnosticLogingFilter
 */
// @Configuration
public class FilterConfiguration {

    /**
     * Erzeugt einen DiagnosticLogingFilter
     */
   // @Bean
    public DiagnosticLoggingFilter createLogFilter() {
        return new DiagnosticLoggingFilter();
    }
}
