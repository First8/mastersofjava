import java.util.Arrays;
import java.util.Objects;

public class Team {
    private Long id;
    private String name;
    private boolean[] testResults;

    public Team(Long id, String teamName, int numberOfTests) {
        this.id = id;
        this.name = teamName;
        this.testResults = new boolean[numberOfTests];
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean[] getTestResults() {
        return testResults;
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", testsSuccess=" + Arrays.toString(testResults) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(id, team.id) && Objects.equals(name, team.name) && Arrays.equals(testResults, team.testResults);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, name);
        result = 31 * result + Arrays.hashCode(testResults);
        return result;
    }
}
