package quradar.model;
import java.time.LocalDateTime;
import java.util.Objects;


public final class Observation {
    private final String plateNumber;
    private final LocalDateTime date;
    private final VehicleType vehicleType;
    private final double speed;
    private final boolean seatbeltFastened;


    public Observation(String plateNumber, LocalDateTime date, VehicleType vehicleType, double speed, boolean seatbeltFastened) {
            this.plateNumber = Objects.requireNonNull(plateNumber, "Plate number cannot be null");
            this.date = Objects.requireNonNull(date, "Date cannot be null");
            this.vehicleType = Objects.requireNonNull(vehicleType, "Vehicle type cannot be null");
            this.speed = speed;
            this.seatbeltFastened = seatbeltFastened;
        }

    public String getPlateNumber() { return plateNumber; }
    public LocalDateTime getDate() { return date; }
    public VehicleType getVehicleType() { return vehicleType; }
    public double getSpeed() { return speed; }
    public boolean isSeatbeltFastened() { return seatbeltFastened; }
}