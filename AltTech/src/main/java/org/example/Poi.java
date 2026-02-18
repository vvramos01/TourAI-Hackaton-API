package org.example;

import jakarta.persistence.*;

@Entity
@Table(name = "poi")
public class Poi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double lat;
    private double lon;
    private String category;
    private int basePopularity;
    private int avgVisitTimeMin;
    private String description;

    public Poi() {}

    public Poi(String name, double lat, double lon, String category, int basePopularity, int avgVisitTimeMin, String description) {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.category = category;
        this.basePopularity = basePopularity;
        this.avgVisitTimeMin = avgVisitTimeMin;
        this.description = description;
    }

    // getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getLat() { return lat; }
    public void setLat(double lat) { this.lat = lat; }
    public double getLon() { return lon; }
    public void setLon(double lon) { this.lon = lon; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public int getBasePopularity() { return basePopularity; }
    public void setBasePopularity(int basePopularity) { this.basePopularity = basePopularity; }
    public int getAvgVisitTimeMin() { return avgVisitTimeMin; }
    public void setAvgVisitTimeMin(int avgVisitTimeMin) { this.avgVisitTimeMin = avgVisitTimeMin; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
