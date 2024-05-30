package net.javaguides.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import com.launchdarkly.eventsource.MessageEvent;
import com.launchdarkly.eventsource.EventHandler;

public class WikimediaChangesHandler implements EventHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(WikimediaChangesHandler.class);
	private KafkaTemplate<String, String> kafkaTemplate;
	private String topic;

	public WikimediaChangesHandler(KafkaTemplate<String, String> kafkaTemplate, String topic) {
		super();
		this.kafkaTemplate = kafkaTemplate;
		this.topic = topic;
	}
	

	public WikimediaChangesHandler() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public void onOpen() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClosed() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMessage(String event, MessageEvent messageEvent) throws Exception {

		// Call Kafka Template
		LOGGER.info(String.format("Event data : %s", messageEvent.getData()));
		
		kafkaTemplate.send(topic, messageEvent.getData());
		
		
	}

	@Override
	public void onComment(String comment) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onError(Throwable t) {
		// TODO Auto-generated method stub
		
	}

}
