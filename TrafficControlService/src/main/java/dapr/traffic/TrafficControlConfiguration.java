package dapr.traffic;

import dapr.traffic.fines.FineCollectionClient;
import dapr.traffic.vehicle.InMemoryVehicleStateRepository;
import dapr.traffic.vehicle.VehicleStateRepository;
import dapr.traffic.violation.DefaultSpeedingViolationCalculator;
import dapr.traffic.violation.SpeedingViolationCalculator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dapr.traffic.fines.DaprFineCollectionClient;
import io.dapr.client.DaprClient;
import io.dapr.client.DaprClientBuilder;
import io.dapr.serializer.DefaultObjectSerializer;



@Configuration
public class TrafficControlConfiguration {
	
	static class JsonObjectSerializer extends DefaultObjectSerializer {
	    public JsonObjectSerializer() {
	        OBJECT_MAPPER.registerModule(new JavaTimeModule());
	        OBJECT_MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	    }
	}
	
    @Value("${traffic.road-id}")
    private String roadId;

    @Value("${traffic.section-length}")
    private int sectionLength;

    @Value("${traffic.speed-limit}")
    private int speedLimit;

    @Value("${traffic.legal-correction}")
    private int legalCorrection;


    @Bean
    public VehicleStateRepository vehicleStateRepository() {
        return new InMemoryVehicleStateRepository();
    }

    @Bean
    public SpeedingViolationCalculator speedingViolationCalculator() {
        return new DefaultSpeedingViolationCalculator(legalCorrection, speedLimit, roadId, sectionLength);
    }

    @Bean
    public FineCollectionClient fineCollectionClient(final DaprClient daprClient) {
        return new DaprFineCollectionClient(daprClient);
    }
    
    @Bean
    public DaprClient daprClient() {
        return new DaprClientBuilder()
                .withObjectSerializer(new JsonObjectSerializer())
                .build();
    }
}
