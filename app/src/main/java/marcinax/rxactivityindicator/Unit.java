package marcinax.rxactivityindicator;

import android.support.annotation.NonNull;

/**
 * Class represents void. Helpful when it is necessary to use empty value with observable.
 */
public class Unit implements Comparable<Unit> {
    private static final Unit defaultUnit = new Unit();

    @Override
    public int compareTo(@NonNull Unit o) {
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Unit;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    public static Unit getDefault() {
        return defaultUnit;
    }
}
