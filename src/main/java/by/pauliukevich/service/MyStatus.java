package by.pauliukevich.service;

import java.util.List;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;

import twitter4j.Status;
import by.pauliukevich.model.MyTweet;

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
