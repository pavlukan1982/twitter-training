package by.pauliukevich.camel;

import static org.ops4j.pax.exam.CoreOptions.maven;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.features;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.karafDistributionConfiguration;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.keepRuntimeFolder;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.logLevel;

import java.io.File;

import javax.inject.Inject;

import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.karaf.features.FeaturesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.karaf.options.LogLevelOption;

@RunWith(PaxExam.class)
public class BundleTest extends CamelTestSupport {

	@Inject
	protected FeaturesService featuresService;

	@Configuration
	public static Option[] configure() throws Exception {
		return new Option[] {
				karafDistributionConfiguration()
				.frameworkUrl(
						maven().groupId("org.apache.karaf").artifactId("apache-karaf").type("tar.gz")
						.version("4.0.1")).karafVersion("4.0.1").useDeployFolder(false)
						.unpackDirectory(new File("target/paxexam/unpack")),
						logLevel(LogLevelOption.LogLevel.WARN),
						features(
								maven().groupId("org.apache.camel.karaf").artifactId("apache-camel").type("xml")
								.classifier("features").version("2.15.1.redhat-620133"), "camel-blueprint",
						"camel-test"), keepRuntimeFolder() };
		// ,
		// features(maven().groupId("by.pauliukevich").artifactId("feature").type("xml").classifier("features")
		// .version("1.0"), "camel-training-camel")
	}

	@Test
	public void testProvisioning() throws Exception {

		assertTrue(featuresService.isInstalled(featuresService.getFeature("camel-blueprint")));
		// assertTrue(featuresService.isInstalled(featuresService.getFeature("camel-training-camel")));

	}

}
