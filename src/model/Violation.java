package quradar.model;
import java.util.Objects;


public final class Violation {
    private final String ruleName;
    private final String description;
    private final double fee;

    public Violation(String ruleName, String description, double fee) {
        this.ruleName = Objects.requireNonNull(ruleName);
        this.description = Objects.requireNonNull(description);
        this.fee = fee;
    }

    public String getRuleName() { return ruleName; }
    public String getDescription() { return description; }
    public double getFee() { return fee; }

    @Override
    public String toString() {
        return String.format("- %s : %.0f EGP", description, fee);
    }
}
