package by.pauliukevich.camel;

import java.io.File;

import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.junit.Test;

public class RouteTest extends CamelBlueprintTestSupport {

	@Override
	protected String getBlueprintDescriptor() {
		return "/OSGI-INF/blueprint/camel-twitter.xml";
	}

	@Override
	public boolean isUseDebugger() {
		return true;
	}

	@Test
	public void testGetFile() throws Exception {

		MockEndpoint endpoint = getMockEndpoint("mock:direct:tweetQueue");
		endpoint.expectedMessageCount(2);

		ClassLoader classLoader = getClass().getClassLoader();
		File testFile = new File(classLoader.getResource("data/movies.xml")
				.getFile());

		template().sendBody("file:work/twitter-training/input", testFile);

		assertMockEndpointsSatisfied();
	}

	@Test
	public void testGoogleMailRoute() throws Exception {

		MockEndpoint mockEndpoint = getMockEndpoint("mock:bean:serviceGoogleMail");
		// mockEndpoint.setAssertPeriod(5000);
		mockEndpoint.expectedMessageCount(14);

		// return only list of message id
		// template().sendBodyAndHeader("google-mail://messages/list", "",
		// "CamelGoogleMail.userId", "me");

		template().sendBodyAndHeader("direct:googleSplitMessages", "",
				"CamelGoogleMail.userId", "me");

		assertMockEndpointsSatisfied();
	}

	@Test
	public void testGoogleMailException() throws Exception {
		// template().sendBody("direct:throwException", "");
		getMockEndpoint("mock:error").expectedMessageCount(1);

		template().sendBodyAndHeader("direct:googleSplitMessages", "",
				"CamelGoogleMail.q", "from:someuser@example.com");

		// template().sendBody("bean:serviceGoogleMail",
		// new ListMessagesResponse());
		assertMockEndpointsSatisfied();
	}

	@Override
	public String isMockEndpoints() {
		return "direct:tweetQueue|bean:serviceGoogleMail(.)*";
		// return null;
		// return "*";
	}

}
