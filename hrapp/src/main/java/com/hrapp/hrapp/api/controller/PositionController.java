package com.hrapp.hrapp.api.controller;

import com.hrapp.hrapp.application.service.PositionService;
import com.hrapp.hrapp.domain.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/positions")
public class PositionController {

    @Autowired
    private PositionService positionService;

    @PreAuthorize("hasAnyRole('ADMIN', 'HR', 'EMPLOYEE')")
    @GetMapping
    public List<Position> getAllPositions() {
        return positionService.getAllPositions();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'HR', 'EMPLOYEE')")
    @GetMapping("/{id}")
    public Position getPositionById(@PathVariable Long id) {
        return positionService.getPositionById(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'HR', 'EMPLOYEE')")
    @GetMapping("/title/{title}")
    public Position getPositionByTitle(@PathVariable String title) {
        return positionService.getPositionByTitle(title);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'HR', 'EMPLOYEE')")
    @GetMapping("/level/{experienceLevel}")
    public List<Position> getByExperienceLevel(@PathVariable String experienceLevel) {
        return positionService.getPositionsByExperienceLevel(experienceLevel);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    @PostMapping
    public Position createPosition(@RequestBody Position position) {
        return positionService.createPosition(position);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    @PutMapping("/{id}")
    public Position updatePosition(@PathVariable Long id, @RequestBody Position updatedPosition) {
        return positionService.updatePosition(id, updatedPosition);
    }

    @PreAuthorize("hasAnyRole('ADMIN','HR')")
    @DeleteMapping("/{id}")
    public void deletePosition(@PathVariable Long id) {
        positionService.deletePosition(id);
    }
}
