package model;

import java.util.List;
import java.util.Objects;

public final class Fine {
    private final String plateNumber;
    private final List<Violation> violations;

    public Fine(String plateNumber, List<Violation> violations) {
        this.plateNumber = Objects.requireNonNull(plateNumber);
        this.violations = List.copyOf(violations);
    }

    public String getPlateNumber() { return plateNumber; }
    public List<Violation> getViolations() { return violations; }

    public double getTotalAmount() {
        return violations.stream().mapToDouble(Violation::getFee).sum();
    }

    public String printFine() {
        StringBuilder sb = new StringBuilder();
        sb.append("Traffic for car ").append(plateNumber).append("\n");
        sb.append("Total amount: ").append((int) getTotalAmount()).append(" EGP\n");
        sb.append("Violations:\n");
        for (Violation v : violations) {
            sb.append(v.toString()).append("\n");
        }
        return sb.toString().trim();
    }
}
