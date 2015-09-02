package by.pauliukevich.camel;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

import by.pauliukevich.service.GoogleMessageException;

import com.google.api.services.gmail.model.ListMessagesResponse;

public class GoogleExceptionTest extends CamelTestSupport {

	@Produce
	protected ProducerTemplate template;

	@Override
	protected RouteBuilder createRouteBuilder() throws Exception {
		return new RouteBuilder() {

			@Override
			public void configure() throws Exception {
				onException(by.pauliukevich.service.GoogleMessageException.class).log("Error response list").handled(
						true);
				from("direct:test").throwException(new GoogleMessageException("Error response list")).to("mock:error");
			}
		};
	}

	@Test
	public void testGoogleException() {
		template.sendBody("direct:test", new ListMessagesResponse());
	}

}
