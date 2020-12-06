package com.abhi.kafka.producer.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhi.kafka.producer.models.User;

@RestController
@RequestMapping("kafka")
public class UserResource {
	@Autowired
	private KafkaTemplate<String, User> kafkaTemplate;
	
	@Autowired
	private KafkaTemplate<String, String> textKafkaTemplate;
	
	private static final String PUBLISH_TOPIC = "Kafka_Example";
	
	private static final String PUBLISH_TEXT_TOPIC = "Kafka_Example_Text";

	@PostMapping("/publish")
	public String post(@RequestBody final User user) {
		
		kafkaTemplate.send(PUBLISH_TOPIC, user);
		
		return "Published successfully";

	}
	
	
	@PostMapping("/publish/{message}")
	public String postText(@PathVariable("message") final String message) {
		
		textKafkaTemplate.send(PUBLISH_TEXT_TOPIC, message);
		
		return "Published successfully";

	}
}
