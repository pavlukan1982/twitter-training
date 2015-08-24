package by.pauliukevich.service;

import java.util.List;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;

import twitter4j.Status;
import by.pauliukevich.model.ModelMessage;

public class ServiceTwitter {

	@Produce(uri = "direct:myModel")
	private ProducerTemplate producer;

	public ProducerTemplate getProducer() {
		return producer;
	}

	public void setProducer(ProducerTemplate producer) {
		this.producer = producer;
	}

	public void convertToModel(List<Status> statusList) {
		ModelMessage myModel = new ModelMessage();
		if (statusList.size() > 0) {
			myModel.setMessage(statusList.get(0).getText());
		}
		producer.sendBody(myModel);

	}

}
