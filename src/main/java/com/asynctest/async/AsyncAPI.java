package com.asynctest.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
public class AsyncAPI {
    @Autowired
    private AsyncService asyncService;

    @GetMapping("/api")
    public DeferredResult<ResponseEntity<String>> testAsync() {
        DeferredResult<ResponseEntity<String>> deferredResult = new DeferredResult<>(5000L);
        System.out.println("Controller Thread begnning: " + Thread.currentThread().getName());

        asyncService.doAsyncTask()
        .thenAccept(result-> {

            deferredResult.setResult(ResponseEntity.ok(result));
            System.out.println("Async Completion Thread: " + Thread.currentThread().getName());
        })
                .exceptionally(throwable -> {
                    deferredResult.setResult(
                            ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                    .body(throwable.getMessage())
                    );
                    return null;
                });
        System.out.println("Controller Thread end: " + Thread.currentThread().getName());
        return deferredResult;
    }
}
