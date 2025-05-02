package com.hrapp.hrapp.domain.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hrapp.hrapp.domain.event.PositionCreatedEvent;
import com.hrapp.hrapp.domain.event.PositionDeletedEvent;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
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


    @Transient
    private final List<Object> domainEvents = new ArrayList<>();

    public void markCreatedEvent() {
        domainEvents.add(new PositionCreatedEvent(this.positionId, this.title));
    }

    public void markDeletedEvent() {
        domainEvents.add(new PositionDeletedEvent(this.positionId));
    }

    public List<Object> getDomainEvents() {
        return domainEvents;
    }

    public void clearDomainEvents() {
        domainEvents.clear();
    }

}
