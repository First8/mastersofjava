import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

public class JavaVersionSelector {
	
	private final Environment env;
	private Collection<JavaVersion> javaVersions;

	public JavaVersionSelector(Environment env, Collection<JavaVersion> javaVersions) {
		this.env = env;
		this.javaVersions = javaVersions;
		
	}

	public JavaVersion getJavaVersion(Integer version) {
		return javaVersions.stream()
				.filter( this::isAvailable)
				.filter( jv -> jv.getVersion() >= version)
				.findFirst()
				.orElse(javaHomeFallback(version));
	}
	
	private boolean isAvailable(JavaVersion javaVersion ) {
		return env.exists(javaVersion.getCompiler()) &&
				env.exists(javaVersion.getRuntime());
	}

	private JavaVersion javaHomeFallback(Integer version) {
		String javaHome = env.getSystemEnv("JAVA_HOME");
		if(javaHome!=null && !javaHome.trim().isEmpty()) {
			Path compiler = Paths.get(javaHome, "bin", "javac");
			Path runtime = Paths.get(javaHome,"bin","java");
			String name = "fallback";
			int javaHomeVersion = env.getJavaHomeRuntimeMajorVersion();
			JavaVersion v = new JavaVersion(javaHomeVersion, name, compiler, runtime);
			return v;
		} else {
			throw new IllegalArgumentException("No java version defined and no JAVA_HOME specified, cannot run without a javac/java...");
		}
	}

}
