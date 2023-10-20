package edu.codespring.cloud.usermanagement.controller;

import edu.codespring.cloud.usermanagement.dto.ScheduleTelescope;
import edu.codespring.cloud.usermanagement.service.ScheduleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScheduleController {

    private static final Logger log = LoggerFactory.getLogger(ScheduleController.class);

    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
        log.info("ScheduleController created");
    }

    @PostMapping(path = "/schedule")
    public ResponseEntity<Void> schedule(@RequestBody ScheduleTelescope scheduleTelescope) {
        log.info("ScheduleController.schedule() called");

        scheduleService.publishEvent(scheduleTelescope);

        return ResponseEntity.accepted().build();
    }
}
