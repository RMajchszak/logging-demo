package de.adesso.cccinw4.worker;

import org.apache.commons.math3.distribution.ParetoDistribution;
import org.apache.commons.math3.random.RandomGeneratorFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping(value = "/api", produces = "application/json")
public class WorkerController {

    private static final Logger LOG = LogManager.getLogger(WorkerController.class);

    @GetMapping(value = "/worker/{key}")
    public ResponseEntity<String> compute( @PathVariable("key") String key ) throws InterruptedException {
        LOG.info("Eingehender Reqzest mit {} als Key", key);
        ParetoDistribution distribution = new ParetoDistribution(
                RandomGeneratorFactory.createRandomGenerator(ThreadLocalRandom.current()),
                0.5,
                1.4);
        double value = distribution.sample();
        Thread.sleep( (long)(value * 1000));
        String result ="Das Ergebnis für " + key + " ist " + value;
        return ResponseEntity.ok(result);
    }
}
