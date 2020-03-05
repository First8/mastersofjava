import java.nio.file.Path;

public class JavaVersion {

	private final Integer version;
	private final String name;
	private final Path compiler;
	private final Path runtime;

	public JavaVersion(Integer version, String name, Path compiler, Path runtime) {
		super();
		this.version = version;
		this.name = name;
		this.compiler = compiler;
		this.runtime = runtime;
	}
	
	public Integer getVersion() {
		return version;
	}
	public String getName() {
		return name;
	}
	public Path getCompiler() {
		return compiler;
	}
	public Path getRuntime() {
		return runtime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((compiler == null) ? 0 : compiler.hashCode());
		result = prime * result + ((runtime == null) ? 0 : runtime.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JavaVersion other = (JavaVersion) obj;
		if (compiler == null) {
			if (other.compiler != null)
				return false;
		} else if (!compiler.equals(other.compiler))
			return false;
		if (runtime == null) {
			if (other.runtime != null)
				return false;
		} else if (!runtime.equals(other.runtime))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "JavaVersion [version=" + version + ", name=" + name + ", compiler=" + compiler + ", runtime=" + runtime
				+ "]";
	}

	
}