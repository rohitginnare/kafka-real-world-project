package net.javaguides.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.entity.WikimediaData;
import net.javaguides.springboot.repository.WikimediaDataRepository;

@Service
public class KafkaDatabaseConsumer {
	
	

	private WikimediaDataRepository dataRepository;
	
	private KafkaDatabaseConsumer(WikimediaDataRepository dataRepository) {
		super();
		this.dataRepository = dataRepository;
	}

	private static final Logger LOGGER=LoggerFactory.getLogger(KafkaDatabaseConsumer.class);
	
	@KafkaListener(topics = "wikimedia_recentchange", groupId = "myGroup")
	public void consume(String eventMessage)
	{
		LOGGER.info(String.format("Event message received : %s", eventMessage));
		
		WikimediaData wikimediaData= new WikimediaData();
		wikimediaData.setWikiEventData(eventMessage);
		
		dataRepository.save(wikimediaData);
	}
}