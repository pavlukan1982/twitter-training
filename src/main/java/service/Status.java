package service;

import model.Tweet;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;

public class Status {

	@Produce(uri = "direct:myTweet")
	private ProducerTemplate producer;

	public ProducerTemplate getProducer() {
		return producer;
	}

	public void setProducer(ProducerTemplate producer) {
		this.producer = producer;
	}

	public void convertToTweet(twitter4j.Status status) {

		Tweet tweet = new Tweet();
		tweet.setMessage(status.getText());

		producer.sendBody(tweet);

	}

}
