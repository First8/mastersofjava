import static org.junit.Assert.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;

public class TestArjansLaptop {
	private final Path JAVAC10 = Paths.get("/Library/Java/JDK10/bin/javac");
	private final Path JAVA10 = Paths.get("/Library/Java/JDK10/bin/java");
	private final JavaVersion JDK10 = new JavaVersion(10, "JDK 10", JAVAC10, JAVA10);

	@Test
	public void testArjansLaptop() {
		// test with JDK10 configured and requested
		JavaVersionSelector jvs = new JavaVersionSelector(envJdk10(), configuration());
		assertEquals(JDK10,  jvs.getJavaVersion(10));
	}

	private Environment envJdk10() {
		Collection<Path> existingFiles = Arrays.asList(JAVAC10, JAVA10);
		return new Environment(existingFiles);
	}

	private Collection<JavaVersion> configuration() {
		Collection<JavaVersion> jvs = new ArrayList<>();
		jvs.add( JDK10 );
		return jvs;
	}

}