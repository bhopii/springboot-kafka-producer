package com.abhi.kafka.producer.resource;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhi.kafka.producer.models.User;

@RestController
@RequestMapping("kafka")
public class UserResource {
	@Autowired
	private KafkaTemplate<String, User> kafkaTemplate;
	
	private static final String PUBLISH_TOPIC = "Kafka_Example";

	@PostMapping("/publish/{message}")
	public String post(@PathVariable("message") final String message) {
		
		kafkaTemplate.send(PUBLISH_TOPIC, new User("Abhishek", BigInteger.valueOf(2000000l), "hr"));
		
		return "Published successfully";

	}
}
