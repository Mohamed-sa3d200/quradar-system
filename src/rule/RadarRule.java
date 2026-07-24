package rule;

import model.Observation;
import model.Violation;
import java.util.Optional;

/**
 * Strategy interface for decoupled traffic compliance rule evaluation.
 */


public interface RadarRule {
    String getRuleName();
    Optional<Violation> evaluate(Observation observation);
}
