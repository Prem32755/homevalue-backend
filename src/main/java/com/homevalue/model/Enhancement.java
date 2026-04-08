package com.homevalue.model;
import jakarta.persistence.*;
@Entity
@Table(name="enhancements")
public class Enhancement {
    @Id @Column(nullable=false,unique=true) private String id;
    private String title,shortDesc,category,costRange,roi,duration,impact;
    @Column(columnDefinition="TEXT") private String longDescription;
    @Column(columnDefinition="TEXT") private String materials;
    @Column(columnDefinition="TEXT") private String steps;
    public String getId(){return id;} public void setId(String id){this.id=id;}
    public String getTitle(){return title;} public void setTitle(String t){this.title=t;}
    public String getShortDesc(){return shortDesc;} public void setShortDesc(String s){this.shortDesc=s;}
    public String getCategory(){return category;} public void setCategory(String c){this.category=c;}
    public String getCostRange(){return costRange;} public void setCostRange(String c){this.costRange=c;}
    public String getRoi(){return roi;} public void setRoi(String r){this.roi=r;}
    public String getDuration(){return duration;} public void setDuration(String d){this.duration=d;}
    public String getImpact(){return impact;} public void setImpact(String i){this.impact=i;}
    public String getLongDescription(){return longDescription;} public void setLongDescription(String l){this.longDescription=l;}
    public String getMaterials(){return materials;} public void setMaterials(String m){this.materials=m;}
    public String getSteps(){return steps;} public void setSteps(String s){this.steps=s;}
}