import java.nio.file.Path;
import java.util.Collection;

/**
 * A mock for the environment the server is running in.
 * Mocks the file existance checks and the java home settings.
 * @author arjanl
 *
 */
public class Environment {
	
	private final Collection<Path> existingFiles;

	private final String javaHome;
	private final Integer version;

	public Environment(Collection<Path> existingFiles) {
		this.existingFiles = existingFiles;
		javaHome = null;
		version = null;
	}

	public Environment(Collection<Path> existingFiles, String javaHome, int version) {
		this.existingFiles = existingFiles;
		this.javaHome = javaHome;
		this.version = version;
	}

	public boolean exists(Path p) {
		return existingFiles.contains(p);
	}

	public String getSystemEnv(String param) {
		if (!param.equals("JAVA_HOME")) {
			throw new IllegalArgumentException("only JAVA_HOME is supported");
		}
		return javaHome;
	}

	public int getJavaHomeRuntimeMajorVersion() {
		return version;
	}

}
