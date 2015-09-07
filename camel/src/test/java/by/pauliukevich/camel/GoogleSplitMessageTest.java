package by.pauliukevich.camel;

import org.junit.Test;

import com.google.api.services.gmail.model.ListMessagesResponse;

public class GoogleSplitMessageTest extends RouteTestSupport {
	@Test
	public void testGoogleMailException() throws Exception {
		// template().sendBody("google-mail://messages/list", "");

		template().sendBody("bean:serviceGoogleMail", new ListMessagesResponse());
	}

	@Override
	public String isMockEndpoints() {
		return "bean:serviceGoogleMail";
	}
}
