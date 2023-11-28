import java.util.function.Supplier;

public class TestInstance {

    // For the sake of simplicity, the code is just a supplier that states if the test was successful.
    private final Supplier<Boolean> code;
    private String testName;
    private Boolean success;

    public TestInstance(String testName, Supplier<Boolean> code) {
        this.testName = testName;
        this.code = code;
    }

    public void run() {
        this.success = this.code.get();
    }

    public Supplier<Boolean> getCode() {
        return code;
    }

    public String getTestName() {
        return testName;
    }

    public Boolean getSuccess() {
        return success;
    }

}
