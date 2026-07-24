package rule;

import model.Observation;
import model.Violation;
import java.util.Optional;


/**
 * Rule implementation validating driver seatbelt status compliance.
 */

public final class SeatbeltRule implements RadarRule {
    private static final String RULE_NAME = "Seatbelt Fastened Rule";
    private final double fee;

    public SeatbeltRule(double fee) {
        this.fee = fee;
    }

    @Override
    public String getRuleName() {
        return RULE_NAME;
    }

    @Override
    public Optional<Violation> evaluate(Observation observation) {
        if (!observation.isSeatbeltFastened()) {
            return Optional.of(new Violation(
                RULE_NAME,
                "Seatbelt not fastned",
                fee
            ));
        }
        return Optional.empty();
    }
}