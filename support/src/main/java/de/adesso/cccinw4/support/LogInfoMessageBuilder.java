package de.adesso.cccinw4.support;

import org.apache.logging.log4j.CloseableThreadContext;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Erlaubt über ein Fluent Interface strukturiertes Loggen
 */
public class LogInfoMessageBuilder {

    /**
     * Gibt eine Builder-Instanz zurück
     * @param logger Auf diesen Logger wird später eine Logmeldung ausgegeben
     * @return Builder
     */
    public static Builder builder( Logger logger ) {
        return new Builder(logger);
    }

    private LogInfoMessageBuilder() {
        // Verdeckt den Default-Konstruktor
    }


    /**
     * Hilfsklasse für Fluent-Interface
     */
    public static class Builder {

        private final Logger logger;
        private final Map<String, String> attributeMap = new HashMap<>();
        private String message = "";
        private Object[] messageParams;

        private Builder(Logger logger) {
            this.logger = logger;
        }

        /**
         * Die eigentliche Log-Meldung
         * @param msg Log-Meldung
         * @return this
         */
        public Builder message(String msg) {
            message = msg;
            messageParams = null;
            return this;
        }

        /**
         * Ereugt eine Log-Meldung mit Parametern
         * @param msg Die Logmeldung mit Platzhaltern "{}"
         * @param params Parameter, die die Plazuhalter ersetzen
         * @return this
         */
        public Builder message(String msg, Object... params) {
            message = msg;
            messageParams = params;
            return this;
        }

        /**
         * Fügt der Logmeldung ein Key-Value-Paar hinzu
         * @param key Key
         * @param value Wert
         * @return this
         */
        public Builder add(String key, String value) {
            attributeMap.put(key, value);
            return this;
        }

        /**
         * Fügt die event-id zur Logmeldung hinzu
         * @param idStr Event-Id
         * @return this
         */
        public Builder id(String idStr) {
            attributeMap.put("event_id", idStr);
            return this;
        }

        /**
         * Loggt die Logmeldung auf dem Info-Level
         */
        public void log() {
            try (CloseableThreadContext.Instance ctx = CloseableThreadContext.putAll(attributeMap)) {
                if (messageParams != null) {
                    logger.info(message, messageParams);
                } else {
                    logger.info(message);
                }
            }
        }
    }

}
