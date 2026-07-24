package rule;

import model.Observation;
import model.VehicleType;
import model.Violation;

import java.util.Map;
import java.util.Optional;

public final class SpeedLimitRule implements RadarRule {
    private static final String RULE_NAME = "Speed Limit Rule";
    private final Map<VehicleType, Double> speedLimits;
    private final double fee;

    public SpeedLimitRule(Map<VehicleType, Double> speedLimits, double fee) {
        this.speedLimits = Map.copyOf(speedLimits);
        this.fee = fee;
    }

    @Override
    public String getRuleName() {
        return RULE_NAME;
    }

    @Override
    public Optional<Violation> evaluate(Observation observation) {
        Double maxAllowed = speedLimits.get(observation.getVehicleType());
        if (maxAllowed != null && observation.getSpeed() > maxAllowed) {
            String description = String.format("speed of %.0f exceeded max allowed %.0f",
                    observation.getSpeed(), maxAllowed);
            return Optional.of(new Violation(RULE_NAME, description, fee));
        }
        return Optional.empty();
    }
}