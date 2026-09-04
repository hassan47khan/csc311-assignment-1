package edu.farmingdale.csc311.fleet;

/**
 * A named group of vehicles stored in a plain array.
 * No ArrayList, no HashMap. Arrays and loops only.
 *
 * @author YOUR NAME HERE
 */
public class Fleet {

    public static final int MAX_VEHICLES = 25;

    /* ------------------------------------------------------------------
     * TODO-08     commit: TODO-08: implement Fleet storage
     *
     * 1. Add three private fields:
     *        name        String, final
     *        vehicles    Vehicle[], final, sized MAX_VEHICLES
     *        count       int, how many slots are actually used
     *
     * 2. The constructor checks name (not null, not blank) and trims it.
     *
     * 3. Methods:
     *
     *    contains(Vehicle v)
     *        loop over the used slots and return true if one equals v.
     *        Use the equals you wrote in TODO-05, not ==.
     *
     *    add(Vehicle v)
     *        null argument           throw IllegalArgumentException
     *        already in the fleet    return false, store nothing
     *        array full              return false
     *        otherwise               store at index count, count++, return true
     *
     *    removeByVin(String vin)
     *        find the slot whose VIN matches, ignoring case. Shift every
     *        later element one place left, null out the old last slot,
     *        count--, return true. Return false when nothing matched or
     *        the vin was null or blank.
     *
     *    findByVin(String vin)
     *        return the matching Vehicle, ignoring case, or null.
     *
     *    size()
     *        return count.
     *
     *    toArray()
     *        return a NEW array of length count holding the vehicles in
     *        insertion order. Returning the internal array lets a caller
     *        overwrite your slots, so copy it.
     * ------------------------------------------------------------------ */

    // Step 1: Add the three fields
    private final String name;
    private final Vehicle[] vehicles;
    private int count;


    // Step 2: Constructor
    public Fleet(String name) {

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("name: " + name);
        }

        this.name = name.trim();
        this.vehicles = new Vehicle[MAX_VEHICLES];
        this.count = 0;
    }


    public String getName() {
        return name;
    }


    // Step 3: contains()
    public boolean contains(Vehicle vehicle) {

        for (int i = 0; i < count; i++) {
            if (vehicles[i].equals(vehicle)) {
                return true;
            }
        }

        return false;
    }


    // Step 4: add()
    public boolean add(Vehicle vehicle) {

        if (vehicle == null) {
            throw new IllegalArgumentException("vehicle: null");
        }

        if (contains(vehicle)) {
            return false;
        }

        if (count == MAX_VEHICLES) {
            return false;
        }

        vehicles[count] = vehicle;
        count++;

        return true;
    }


    // Step 5: removeByVin()
    public boolean removeByVin(String vin) {

        if (vin == null || vin.trim().isEmpty()) {
            return false;
        }

        vin = vin.trim();

        for (int i = 0; i < count; i++) {

            if (vehicles[i].getVin().equalsIgnoreCase(vin)) {

                // Shift everything after the removed vehicle left
                for (int j = i; j < count - 1; j++) {
                    vehicles[j] = vehicles[j + 1];
                }

                // Remove the duplicate reference at the end
                vehicles[count - 1] = null;

                count--;

                return true;
            }
        }

        return false;
    }


    // Step 6: findByVin()
    public Vehicle findByVin(String vin) {

        if (vin == null || vin.trim().isEmpty()) {
            return null;
        }

        vin = vin.trim();

        for (int i = 0; i < count; i++) {

            if (vehicles[i].getVin().equalsIgnoreCase(vin)) {
                return vehicles[i];
            }
        }

        return null;
    }


    // Step 7: size()
    public int size() {
        return count;
    }


    // Step 8: toArray()
    public Vehicle[] toArray() {

        Vehicle[] result = new Vehicle[count];

        for (int i = 0; i < count; i++) {
            result[i] = vehicles[i];
        }

        return result;
    }
    /* ------------------------------------------------------------------
     * TODO-09     commit: TODO-09: implement Fleet reports
     *
     * None of these may reorder or change the internal array. Start from
     * toArray() when you need a different order.
     *
     *    sortedByYear()
     *        a new array ordered by year, oldest first. When two years
     *        match, order by make A to Z ignoring case
     *        (String.compareToIgnoreCase). Write the sort yourself:
     *        selection sort or insertion sort, your choice. No Arrays.sort,
     *        no Comparator.
     *
     *    countWithFuelType(FuelType fuel)
     *        how many vehicles use that fuel.
     *
     *    averageEngineSize()
     *        average engine size over the vehicles whose fuel type has an
     *        engine. Electrics are left out, otherwise their 0.0 drags the
     *        number down and it means nothing. Return 0.0 when the count is
     *        zero, and watch the division.
     *
     *    longestRange()
     *        the vehicle with the largest rangeInMiles(), or null when the
     *        fleet is empty. On a tie keep the one added first. Note that
     *        this compares cars against trucks without a single if about
     *        the type: rangeInMiles() already knows which formula to run.
     * ------------------------------------------------------------------ */

    public Vehicle[] sortedByYear() {
        throw new UnsupportedOperationException("TODO-09");
    }

    public int countWithFuelType(FuelType fuel) {
        throw new UnsupportedOperationException("TODO-09");
    }

    public double averageEngineSize() {
        throw new UnsupportedOperationException("TODO-09");
    }

    public Vehicle longestRange() {
        throw new UnsupportedOperationException("TODO-09");
    }
}
