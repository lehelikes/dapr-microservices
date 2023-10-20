package edu.codespring.cloud.usermanagement.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScheduleController {

    private static final Logger log = LoggerFactory.getLogger(ScheduleController.class);

    public ScheduleController() {
        log.info("ScheduleController created");
    }

    @PostMapping(path = "/schedule")
    public ResponseEntity<Void> schedule() {
        log.info("ScheduleController.schedule() called");

        return ResponseEntity.accepted().build();
    }
}
