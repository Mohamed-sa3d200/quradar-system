import model.Fine;
import model.Observation;
import model.VehicleType;
import rule.SeatbeltRule;
import rule.SpeedLimitRule;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;


/**
 * Application entry point demonstrating QuRadar telemetry processing and fine generation.
 */


public class Main {
    public static void main(String[] args) {
        QuRadar radar = new QuRadar();

        
        Map<VehicleType, Double> speedLimits = Map.of(
            VehicleType.TRUCK, 60.0,
            VehicleType.PRIVATE, 80.0,
            VehicleType.BUS, 70.0
        );

        
        radar.addRule(new SeatbeltRule(100.0));
        radar.addRule(new SpeedLimitRule(speedLimits, 300.0));

        System.out.println("=== QuRadar System Output ===\n");

        
        Observation obs1 = new Observation("ABC1234", LocalDateTime.now(), VehicleType.PRIVATE, 94.0, false);
        Optional<Fine> fine1 = radar.processObservation(obs1);

        fine1.ifPresent(fine -> System.out.println(fine.printFine()));
        System.out.println();

        
        Observation obs2 = new Observation("TRK8888", LocalDateTime.now(), VehicleType.TRUCK, 75.0, true);
        Optional<Fine> fine2 = radar.processObservation(obs2);

        fine2.ifPresent(fine -> System.out.println(fine.printFine()));
        System.out.println();

        
        Observation obs3 = new Observation("OK9999", LocalDateTime.now(), VehicleType.PRIVATE, 70.0, true);
        radar.processObservation(obs3);

        
        System.out.println("--- getAllPossibleFines ---");
        Map<String, Double> allFines = radar.getAllPossibleFines();
        allFines.forEach((plate, total) ->
            System.out.println("Plate: " + plate + " -> Total: " + total.intValue() + " EGP"));

        
        System.out.println("\n--- getViolatedRulesWithCount ---");
        Map<String, Integer> ruleCounts = radar.getViolatedRulesWithCount();
        ruleCounts.forEach((rule, count) ->
            System.out.println(rule + " : " + count + " violation(s)"));
    }
}