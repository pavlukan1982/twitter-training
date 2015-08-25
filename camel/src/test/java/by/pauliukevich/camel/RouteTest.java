package by.pauliukevich.camel;

import java.io.File;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
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
		File testFile = new File(classLoader.getResource("data/movies.xml")
				.getFile());

		template().sendBody("file:work/twitter-training/input", testFile);

		assertMockEndpointsSatisfied();
	}

	@Test
	public void testGoogleMail() throws Exception {

		CamelContext camelContext = context();

		camelContext.addRoutes(new RouteBuilder() {
			@Override
			public void configure() throws Exception {
				from("direct:from").to("google-mail://messages/list?userId=me")
						.log("Google mail request ${in.body}")
						.to("bean:serviceGoogleMail?method=convertToModel")
						.log("Google result ${body}").to("mock:end");

			}

		});

		template().sendBody("direct:from", "");

	}

	// @Override
	// public String isMockEndpointsAndSkip() {
	// return "((direct)):(.*)";
	// }

}
