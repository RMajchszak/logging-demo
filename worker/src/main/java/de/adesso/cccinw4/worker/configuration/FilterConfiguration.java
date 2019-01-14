package de.adesso.cccinw4.worker.configuration;

import de.adesso.cccinw4.support.filter.DiagnosticLoggingFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Konfiguriert einen DiagnosticLoggingFilter
 */
//@Configuration
public class FilterConfiguration {

    /**
     * Erzeugt einen DiagnosticLoggingFilter
     */
  //  @Bean
    public DiagnosticLoggingFilter createLogFilter() {
        return new DiagnosticLoggingFilter();
    }
}
