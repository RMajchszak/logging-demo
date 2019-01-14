package de.adesso.cccinw4.primary;

import de.adesso.cccinw4.primary.client.WorkerClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * REST-Controller des Primary Microservice
 */
@RestController
@RequestMapping(value = "/api", produces = "application/json")
public class PrimaryController {

    private static final Logger LOG = LogManager.getLogger(PrimaryController.class);

    private final WorkerClient workerClient;

    /**
     * Spring DI-Konstruktor
     * @param workerClient Feign Client für den Zugriff auf den worker
     */
    @Autowired
    public PrimaryController(WorkerClient workerClient) {
        this.workerClient = workerClient;
    }


    /**
     * Einstiegspunkt für den Primary Microservice
     * @param key Ein Key
     * @param principal Spring Security Principal
     * @return Ein Ergebnisstring
     */
    @GetMapping(value = "/primary/{key}")
    public ResponseEntity<String> compute(@PathVariable("key") String key, Principal principal) {
            LOG.info("Eingehender Request mit {} als Key", key);
            String workerResult = workerClient.doWork(key);
            String result = "Der Worker meldet: " + workerResult;
            return ResponseEntity.ok(result);
    }
}
