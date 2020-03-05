import static org.junit.Assert.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;

public class TestTrivialCase {

	private final Path JAVAC10 = Paths.get("/Library/Java/JDK10/bin/javac");
	private final Path JAVA10 = Paths.get("/Library/Java/JDK10/bin/java");
	private final JavaVersion JDK10 = new JavaVersion(10, "JDK 10", JAVAC10, JAVA10);

	private final Path JAVAC9 = Paths.get("/Library/Java/JDK/Home/bin/javac");
	private final Path JAVA9 = Paths.get("/Library/Java/JDK/Home/bin/java");
	private final JavaVersion JDK9_JAVAHOME = new JavaVersion(9, "JDK 9", JAVAC9, JAVA9);
	
	private final String JAVA_HOME = "/Library/Java/JDK/Home";
	private final int JAVA_HOME_VERSION = 9;
	
	@Test
	public void testTrivialCase() {
		// test with JDK10 configured and requested
		JavaVersionSelector jvs = new JavaVersionSelector(envJdk10(), configuration());
		assertEquals(JDK10,  jvs.getJavaVersion(10));
	}

	@Test
	public void testFallback() {
		// test with only java_home
		JavaVersionSelector jvs = new JavaVersionSelector(envJavahome(), configuration());
		assertEquals(JDK9_JAVAHOME,  jvs.getJavaVersion(9));
		
	}

	
	private Environment envJdk10() {
		Collection<Path> existingFiles = Arrays.asList(JAVAC10, JAVA10);
		return new Environment(existingFiles, JAVA_HOME, JAVA_HOME_VERSION);
	}

	private Environment envJavahome() {
		Collection<Path> existingFiles = new ArrayList<>();
		return new Environment(existingFiles, JAVA_HOME, JAVA_HOME_VERSION);
	}

	private Collection<JavaVersion> configuration() {
		Collection<JavaVersion> jvs = new ArrayList<>();
		jvs.add( JDK10 );
		return jvs;
	}
	
	

}