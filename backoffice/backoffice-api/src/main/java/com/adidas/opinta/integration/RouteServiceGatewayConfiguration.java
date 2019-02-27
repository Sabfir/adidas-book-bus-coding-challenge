package com.adidas.opinta.integration;

import com.adidas.opinta.integration.properties.BackOfficeTopics;
import java.util.List;
import java.util.UUID;
import kryo.KryoSerdeFactory;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.config.ContainerProperties;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;

import static com.adidas.opinta.integration.RouteServiceGateway.CHANNEL_GET_ALL_ROUTES_REPLY;
import static com.adidas.opinta.integration.RouteServiceGateway.CHANNEL_GET_ALL_ROUTES_REQUEST;
import static org.springframework.integration.kafka.dsl.Kafka.outboundGateway;
import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Configuration
public class RouteServiceGatewayConfiguration {

    @Bean
    public IntegrationFlow getAllRoutes(
            BackOfficeTopics topics,
            ReplyingKafkaTemplate<String, String, List> kafkaTemplate) {
        // FEATURE: add redis cache here with some ttl before send message to the kafka
        return IntegrationFlows.from(CHANNEL_GET_ALL_ROUTES_REQUEST)
                .enrichHeaders(h -> h.header(TOPIC, topics.getAllRoutes()))
                .handle(outboundGateway(kafkaTemplate))
                .channel(CHANNEL_GET_ALL_ROUTES_REPLY)
                .get();
    }

    @SuppressWarnings("unchecked")
    @Bean
    public ReplyingKafkaTemplate<String, String, List> replyingKafkaTemplate(
            BackOfficeTopics topics, ProducerFactory<String, String> requestProducerFactory,
            ConsumerFactory<String, List> replyConsumerFactory, UUID instanceId) {
        final String replyTopic = topics.getAllRoutesByDepartureCite() + "-" + instanceId;
        final ContainerProperties containerProperties = new ContainerProperties(replyTopic);
        KafkaMessageListenerContainer<String, List> replyListenerContainer =
                new KafkaMessageListenerContainer<>(replyConsumerFactory, containerProperties);
        return new ReplyingKafkaTemplate(requestProducerFactory, replyListenerContainer);
    }

    @Bean
    public ConsumerFactory<String, List> replyConsumerFactory(
            KafkaProperties kafkaProperties,
            KryoSerdeFactory serdeFactory) {
        final StringDeserializer keyDeserializer = new StringDeserializer();
        final Deserializer<List> valueDeserializer = serdeFactory.getPooledDeserializer(List.class);

        return new DefaultKafkaConsumerFactory<>(
                kafkaProperties.buildConsumerProperties(), keyDeserializer, valueDeserializer);
    }

    @Bean
    public ProducerFactory<String, String> producerFactory(KafkaProperties kafkaProperties) {
        final StringSerializer keySerializer = new StringSerializer();
        return new DefaultKafkaProducerFactory<>(
                kafkaProperties.buildProducerProperties(), keySerializer, keySerializer);
    }
}
