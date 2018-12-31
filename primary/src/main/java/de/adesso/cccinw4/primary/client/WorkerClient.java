package de.adesso.cccinw4.primary.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value="workerClient", url = "${worker.url}")
public interface WorkerClient {

    @RequestMapping( method= RequestMethod.GET, value = "/worker/{key}")
    String doWork(@PathVariable("key") String key);
}
