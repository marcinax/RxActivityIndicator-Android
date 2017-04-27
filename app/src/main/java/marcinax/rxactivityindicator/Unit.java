package marcinax.rxactivityindicator;

import android.support.annotation.NonNull;

/**
 * Created by Marcin Gmyz on 27.04.2017.
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
