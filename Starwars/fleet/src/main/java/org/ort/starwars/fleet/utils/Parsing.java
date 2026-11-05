package org.ort.starwars.fleet.utils;

import java.net.URI;
import java.util.regex.Pattern;

public class Parsing {

    /**
     * Extrait le chemin relatif d'une URL par rapport à la base_url fournie.
     */
    public static String getEndpoint(String baseUrl, String url) {
        if (baseUrl == null || url == null) {
            return null;
        }
        URI base = URI.create(baseUrl);
        URI target = URI.create(url);

        String basePath = base.getRawPath() == null ? "" : base.getRawPath();
        String path = target.getRawPath() == null ? "" : target.getRawPath();

        if (!path.startsWith(basePath)) {
            return null;
        }
        String relative = path.substring(basePath.length());
        return target.getRawQuery() == null
                ? relative
                : relative + "?" + target.getRawQuery();
    }

    /**
     * Lit un numérique sans tenir compte d'un séparateur de milliers
     * ',' '.' ou l'espace.
     */
    public static int getInt(String value, int fallback) {
        if (value == null) {
            return fallback;
        }
        String digits = value.replaceAll("[,.\\s\\u00A0]", "");
        try {
            return Integer.parseInt(digits);
        } catch (NumberFormatException e) {
            return fallback; // "unknown", "n/a", ""...
        }
    }

    /**
     * Extrait les quantités encodées dans un intervalle de la forme xxx-yyy.
     */
    public static int[] getRange(String value, int fallback, String sep) {
        if (value == null) {
            return new int[] { fallback };
        }
        String[] parts = value.split(Pattern.quote(sep));
        int[] result = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            result[i] = getInt(parts[i].trim(), fallback);
        }
        return result;
    }

    public static int[] getRange(String value, int fallback) {
        return getRange(value, fallback, "-");
    }

    public static int[] getRange(String value) {
        return getRange(value, -1, "-");
    }
}