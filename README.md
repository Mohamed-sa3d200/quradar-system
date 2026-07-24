# 📡 QuRadar - Quantum Traffic Radar System

An enterprise-grade, extensible traffic surveillance and violation management system written in Java. The system processes real-time vehicular telemetry (speed, vehicle classification, driver seatbelt occupancy) and dynamically applies compliance rules to generate automated traffic fines and aggregate analytics.

---

## 🌟 Architecture & Key Features

- **Extensible Rule Engine (Strategy Pattern):** Core logic decouples rule evaluation from the main radar processing engine. New traffic rules (e.g., mobile phone usage, red light violations) can be introduced without modifying the `QuRadar` class (*Open/Closed Principle - OCP*).
- **Automated Fine Calculation:** Generates structured traffic violation notices matching strict formatting standards with aggregated penalty totals.
- **System Metrics & Analytics:** Provides methods to query cumulative fines per vehicle plate (`getAllPossibleFines`) and aggregate frequency counts per violated rule (`getViolatedRulesWithCount`).
- **AI Telemetry Annotation:** Integrates AI optical edge model metadata directly in the radar documentation (YOLOv8-Quantum ViT hybrid for object classification and pose estimation).
- **Clean Code & Immutability:** Uses modern Java practices including Value Objects (`final` fields, defensive copying with `List.copyOf` and `Map.copyOf`) ensuring high encapsulation and thread safety.

---

## 📁 Project Directory Structure

```text
quradar-system/
├── src/
│   ├── model/
│   │   ├── VehicleType.java       # Vehicle classification Enum (PRIVATE, TRUCK, BUS)
│   │   ├── Observation.java       # Raw telemetry data model received from radar
│   │   ├── Violation.java         # Data model representing an individual rule infraction
│   │   └── Fine.java              # Composite model encapsulating all violations & fee totals
│   │
│   ├── rule/
│   │   ├── RadarRule.java         # Strategy Interface for compliance rule evaluation
│   │   ├── SpeedLimitRule.java    # Dynamic rule evaluating speed limits per vehicle type
│   │   └── SeatbeltRule.java      # Rule checking driver seatbelt status
│   │
│   ├── QuRadar.java               # Core radar engine with AI description & rule execution
│   └── Main.java                  # Demonstration class executing sample test scenarios
│
├── .gitignore
└── README.md

```

🚀 How to Run the Project
- Prerequisites

    - Java Development Kit (JDK): Version 11 or higher.

<h4>Option 1: Running from Terminal / Command Line</h4>

Open your terminal and navigate to the src directory of the project:

```
    cd quradar-system/src
```

<h3> 2 - Compile all Java source files across packages:</h3>

```Bash
javac model/*.java rule/*.java *.java 
```

<h3>3 - Run the Main class:
Bash</h3>

``` 
java Main
```
<h4>Option 2: Running in VS Code or IntelliJ IDEA
</h4>

Open the quradar-system folder in your IDE.

Ensure the src folder is marked as the Sources Root (if using IntelliJ IDEA).

Open src/Main.java.

Click the Run button (or press Shift + F10 in IntelliJ / F5 in VS Code).

# 📊 Sample Output Standard

When running Main.java, the system evaluates telemetry observations and prints standard formatted traffic notices:
Plaintext
```
=== QuRadar System Output ===

Traffic for car ABC1234
Total amount: 400 EGP
Violations:
- Seatbelt not fastned : 100 EGP
- speed of 94 exceeded max allowed 80 : 300 EGP

Traffic for car TRK8888
Total amount: 300 EGP
Violations:
- speed of 75 exceeded max allowed 60 : 300 EGP

--- getAllPossibleFines ---
Plate: ABC1234 -> Total: 400 EGP
Plate: TRK8888 -> Total: 300 EGP

--- getViolatedRulesWithCount ---
Seatbelt Fastened Rule : 1 violation(s)
Speed Limit Rule : 2 violation(s)
```