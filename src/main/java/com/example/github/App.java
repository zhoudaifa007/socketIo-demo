package com.example.github;

/**
 * Created by ZHOUDF2 on 2016-12-29.
 */
public class App {
    public static void main( String[] args ) {
        MyKafkaProducer producer = new MyKafkaProducer();
        MyKafkaConsumer consumer = new MyKafkaConsumer();

		//producer.runProducer();
        consumer.runConsumer();

    }

}