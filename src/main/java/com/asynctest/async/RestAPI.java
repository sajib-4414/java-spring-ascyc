package com.asynctest.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestAPI {
    @Autowired
    private AsyncService asyncService;

    @GetMapping("/api")
    public ResponseEntity<String> testAsync() {
        System.out.println("Controller Thread: " + Thread.currentThread().getName());
        asyncService.doAsyncTask();
        return ResponseEntity.ok("Async task started");
    }
}
