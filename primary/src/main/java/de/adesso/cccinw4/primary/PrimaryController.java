package de.adesso.cccinw4.primary;

import de.adesso.cccinw4.primary.client.WorkerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api", produces = "application/json")
public class PrimaryController {

    private final WorkerClient workerClient;

    @Autowired
    public PrimaryController(WorkerClient workerClient) {
        this.workerClient = workerClient;
    }


    @GetMapping(value = "/primary/{key}")
    public ResponseEntity<String> compute(@PathVariable("key") String key )  {
        String workerResult = workerClient.doWork(key);
        String result = "Der Worker meldet: " + workerResult;
        return ResponseEntity.ok(result);
    }
}
