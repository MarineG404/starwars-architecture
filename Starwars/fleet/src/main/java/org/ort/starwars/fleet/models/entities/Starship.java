package org.ort.starwars.fleet.models.entities;

import org.apache.logging.log4j.util.Strings;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.ort.starwars.fleet.models.enums.StarshipType;

import jakarta.persistence.EnumType;
import jakarta.persistence.GenerationType;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Starship {
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    // Caratéristiques
    @Column(unique = true)
    private String name;
    @Enumerated(EnumType.STRING)
    private StarshipType category;
    @Column 
    private int length;
    @Column 
    private int crew;
    @Column 
    private int passengers;
    @Column 
    private int count;
    @Column 
    private String pilot;

    public String getKey() {
        return Strings.isBlank(pilot) ? name : name + " " + pilot;
    }

    public double getSpeed() {
        double l = this.length;
        return l > 0 ? 10000.0 / l : 0;
    }

    public double getCost() {
        double l = this.length;
        return l * l;
    }
}
