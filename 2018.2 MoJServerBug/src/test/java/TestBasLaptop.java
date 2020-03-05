import static org.junit.Assert.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;

public class TestBasLaptop {

	private final Path JAVAC10 = Paths.get("/Library/Java/JDK10/bin/javac");
	private final Path JAVA10 = Paths.get("/Library/Java/JDK10/bin/java");
	private final JavaVersion JDK10 = new JavaVersion(10, "JDK 10", JAVAC10, JAVA10);
	
	private final Path JAVAC9 = Paths.get("/Library/Java/JDK9/bin/javac");
	private final Path JAVA9 = Paths.get("/Library/Java/JDK9/bin/java");
	private final JavaVersion JDK9 = new JavaVersion(9, "JDK 9", JAVAC9, JAVA9);

	private final String JAVA_HOME = "/Library/Java/JDK/Home";
	private final int JAVA_HOME_VERSION = 9;
	

	@Test
	public void testBasLaptop() {
		// test with only java_home
		JavaVersionSelector jvs = new JavaVersionSelector(envJdk9_10(), configuration());
		assertEquals(JDK9,  jvs.getJavaVersion(9));
		assertEquals(JDK10,  jvs.getJavaVersion(10));
	}

	
	private Environment envJdk9_10() {
		Collection<Path> existingFiles = Arrays.asList(JAVAC10, JAVA10, JAVAC9, JAVA9);
		return new Environment(existingFiles, JAVA_HOME, JAVA_HOME_VERSION);
	}


	private Collection<JavaVersion> configuration() {
		return Arrays.asList(JDK9, JDK10);
	}
	
	
}