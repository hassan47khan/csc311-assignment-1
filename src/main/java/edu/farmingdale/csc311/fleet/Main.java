package edu.farmingdale.csc311.fleet;

/**
 * Driver. This is the only class that prints a report.
 *
 * @author YOUR NAME HERE
 */
public class Main {

    /* ------------------------------------------------------------------
     * TODO-10     commit: TODO-10: build the fleet demo in Main
     *
     * Print exactly the output listed in the assignment handout. Break the
     * work into private static helper methods, one per section. A 120 line
     * main() loses points.
     *
     * 1. Build a Fleet named "Farmingdale Motor Pool" and add these five in
     *    this order. Every value matters.
     *
     *    Car   1HGCM82633A004352 Honda  Accord  2023 Blue   4 2.0 GASOLINE 15.8 4 doors
     *    Car   5YJ3E1EA7PF123456 Tesla  Model 3 2024 Red    4 0.0 ELECTRIC 75.0 4 doors
     *    Car   JTDKARFU2J3061234 Toyota Prius   2020 Silver 4 1.8 HYBRID   11.3 5 doors
     *    Truck 1FT8W3BT5MEC12345 Ford   F-350   2021 White  6 6.7 DIESEL   40.0 3500.0 kg
     *    Truck 3C6UR5DL9JG123456 Ram    2500    2019 Black  4 6.4 GASOLINE 31.0 1800.0 kg
     *
     * 2. Inventory: loop over sortedByYear() and println each one. Declare
     *    the loop variable as Vehicle, not Car and not Truck. One loop
     *    prints both kinds. No instanceof anywhere in this file.
     *
     * 3. Sound check: loop over sortedByYear() again with the loop variable
     *    declared as Honkable and call honk(). Then find the Accord with
     *    findByVin and honk 3 times.
     *
     * 4. Report, using these exact printf formats:
     *        "%-20s: %d%n"                     vehicle count
     *        "%-20s: %.1f L%n"                 average engine size
     *        "%-20s: %d %s %s (%.1f mi)%n"     longest range
     *        "  %-9s: %d%n"                    one line per fuel
     *    Get the fuel lines by looping over FuelType.values() and calling
     *    countWithFuelType.
     *
     * 5. Guard rails, first three lines with "%-23s: %s%n":
     *        a. add the Accord a second time, print what add() returned
     *        b. removeByVin the Prius, print what it returned
     *        c. print size() afterwards
     *    Then three separate try/catch blocks, each catching
     *    IllegalArgumentException and printing "Caught: " + e.getMessage():
     *        d. build a Car with fuel ELECTRIC and engine size 2.0
     *        e. FuelType.fromLabel("Steam")
     *        f. honk(0) on any vehicle
     *    Catch IllegalArgumentException, not Exception. No empty catch.
     * ------------------------------------------------------------------ */

    public static void main(String[] args) {

        Fleet fleet = buildFleet();

        printInventory(fleet);
        soundCheck(fleet);
        printReport(fleet);
        guardRails(fleet);
    }


    // ================================================================
    // Section 1: Build the Fleet
    // ================================================================

    private static Fleet buildFleet() {

        Fleet fleet = new Fleet("Farmingdale Motor Pool");

        fleet.add(new Car(
                "1HGCM82633A004352",
                "Honda",
                "Accord",
                2023,
                "Blue",
                4,
                2.0,
                FuelType.GASOLINE,
                15.8,
                4
        ));

        fleet.add(new Car(
                "5YJ3E1EA7PF123456",
                "Tesla",
                "Model 3",
                2024,
                "Red",
                4,
                0.0,
                FuelType.ELECTRIC,
                75.0,
                4
        ));

        fleet.add(new Car(
                "JTDKARFU2J3061234",
                "Toyota",
                "Prius",
                2020,
                "Silver",
                4,
                1.8,
                FuelType.HYBRID,
                11.3,
                5
        ));

        fleet.add(new Truck(
                "1FT8W3BT5MEC12345",
                "Ford",
                "F-350",
                2021,
                "White",
                6,
                6.7,
                FuelType.DIESEL,
                40.0,
                3500.0
        ));

        fleet.add(new Truck(
                "3C6UR5DL9JG123456",
                "Ram",
                "2500",
                2019,
                "Black",
                4,
                6.4,
                FuelType.GASOLINE,
                31.0,
                1800.0
        ));

        return fleet;
    }


    // ================================================================
    // Section 2: Inventory
    // ================================================================

    private static void printInventory(Fleet fleet) {

        for (Vehicle vehicle : fleet.sortedByYear()) {
            System.out.println(vehicle);
        }
    }


    // ================================================================
    // Section 3: Sound Check
    // ================================================================

    private static void soundCheck(Fleet fleet) {

        for (Honkable vehicle : fleet.sortedByYear()) {
            vehicle.honk();
        }

        Vehicle accord = fleet.findByVin("1HGCM82633A004352");

        accord.honk(3);
    }


    // ================================================================
    // Section 4: Report
    // ================================================================

    private static void printReport(Fleet fleet) {

        System.out.printf(
                "%-20s: %d%n",
                "Vehicle count",
                fleet.size()
        );

        System.out.printf(
                "%-20s: %.1f L%n",
                "Average engine size",
                fleet.averageEngineSize()
        );

        Vehicle longest = fleet.longestRange();

        System.out.printf(
                "%-20s: %d %s %s (%.1f mi)%n",
                "Longest range",
                longest.getYear(),
                longest.getMake(),
                longest.getModel(),
                longest.rangeInMiles()
        );

        for (FuelType fuel : FuelType.values()) {

            System.out.printf(
                    "  %-9s: %d%n",
                    fuel.getLabel(),
                    fleet.countWithFuelType(fuel)
            );
        }
    }


    // ================================================================
    // Section 5: Guard Rails
    // ================================================================

    private static void guardRails(Fleet fleet) {

        // a. Try adding the Accord a second time
        Vehicle accord = fleet.findByVin("1HGCM82633A004352");

        System.out.printf(
                "%-23s: %s%n",
                "Duplicate add",
                fleet.add(accord)
        );


        // b. Remove the Prius
        System.out.printf(
                "%-23s: %s%n",
                "Remove Prius",
                fleet.removeByVin("JTDKARFU2J3061234")
        );


        // c. Print size afterwards
        System.out.printf(
                "%-23s: %s%n",
                "Size after removal",
                fleet.size()
        );


        // d. Electric vehicle with an engine
        try {

            new Car(
                    "AAAAAAAAAAAAAAAAA",
                    "Test",
                    "Electric",
                    2024,
                    "Black",
                    4,
                    2.0,
                    FuelType.ELECTRIC,
                    75.0,
                    4
            );

        } catch (IllegalArgumentException e) {

            System.out.println("Caught: " + e.getMessage());
        }


        // e. Invalid FuelType label
        try {

            FuelType.fromLabel("Steam");

        } catch (IllegalArgumentException e) {

            System.out.println("Caught: " + e.getMessage());
        }


        // f. Invalid honk count
        try {

            fleet.findByVin("1HGCM82633A004352").honk(0);

        } catch (IllegalArgumentException e) {

            System.out.println("Caught: " + e.getMessage());
        }
    }