package edu.codespring.cloud.usermanagement.dapr;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.dapr.client.DaprClient;
import io.dapr.client.DaprClientBuilder;
import io.dapr.serializer.DefaultObjectSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserManagementDaprConfiguration {

    static class JsonObjectSerializer extends DefaultObjectSerializer {
        public JsonObjectSerializer() {
            OBJECT_MAPPER.registerModule(new JavaTimeModule());
            OBJECT_MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        }
    }

    @Bean
    public DaprClient daprClient() {
        return new DaprClientBuilder()
                .withObjectSerializer(new JsonObjectSerializer())
                .build();
    }
}
