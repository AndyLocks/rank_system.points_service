package com.leute.rank_system.points_service.config;

import com.leute.rank_system.points_service.entity.KafkaPointMessage;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaPointsForRewardServiceProducer {

    @Value("${kafka.host}")
    private String kafkaHost;

    @Bean
    public ProducerFactory<String, KafkaPointMessage> notificationProducerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, KafkaPointMessage> notificationKafkaTemplate() {
        return new KafkaTemplate<>(notificationProducerFactory());
    }

    private Map<String, Object> producerConfigs() {

        var props = new HashMap<String, Object>();

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaHost);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return props;
    }

}
