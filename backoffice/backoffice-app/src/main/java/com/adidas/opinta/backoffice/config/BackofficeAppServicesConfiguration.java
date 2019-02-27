package com.adidas.opinta.backoffice.config;

import com.adidas.opinta.backoffice.service.RouteService;
import com.adidas.opinta.integration.properties.BackOfficeTopics;
import com.adidas.opinta.model.Route;
import java.util.List;
import kryo.KryoSerdeFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.kafka.dsl.Kafka;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.config.ContainerProperties;

@Configuration
@Slf4j
public class BackofficeAppServicesConfiguration {

    @Bean
    public IntegrationFlow getAllRoutesInboundGateway(
            BackOfficeTopics topics,
            ConsumerFactory<String, String> stringStringConsumerFactory,
            ProducerFactory<String, List<Route>> stringGraphProducerFactory,
            RouteService routeService) {
        return IntegrationFlows
                .from(Kafka.inboundGateway(stringStringConsumerFactory,
                        new ContainerProperties(topics.getAllRoutes()), stringGraphProducerFactory))
                .transform(code -> routeService.findAll())
                .get();
    }

    @Bean
    public ProducerFactory<String, List<Route>> stringGraphProducerFactory(
            KafkaProperties kafkaProperties,
            KryoSerdeFactory serdeFactory) {
        final StringSerializer keySerializer = new StringSerializer();
        final Serializer<List<Route>> valueSerializer = serdeFactory.getPooledSerializer();
        return new DefaultKafkaProducerFactory<>(
                kafkaProperties.buildProducerProperties(), keySerializer, valueSerializer);
    }

    @Bean
    public ConsumerFactory<String, String> stringStringConsumerFactory(KafkaProperties kafkaProperties) {
        final StringDeserializer keyDeserializer = new StringDeserializer();
        return new DefaultKafkaConsumerFactory<>(
                kafkaProperties.buildConsumerProperties(), keyDeserializer, keyDeserializer);
    }
}
