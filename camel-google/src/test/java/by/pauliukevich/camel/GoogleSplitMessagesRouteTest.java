package by.pauliukevich.camel;

import org.junit.Test;

public class GoogleSplitMessagesRouteTest extends RouteTestSupport {

	@Test
	public void testGoogleMailRoute() throws Exception {

		// MockEndpoint mockEndpoint =
		// getMockEndpoint("mock:bean:serviceGoogleMail");
		// mockEndpoint.expectedMessageCount(14);

		// return only list of message id
		template().sendBodyAndHeader("google-mail://messages/list", "*", "CamelGoogleMail.userId", "me");

		assertMockEndpointsSatisfied();

	}

	@Override
	public String isMockEndpoints() {
		return "google-mail://messages/list";
	}

	// @Override
	// public String isMockEndpointsAndSkip() {
	// return "bean:serviceGoogleMail(.)*";
	// }

}
