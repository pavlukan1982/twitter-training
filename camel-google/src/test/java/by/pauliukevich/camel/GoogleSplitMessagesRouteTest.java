package by.pauliukevich.camel;

import java.util.List;

import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.model.RouteDefinition;
import org.junit.Test;

public class GoogleSplitMessagesRouteTest extends RouteTestSupport {

	@Test
	public void testGoogleMailRoute() throws Exception {

		MockEndpoint mockEndpoint = getMockEndpoint("mock:bean:serviceGoogleMailSplit");
		mockEndpoint.expectedMessageCount(1);

		// return only list of message id
		// template().sendBodyAndHeader("google-mail://messages/list", "*",
		// "CamelGoogleMail.userId", "me");

		List<RouteDefinition> routeDefinitions = context().getRouteDefinitions();

		assertMockEndpointsSatisfied();

	}

	@Override
	public String isMockEndpoints() {
		return "(google-mail://messages/list)";
	}

	@Override
	public String isMockEndpointsAndSkip() {
		return "(bean:serviceGoogleMail(.)*)";
	}

}
