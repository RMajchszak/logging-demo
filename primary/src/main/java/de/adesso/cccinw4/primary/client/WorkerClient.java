package de.adesso.cccinw4.primary.client;

import de.adesso.cccinw4.primary.configuration.WorkerFeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Feign Client Interface für den Zugriff auf den Worker
 */
@FeignClient(value="workerClient", url = "${worker.url}", configuration = WorkerFeignConfiguration.class)
public interface WorkerClient {

    /**
     * Aufruf der Worker Methode
     * @param key Ein Key
     * @return Ein Ergebnis als String
     */
    @RequestMapping( method= RequestMethod.GET, value = "/worker/{key}")
    String doWork(@PathVariable("key") String key);
}
