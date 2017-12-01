package com.example.Demo;

import com.example.utils.PropertyUtils;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

/**
 * Created by ZHOUDF2 on 2016-12-28.
 */
public class KafkaConsumerDemo {
    public static void main(String[] args) {

        new KafkaConsumerDemo().consume();
    }

    public void consume() {

        JsonParser jsonParser = new JsonParser();

        // and the consumer
        KafkaConsumer<String, String> consumer = null;
        try {
            Properties props = PropertyUtils.load("consumer_config.properties");
            consumer = new KafkaConsumer<>(props);

            //subscribe topics
            consumer.subscribe(Arrays.asList("hello", "test"));

            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(100);
                for (ConsumerRecord<String, String> record : records){

//                  System.out.printf("offset -> %d, key -> %s, value -> %s",
//                          record.offset(), record.key(), record.value());

                    switch (record.topic()) {
                        case "hello":

                            JsonObject jObj = (JsonObject)jsonParser.parse(record.value());
                            switch (jObj.get("type").getAsString()) {
                                case "test":

                                    long latency = System.currentTimeMillis() - jObj.get("t").getAsLong();
                                    System.out.println(latency);

                                    break;
                                case "marker":

                                    break;
                                default:
                                    break;
                            }
                            break;
                        case "test":

                            break;
                        default:
                            throw new IllegalStateException("Shouldn't be possible to get message on topic " + record.topic());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(consumer!=null){
                consumer.close();
            }
        }
    }
}
