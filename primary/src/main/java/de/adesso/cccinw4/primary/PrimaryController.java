package de.adesso.cccinw4.primary;

import de.adesso.cccinw4.primary.client.WorkerClient;
import de.adesso.cccinw4.support.LogInfoMessageBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api", produces = "application/json")
public class PrimaryController {

    private static final Logger LOG = LogManager.getLogger(PrimaryController.class);

    private final WorkerClient workerClient;

    @Autowired
    public PrimaryController(WorkerClient workerClient) {
        this.workerClient = workerClient;
    }


    @GetMapping(value = "/primary/{key}")
    public ResponseEntity<String> compute(@PathVariable("key") String key )  {
        LOG.info("Eingehender Reqzest mit {} als Key", key);
        StopWatch watch = new StopWatch();
        watch.start();
        String workerResult = workerClient.doWork(key);
        watch.stop();
        LogInfoMessageBuilder.builder(LOG)
                .id("worker.returned")
                .message("Result: {}",workerResult)
                .add("duration",""+watch.getTotalTimeMillis())
                .log();
        String result = "Der Worker meldet: " + workerResult;
        return ResponseEntity.ok(result);
    }
}
