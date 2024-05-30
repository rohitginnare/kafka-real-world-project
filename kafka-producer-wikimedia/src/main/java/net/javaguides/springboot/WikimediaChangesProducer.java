package net.javaguides.springboot;

import java.net.URI;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.launchdarkly.eventsource.*;

@Service
public class WikimediaChangesProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(WikimediaChangesProducer.class);
	
	private KafkaTemplate<String, String> kafkaTemplate;

	//ignore @Autowired, as it has only one Constructor for this bean
	public WikimediaChangesProducer(KafkaTemplate<String, String> kafkaTemplate) {
		super();
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public void sendMessage() throws InterruptedException
	{
		String topic = "wikimedia_recentchange";
		
		// To read real time stream data from wikimedia, we use event source
		EventHandler eventHandler =  new WikimediaChangesHandler(kafkaTemplate, topic);
		String url = "https://stream.wikimedia.org/v2/stream/recentchange";
		
		EventSource eventSource = new EventSource.Builder(eventHandler, URI.create(url))
		    .build();
		
		eventSource.start();
		TimeUnit.MINUTES.sleep(10);
	}
	
}