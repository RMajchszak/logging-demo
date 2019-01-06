package de.adesso.cccinw4.support;

import org.apache.logging.log4j.CloseableThreadContext;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class LogInfoMessageBuilder {

    public static Builder builder( Logger logger ) {
        return new Builder(logger);
    }

    public static class Builder {

        private final Logger logger;
        private final Map<String, String> attributeMap = new HashMap<>();
        private String message = "";
        private Object[] messageParams;

        private Builder(Logger logger) {
            this.logger = logger;
        }

        public Builder message(String msg) {
            message = msg;
            messageParams = null;
            return this;
        }

        public Builder message(String msg, Object... params) {
            message = msg;
            messageParams = params;
            return this;
        }

        public Builder add(String key, String value) {
            attributeMap.put(key, value);
            return this;
        }

        public Builder id(String idStr) {
            attributeMap.put("event_id", idStr);
            return this;
        }

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
