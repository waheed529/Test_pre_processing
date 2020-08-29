package com.test.preprocessing;

import java.text.DateFormat;

import java.text.SimpleDateFormat;

import java.util.Date;



import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.kafka.core.KafkaTemplate;

import org.springframework.kafka.support.SendResult;

import org.springframework.stereotype.Service;

import org.springframework.util.concurrent.ListenableFuture;

import com.test.model.KafkaPublishedMetadata;
import com.test.preprocessing.KafkaConfig;


@Service
public class KafkaProducerService {



	private static final Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);

	

	 @Autowired

	 private KafkaTemplate<String, byte[]> kafkaTemplate;



	 @Autowired

	 private KafkaConfig kafkaconf;



	@Value("${kafka_request_topic}")
    private String kafkarequesttopic;

	

    public String PublishTestMsg(String data) {

    	Long start_time=System.currentTimeMillis();

        KafkaPublishedMetadata kpmd = null;

        String msg="Processing Completed";

        

        System.out.println("kafka topic ::"+kafkarequesttopic);

        

        System.out.println("kafka kafkaconf ::"+kafkaconf.KAFKA_CONFIG);



        try {

            long offset = -1;

            byte [] byteArr=data.getBytes();

            ListenableFuture<SendResult<String, byte[]>> future=kafkaTemplate.send(kafkarequesttopic, byteArr);

            SendResult<String, byte[]> result = null;

            result = future.get();

            if (result != null) {

                offset = result.getRecordMetadata().offset();

            }

            Date date=new Date();

            DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

            kpmd = new KafkaPublishedMetadata(kafkarequesttopic, data, offset,"Published Successfully",df.format(date));

        } catch (Exception e) {

        	e.printStackTrace();

        	logger.error("Exception in PublishTestMsg : "+e.getMessage());

        	return msg+": failed";

        }

        Long end_time=System.currentTimeMillis();

        logger.info("Time to Execute PublishTestMsg : "+(start_time-end_time));

        return msg+": Success";

    }



   

}