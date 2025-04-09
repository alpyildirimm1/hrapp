package com.hrapp.hrapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "positions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long positionId;


    private String title;
    private String description;
    private String responsibilities;
    private String requiredSkills;
    private String experienceLevel;
    private String educationRequirement;
    private String promotionPath;

    @OneToMany(mappedBy = "position")
    @JsonManagedReference(value = "employee-position")
    private List<Employee> employees;


/*
    public Position() {
    }

    // --- Tüm alanları içeren constructor ---
    public Position(Long positionId, String title, String description, String responsibilities,
                    String requiredSkills, String experienceLevel, String educationRequirement,
                    String promotionPath, List<Employee> employees) {
        this.positionId = positionId;
        this.title = title;
        this.description = description;
        this.responsibilities = responsibilities;
        this.requiredSkills = requiredSkills;
        this.experienceLevel = experienceLevel;
        this.educationRequirement = educationRequirement;
        this.promotionPath = promotionPath;
        this.employees = employees;
    }
*/
    // --- Getter & Setter metotları ---

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }

    public String getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(String requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public String getExperienceLevel() {
        return experienceLevel;
    }

    public void setExperienceLevel(String experienceLevel) {
        this.experienceLevel = experienceLevel;
    }

    public String getEducationRequirement() {
        return educationRequirement;
    }

    public void setEducationRequirement(String educationRequirement) {
        this.educationRequirement = educationRequirement;
    }

    public String getPromotionPath() {
        return promotionPath;
    }

    public void setPromotionPath(String promotionPath) {
        this.promotionPath = promotionPath;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
