package by.pauliukevich.camel;

import org.junit.Test;

import com.google.api.services.gmail.model.ListMessagesResponse;

public class GoogleSplitMessageTest extends RouteTestSupport {
	@Test
	public void testGoogleMailException() throws Exception {
		// template().sendBody("google-mail://messages/list", "");

		template().sendBody("bean:serviceGoogleMailSplit", new ListMessagesResponse());
	}

	@Override
	public String isMockEndpoints() {
		return "bean:serviceGoogleMailSplit(.)*";
	}

	@Override
	public String isMockEndpointsAndSkip() {
		return "google-mail://messages/list|direct:googleMessage";
	}

	@Override
	protected String getBlueprintDescriptor() {
		return "/OSGI-INF/blueprint/camel-test.xml";
	}

}
