package org.novatec.hunger;



import java.util.logging.Logger;
import java.awt.Desktop;
import java.net.URI;

import org.camunda.bpm.client.ExternalTaskClient;

public class hungerWorker {
        private final static Logger LOGGER = Logger.getLogger(hungerWorker.class.getName());

        public static void main(String[] args) {
            ExternalTaskClient client = ExternalTaskClient.create()
                    .baseUrl("http://localhost:8080/engine-rest")
                    .asyncResponseTimeout(10000) // long polling timeout
                    .build();

            // subscribe to an external task topic as specified in the process
            client.subscribe("foodDecision")
                    .lockDuration(1000) // the default lock duration is 20 seconds, but you can override this
                    .handler((externalTask, externalTaskService) -> {
                        // Put your business logic here

                        // Get a process variable
                        String item = externalTask.getVariable("desired food");
                        Integer amount = externalTask.getVariable("amount of food");

                        LOGGER.info("Purchasing an amount of '" + amount + "' of '" + item + "'...");

                        // Complete the task
                        externalTaskService.complete(externalTask);
                    })
                    .open();
        }
    }


