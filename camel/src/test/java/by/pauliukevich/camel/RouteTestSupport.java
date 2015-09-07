package by.pauliukevich.camel;

import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;

public abstract class RouteTestSupport extends CamelBlueprintTestSupport {

	@Override
	protected String getBlueprintDescriptor() {
		return "/OSGI-INF/blueprint/camel-twitter.xml";
	}

	@Override
	public boolean isUseDebugger() {
		return true;
	}

	@Override
	public String isMockEndpoints() {
		return "*";
	}

}
