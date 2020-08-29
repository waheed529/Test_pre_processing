package com.test.preprocessing;

import org.apache.kafka.clients.consumer.ConsumerConfig;

import org.apache.kafka.clients.producer.ProducerConfig;

import org.apache.kafka.common.serialization.ByteArrayDeserializer;

import org.apache.kafka.common.serialization.ByteArraySerializer;

import org.apache.kafka.common.serialization.StringDeserializer;

import org.apache.kafka.common.serialization.StringSerializer;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;

import org.springframework.kafka.core.*;



import java.util.HashMap;

import java.util.Map;



@Configuration

public class KafkaConfig {



    @Value("${KAFKA_CONFIG}")

    String KAFKA_CONFIG;



    @Bean

    public ProducerFactory<String,  byte[]> producerFactory() {

        Map<String, Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_CONFIG);

        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class);



        return new DefaultKafkaProducerFactory<>(config);

    }



    @Bean

    public KafkaTemplate<String, byte[]> kafkaTemplate() {

        return new KafkaTemplate<>(producerFactory());

    }



   



    

    @Bean

    public ConsumerFactory<String, String> consumerFactory() {

        Map<String, Object> config = new HashMap<>();

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_CONFIG);

        //config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_hb");

        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);



        return new DefaultKafkaConsumerFactory<>(config);

    }





    @Bean

    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory();

        factory.setConsumerFactory(consumerFactory());

        return factory;

    }



    @Bean

    public ConsumerFactory<String, byte[]> outputConsumerFactory() {

        Map<String, Object> config = new HashMap<>();

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_CONFIG);

        //config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_hb_output");

        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ByteArrayDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),

                new ByteArrayDeserializer());

    }



    @Bean

    public ConcurrentKafkaListenerContainerFactory<String, byte[]> outputKafkaListenerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, byte[]> factory = new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(outputConsumerFactory());

        return factory;

    }

}
