package com.demo.threads.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class HeavyService {

    @Async
    public CompletableFuture<String> heavyTaskToExecute(Integer id) throws InterruptedException {
        //try {
            log.info("Executing heavy task in spring in thread: " + Thread.currentThread().getName() + " with id: " + id);
            Thread.sleep(3000);
            if(id == 2) {
                throw new RuntimeException("Failed so bad in thread with id: " + id);
            }
            return CompletableFuture.completedFuture("Task executed successfully");
        /*} catch (InterruptedException e) {
            log.error("InterruptedException => Something went wrong...", e);
            return CompletableFuture.completedFuture("InterruptedException => Task failed: " + e);
        } catch (Exception ex){
            log.error("Exception => Something went wrong... ", ex);
            return CompletableFuture.completedFuture("Exception => Task failed: " + ex);
        }*/
    }
}
