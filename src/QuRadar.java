import model.Fine;
import model.Observation;
import model.Violation;
import rule.RadarRule;

import java.util.*;


/**
 * Core radar engine managing rule execution, fine generation, and violation metrics.
 * 
 * System Architecture:
 * Integrates edge-deployed YOLOv8-Quantum (Q-ViT) hybrid model for multi-object tracking,
 * license plate recognition (ALPR), and CNN-based posture estimation.
 */


public class QuRadar {

    private final List<RadarRule> rules = new ArrayList<>();
    private final List<Fine> issuedFines = new ArrayList<>();
    private final Map<String, Integer> ruleViolationCounts = new LinkedHashMap<>();

    public void addRule(RadarRule rule) {
        rules.add(Objects.requireNonNull(rule, "Rule cannot be null"));
    }

    public Optional<Fine> processObservation(Observation observation) {
        Objects.requireNonNull(observation, "Observation cannot be null");
        List<Violation> violations = new ArrayList<>();

        for (RadarRule rule : rules) {
            Optional<Violation> violationOpt = rule.evaluate(observation);
            if (violationOpt.isPresent()) {
                Violation v = violationOpt.get();
                violations.add(v);

                ruleViolationCounts.put(v.getRuleName(),
                        ruleViolationCounts.getOrDefault(v.getRuleName(), 0) + 1);
            }
        }

        if (!violations.isEmpty()) {
            Fine fine = new Fine(observation.getPlateNumber(), violations);
            issuedFines.add(fine);
            return Optional.of(fine);
        }

        return Optional.empty();
    }

    public Map<String, Double> getAllPossibleFines() {
        Map<String, Double> summary = new LinkedHashMap<>();
        for (Fine fine : issuedFines) {
            summary.put(fine.getPlateNumber(),
                    summary.getOrDefault(fine.getPlateNumber(), 0.0) + fine.getTotalAmount());
        }
        return summary;
    }

    public Map<String, Integer> getViolatedRulesWithCount() {
        return Collections.unmodifiableMap(ruleViolationCounts);
    }
}