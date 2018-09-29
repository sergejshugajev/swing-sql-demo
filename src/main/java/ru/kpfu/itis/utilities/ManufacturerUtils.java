package ru.kpfu.itis.utilities;

import ru.kpfu.itis.entities.Manufacturer;

import java.util.LinkedList;
import java.util.List;

public class ManufacturerUtils {

    public static Manufacturer getManufacturer(String country) {
        for (Manufacturer m : Manufacturer.values()) {
            if (m.getCountry().equals(country)) {
                return m;
            }
        }
        return null;
    }

    public static String[] getManufacturers() {
        List<String> values = new LinkedList<>();
        for (Manufacturer m : Manufacturer.values()) {
            values.add(m.getCountry());
        }
        String[] vals = new String[values.size()];
        int i = 0;
        for (String v : values) {
            vals[i] = v;
            i++;
        }
        return vals;
    }
}
