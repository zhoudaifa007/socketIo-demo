package com.example.service;


import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * Created by ZHOUDF2 on 2016-12-28.
 */
public class KafkaProducerClient {
    private static Producer<String,String> producer;
    private final Properties props=new Properties();
    private int total = 1000000;

    public KafkaProducerClient(){
        //定义连接的broker list
        props.put("metadata.broker.list", "192.168.4.31:9092");
        //定义序列化类（Java对象传输前要序列化）
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        producer =  new KafkaProducer<String, String>(props);
    }

    public void  send()
    {
        for (int i = 0; i < total; i++)
        {
            producer.send(new ProducerRecord<String, String>("hello",
                    String.valueOf(i), String.format("{\"type\":\"test\", \"t\":%d, \"k\":%d}", System.currentTimeMillis(), i)));

            if (i % 1000 == 0)
            {
                producer.send(new ProducerRecord<String, String>("test", String.format("{\"type\":\"marker\", \"t\":%d, \"k\":%d}", System.currentTimeMillis(), i)));
                producer.send(new ProducerRecord<String, String>("hello", String.format("{\"type\":\"marker\", \"t\":%d, \"k\":%d}", System.currentTimeMillis(), i)));
                producer.flush();
                System.out.println("Sent msg number " + i);
            }
        }
    }
}
