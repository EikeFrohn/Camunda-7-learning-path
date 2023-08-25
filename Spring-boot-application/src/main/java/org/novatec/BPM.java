package org.novatec;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.spring.boot.starter.annotation.*;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@EnableProcessApplication

public class BPM {
    public static void main(String... args) {
        SpringApplication.run(BPM.class, args);

    }
    @Autowired
    private RuntimeService runtimeService;

    @EventListener
    private void processPostDeploy(PostDeployEvent event) {
        runtimeService.startProcessInstanceByKey("foodDecision");
    }
}
