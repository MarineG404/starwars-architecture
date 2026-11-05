package org.ort.starwars.fleet.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * Fusionne une liste « après » avec une liste d'origine « avant ».
 * Deux éléments sont identiques s'ils ont la même clé.
 */
public class Merge<K, T> {

    private final Function<T, K> keyOf;
    private final Map<K, T> before = new LinkedHashMap<>();
    private final Set<K> matched = new HashSet<>();
    private final Set<K> seen = new HashSet<>();
    private final List<T> saved = new ArrayList<>();

    public Merge(Iterable<T> origin, Function<T, K> keyOf) {
        this.keyOf = keyOf;
        for (T item : origin) {
            before.put(keyOf.apply(item), item);
        }
    }

    /**
     * Ajoute les éléments « après ». Si un élément existe déjà dans l'origine,
     * onExisting(nouveau, ancien) est appelé pour récupérer par exemple son id.
     */
    public void addAll(Iterable<T> items, BiConsumer<T, T> onExisting) {
        for (T item : items) {
            K key = keyOf.apply(item);
            if (!seen.add(key)) {
                continue; // doublon dans la source : on l'ignore
            }
            T existing = before.get(key);
            if (existing != null) {
                onExisting.accept(item, existing);
                matched.add(key);
            }
            saved.add(item);
        }
    }

    /** Éléments à enregistrer : sans id = ajoutés, avec id = mis à jour. */
    public List<T> getSaved() {
        return saved;
    }

    /** Éléments présents avant mais absents de la nouvelle liste. */
    public List<T> getDeleted() {
        List<T> deleted = new ArrayList<>();
        before.forEach((key, item) -> {
            if (!matched.contains(key)) {
                deleted.add(item);
            }
        });
        return deleted;
    }
}