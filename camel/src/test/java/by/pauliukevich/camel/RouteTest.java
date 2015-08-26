package by.pauliukevich.camel;

import java.io.File;

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

		getMockEndpoint("mock:direct:tweetQueue").expectedMessageCount(2);

		ClassLoader classLoader = getClass().getClassLoader();
		File testFile = new File(classLoader.getResource("data/movies.xml").getFile());

		template().sendBody("file:work/twitter-training/input", testFile);

		assertMockEndpointsSatisfied();
	}

	@Test
	public void testGoogleMailRoute() throws Exception {

		// return only list of message id
		template().sendBodyAndHeader("google-mail://messages/list", "", "CamelGoogleMail.userId", "me");

		assertMockEndpointsSatisfied();

	}

	// @Override
	// public String isMockEndpointsAndSkip() {
	// return "((direct)):(.*)";
	// }

}
