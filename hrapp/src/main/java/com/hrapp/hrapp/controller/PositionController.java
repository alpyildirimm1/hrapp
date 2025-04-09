package com.hrapp.hrapp.controller;

import com.hrapp.hrapp.model.Position;
import com.hrapp.hrapp.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/positions")
public class PositionController {

    @Autowired
    private PositionRepository positionRepository;

    @PreAuthorize("hasAnyRole('ADMIN', 'HR', 'EMPLOYEE')")
    @GetMapping
    public List<Position> getAllPositions() {
        return positionRepository.findAll();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'HR', 'EMPLOYEE')")
    @GetMapping("/{id}")
    public Position getPositionById(@PathVariable Long id) {
        return positionRepository.findById(id).orElse(null);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'HR', 'EMPLOYEE')")
    @GetMapping("/title/{title}")
    public Position getPositionByTitle(@PathVariable String title) {
        return positionRepository.findByTitle(title);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'HR', 'EMPLOYEE')")
    @GetMapping("/level/{experienceLevel}")
    public List<Position> getByExperienceLevel(@PathVariable String experienceLevel) {
        return positionRepository.findByExperienceLevel(experienceLevel);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    @PostMapping
    public Position createPosition(@RequestBody Position position) {
        return positionRepository.save(position);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    @PutMapping("/{id}")
    public Position updatePosition(@PathVariable Long id, @RequestBody Position updatedPosition) {
        return positionRepository.findById(id).map(position -> {
            position.setTitle(updatedPosition.getTitle());
            position.setDescription(updatedPosition.getDescription());
            position.setResponsibilities(updatedPosition.getResponsibilities());
            position.setRequiredSkills(updatedPosition.getRequiredSkills());
            position.setExperienceLevel(updatedPosition.getExperienceLevel());
            position.setEducationRequirement(updatedPosition.getEducationRequirement());
            position.setPromotionPath(updatedPosition.getPromotionPath());
            return positionRepository.save(position);
        }).orElse(null);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deletePosition(@PathVariable Long id) {
        positionRepository.deleteById(id);
    }
}
