package edu.codespring.cloud.usermanagement.service;

import edu.codespring.cloud.usermanagement.dto.ScheduleTelescope;
import io.dapr.client.DaprClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ScheduleService implements DaprPublisherService<ScheduleTelescope> {

    private static final String TOPIC_SCHEDULE = "schedule";
    private static final String PUBSUB = "pubsub";

    private final DaprClient daprClient;

    @Autowired
    public ScheduleService(DaprClient daprClient) {
        this.daprClient = daprClient;
    }

    @Override
    public void publishEvent(ScheduleTelescope data) {
        data.setId(UUID.randomUUID().toString());

        daprClient.publishEvent(PUBSUB, TOPIC_SCHEDULE, data).block();
    }
}
