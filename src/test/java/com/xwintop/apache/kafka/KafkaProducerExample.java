package com.xwintop.apache.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Test;

import java.util.Properties;

/**
 * @ClassName: KafkaProducerExample
 * @Description: 生产者代码
 * @author: xufeng
 * @date: 2017年10月20日 上午10:04:22
 */
public class KafkaProducerExample {
	@Test
	public void testmain() {
		Properties props = new Properties();
		props.put("bootstrap.servers", "master:9092");
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		Producer<String, String> producer = new KafkaProducer<>(props);
		for (int i = 0; i < 100; i++)
			producer.send(new ProducerRecord<>("topic1", Integer.toString(i), Integer.toString(i)));

		producer.close();
	}
}
