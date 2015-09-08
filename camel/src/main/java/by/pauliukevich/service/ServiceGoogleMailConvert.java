package by.pauliukevich.service;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;

import by.pauliukevich.model.ModelMessage;

import com.google.api.services.gmail.model.Message;
import com.google.api.services.gmail.model.MessagePartHeader;

public class ServiceGoogleMailConvert {

	@Produce
	private ProducerTemplate producer;

	public ProducerTemplate getProducer() {
		return producer;
	}

	public void setProducer(ProducerTemplate producer) {
		this.producer = producer;
	}

	public void convertToModel(Message message) {

		ModelMessage myModel = new ModelMessage();

		for (MessagePartHeader header : message.getPayload().getHeaders()) {
			if ("Subject".equals(header.getName())) {
				myModel.setMessage(header.getValue());
				producer.sendBody("direct:myModel", myModel);
			}
		}
	}
}
