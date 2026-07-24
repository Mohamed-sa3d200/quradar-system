package rule;

import model.Observation;
import model.Violation;
import java.util.Optional;

public interface RadarRule {
    String getRuleName();
    Optional<Violation> evaluate(Observation observation);
}
