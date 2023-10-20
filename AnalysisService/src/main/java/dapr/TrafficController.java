package dapr;

import dapr.analyis.AnalyseResultClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TrafficController {
    private static final Logger log = LoggerFactory.getLogger(TrafficController.class);

    private final AnalyseResultClient analyseResultClient;

    public TrafficController(final AnalyseResultClient analyseResultClient) {
        this.analyseResultClient = analyseResultClient;
    }

    @PostMapping(path = "/exitcam")
    public ResponseEntity<Void> vehicleExit() {
        handlePossibleSpeedingViolation();
        return ResponseEntity.accepted().build();
    }

    private void handlePossibleSpeedingViolation() {
        log.info("Speeding violation by vehicle {} detected: {} km/h");

        analyseResultClient.analyseResultSend();
    }
}
