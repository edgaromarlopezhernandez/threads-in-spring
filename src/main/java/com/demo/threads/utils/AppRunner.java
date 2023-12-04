package com.demo.threads.utils;

import com.demo.threads.service.HeavyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class AppRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);

    @Autowired
    private HeavyService heavyService;

    @Override
    public void run(String... args) throws Exception {
        // Start the clock
        long start = System.currentTimeMillis();

        // Kick of multiple, asynchronous lookups
        /*CompletableFuture<String> task1 = heavyService.heavyTaskToExecute(1).exceptionally(ex -> "Error: " + ex.getMessage());
        CompletableFuture<String> task2 = heavyService.heavyTaskToExecute(2).exceptionally(ex -> "Error: " + ex.getMessage());
        CompletableFuture<String> task3 = heavyService.heavyTaskToExecute(3).exceptionally(ex -> "Error: " + ex.getMessage());*/

        CompletableFuture<String> task1 = heavyService.heavyTaskToExecute(1).exceptionally(Throwable::toString);
        CompletableFuture<String> task2 = heavyService.heavyTaskToExecute(2).exceptionally(Throwable::toString);
        CompletableFuture<String> task3 = heavyService.heavyTaskToExecute(3).exceptionally(Throwable::toString);

        // Wait until they are all done
        CompletableFuture.allOf(task1,task2,task3).join();

        // Print results, including elapsed time
        logger.info("Elapsed time: " + (System.currentTimeMillis() - start));
        logger.info("task1--> " + task1.get());
        logger.info("task2--> " + task2.get());
        logger.info("task3--> " + task3.get());
    }

}
