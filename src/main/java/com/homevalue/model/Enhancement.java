package com.homevalue.model;
import jakarta.persistence.*;

@Entity
@Table(name = "enhancements")
public class Enhancement {
    @Id
    @Column(nullable = false, unique = true)
    private String id;
    private String title;
    private String shortDesc;
    private String category;
    private String costRange;
    private String roi;
    private String duration;
    private String impact;
    @Column(columnDefinition = "TEXT")
    private String longDescription;
    @Column(columnDefinition = "TEXT")
    private String materials;
    @Column(columnDefinition = "TEXT")
    private String steps;
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getShortDesc() { return shortDesc; }
    public void setShortDesc(String shortDesc) { this.shortDesc = shortDesc; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getCostRange() { return costRange; }
    public void setCostRange(String costRange) { this.costRange = costRange; }
    public String getRoi() { return roi; }
    public void setRoi(String roi) { this.roi = roi; }
    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }
    public String getImpact() { return impact; }
    public void setImpact(String impact) { this.impact = impact; }
    public String getLongDescription() { return longDescription; }
    public void setLongDescription(String longDescription) { this.longDescription = longDescription; }
    public String getMaterials() { return materials; }
    public void setMaterials(String materials) { this.materials = materials; }
    public String getSteps() { return steps; }
    public void setSteps(String steps) { this.steps = steps; }
}