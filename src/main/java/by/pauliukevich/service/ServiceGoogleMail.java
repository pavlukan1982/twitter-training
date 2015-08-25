package by.pauliukevich.service;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;

import by.pauliukevich.model.ModelMessage;

import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;

public class ServiceGoogleMail {

	@Produce(uri = "direct:myModel")
	private ProducerTemplate producer;

	public ProducerTemplate getProducer() {
		return producer;
	}

	public void setProducer(ProducerTemplate producer) {
		this.producer = producer;
	}

	public void convertToModel(ListMessagesResponse messageList) {

		ModelMessage myModel = new ModelMessage();

		for (Message message : messageList.getMessages()) {
			if (message != null) {
				producer.sendBody(message.getRaw());
			}
			// for (MessagePartHeader header :
			// message.getPayload().getHeaders()) {
			// myModel.setMessage(header.getName());
			// producer.sendBody(myModel);
			// }
		}
	}

}
