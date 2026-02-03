package org.example;

public class Bai4 {
    private String unitId;
    private String name;
    private String description;

    public Bai4(String unitId, String name, String description) {
        this.unitId = unitId;
        this.name = name;
        this.description = description;
    }

    public String getUnitId() { return unitId; }
    public void setUnitId(String unitId) { this.unitId = unitId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}