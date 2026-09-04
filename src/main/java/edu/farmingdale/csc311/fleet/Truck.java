package edu.farmingdale.csc311.fleet;

/**
 * A work truck. Its range drops as its payload rating goes up, so it
 * computes range differently from a Car.
 *
 * @author Hassan Khan
 */
public class Truck extends Vehicle {

    /* ------------------------------------------------------------------
     * TODO-07     commit: TODO-07: implement Truck
     *
     * 1. Add a private double field payloadKg.
     * 2. After the super(...) call, check payloadKg (above 0.0, at most
     *    20000.0) and store it.
     * 3. Fill in getPayloadKg() and setPayloadKg(double) with the same check.
     * 4. category()     returns "Truck"
     *    hornSound()    returns "HOOOONK!"
     *    honk()         a truck answers one honk() with two blasts.
     *                   Override it and call honk(2). Do not copy the
     *                   printing code from Vehicle.
     *    rangeInMiles()
     *        loadFactor = 1.0 - Math.min(0.35, payloadKg / 20000.0)
     *        range      = getFuelCapacity() * getFuelType().getMilesPerUnit() * loadFactor
     * 5. toString() returns:
     *
     *      Truck -> <what Vehicle.toString() gives>, payload=3500.0 kg, range=1122.0 mi
     *
     *    Same rules as Car: super.toString(), category(), one decimal.
     * ------------------------------------------------------------------ */

    // Step 1: Add the payload field
    private double payloadKg;


    public Truck(String vin, String make, String model, int year, String color,
                 int wheels, double engineSize, FuelType fuelType,
                 double fuelCapacity, double payloadKg) {

        // Step 2: super(...) MUST stay first
        super(vin, make, model, year, color, wheels, engineSize,
                fuelType, fuelCapacity);

        // Check payloadKg: above 0.0 and at most 20000.0
        if (payloadKg <= 0.0 || payloadKg > 20000.0) {
            throw new IllegalArgumentException("payloadKg: " + payloadKg);
        }

        this.payloadKg = payloadKg;
    }


    // Step 3: Getter
    public double getPayloadKg() {
        return payloadKg;
    }


    // Step 3: Setter
    public void setPayloadKg(double payloadKg) {
        if (payloadKg <= 0.0 || payloadKg > 20000.0) {
            throw new IllegalArgumentException("payloadKg: " + payloadKg);
        }

        this.payloadKg = payloadKg;
    }


    // Step 4: Category
    @Override
    public String category() {
        return "Truck";
    }


    // Step 4: Horn sound
    @Override
    public String hornSound() {
        return "HOOOONK!";
    }


    // Step 4: Truck honks twice
    @Override
    public void honk() {
        honk(2);
    }


    // Step 4: Calculate range based on payload
    @Override
    public double rangeInMiles() {

        double loadFactor =
                1.0 - Math.min(0.35, payloadKg / 20000.0);

        return getFuelCapacity()
                * getFuelType().getMilesPerUnit()
                * loadFactor;
    }


    // Step 5: toString
    @Override
    public String toString() {
        return String.format(
                "%s -> %s, payload=%.1f kg, range=%.1f mi",
                category(),
                super.toString(),
                payloadKg,
                rangeInMiles()
        );
    }
}
