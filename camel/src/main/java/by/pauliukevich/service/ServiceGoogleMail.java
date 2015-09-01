package by.pauliukevich.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.RuntimeCamelException;

import by.pauliukevich.model.ModelMessage;

import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;
import com.google.api.services.gmail.model.MessagePartHeader;

public class ServiceGoogleMail {

	@Produce
	private ProducerTemplate producer;

	public ProducerTemplate getProducer() {
		return producer;
	}

	public void setProducer(ProducerTemplate producer) {
		this.producer = producer;
	}

	public void splitToMessage(ListMessagesResponse messageList) {

		if (messageList.getResultSizeEstimate() != null) {
			for (Message message : messageList.getMessages()) {
				if (message != null) {

					Map<String, Object> requestParam = new HashMap<>();

					requestParam.put("CamelGoogleMail.userId", "me");
					requestParam.put("CamelGoogleMail.id", message.getId());

					producer.sendBodyAndHeaders("direct:googleMessage", message.getId(), requestParam);
				}
			}
		} else {

			throw new RuntimeCamelException("Empty google list message response");

		}
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
