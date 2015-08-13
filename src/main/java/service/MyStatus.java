package service;

import java.util.List;

import model.MyTweet;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;

import twitter4j.Status;
import twitter4j.Twitter;

public class MyStatus {

	@Produce(uri = "direct:myTweet")
	private ProducerTemplate producer;

	public ProducerTemplate getProducer() {
		return producer;
	}

	public void setProducer(ProducerTemplate producer) {
		this.producer = producer;
	}

	public void convertToTweet(List<Status> statusList) {
		MyTweet myTweet = new MyTweet();
		if (statusList.size() > 0) {
			myTweet.setMessage(statusList.get(0).getText());
		}
		producer.sendBody(myTweet);

	}

}
