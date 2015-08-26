package by.pauliukevich.camel;

import static org.ops4j.pax.exam.CoreOptions.maven;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.features;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.karafDistributionConfiguration;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.keepRuntimeFolder;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.logLevel;

import java.io.File;

import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.karaf.options.LogLevelOption;

@RunWith(PaxExam.class)
public class BundleTest extends CamelTestSupport {

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
								.classifier("features").version("2.12.1"), "camel-blueprint", "camel-test"),
				features(
						maven().groupId("net.nanthrax.blog").artifactId("camel-blueprint").type("xml")
								.classifier("features").version("1.0-SNAPSHOT"), "blog-camel-blueprint-route"),
				keepRuntimeFolder() };
	}

}
