package by.pauliukevich.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;

import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;

public class ServiceGoogleMailSplit {

	@Produce
	private ProducerTemplate producer;

	public ProducerTemplate getProducer() {
		return producer;
	}

	public void setProducer(ProducerTemplate producer) {
		this.producer = producer;
	}

	public void splitToMessage(ListMessagesResponse messageList) throws GoogleMessageException {

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

			throw new GoogleMessageException("Empty google list message response");

		}
	}

}
