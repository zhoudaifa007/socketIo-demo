package com.example.service;

import com.example.utils.KafkaUtil;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

/**
 * Created by ZHOUDF2 on 2016-12-28.
 */
public class KafkaProducerTest {
    public static void main(String[] args) throws Exception{
        Producer<String, String> producer = KafkaUtil.getProducer();
        int i = 0;
        while(true) {
            ProducerRecord<String, String> record = new ProducerRecord<String, String>("test1", String.valueOf(i), "this is message"+i);
            producer.send(record, new Callback() {
                public void onCompletion(RecordMetadata metadata, Exception e) {
                    if (e != null)
                        e.printStackTrace();
                    System.out.println("message send to partition " + metadata.partition() + ", offset: " + metadata.offset());
                }
            });
            i++;
            Thread.sleep(1000);
        }
    }
}
