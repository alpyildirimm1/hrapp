package com.hrapp.hrapp.application.service;

import com.hrapp.hrapp.domain.event.PositionDeletedEvent;
import com.hrapp.hrapp.domain.model.Position;
import com.hrapp.hrapp.domain.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PositionService {

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;


    public List<Position> getAllPositions() {
        return positionRepository.findAll();
    }

    public Position getPositionById(Long id) {
        return positionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Position not found"));
    }

    public Position getPositionByTitle(String title) {
        return positionRepository.findByTitle(title);
    }
    public Optional<Position> findById(Long id) {
        return positionRepository.findById(id);
    }

    public List<Position> getPositionsByExperienceLevel(String level) {
        return positionRepository.findByExperienceLevel(level);
    }

    public Position createPosition(Position position) {
        boolean isNew = (position.getPositionId() == null);
        Position saved = positionRepository.save(position);

        if (isNew) {
            saved.markCreatedEvent();
            saved.getDomainEvents().forEach(eventPublisher::publishEvent);
            saved.clearDomainEvents();
        }
        return saved;
    }


    public Position updatePosition(Long id, Position updated) {
        Position existing = getPositionById(id);
        existing.setTitle(updated.getTitle());
        existing.setDescription(updated.getDescription());
        existing.setResponsibilities(updated.getResponsibilities());
        existing.setRequiredSkills(updated.getRequiredSkills());
        existing.setExperienceLevel(updated.getExperienceLevel());
        existing.setEducationRequirement(updated.getEducationRequirement());
        existing.setPromotionPath(updated.getPromotionPath());
        return positionRepository.save(existing);
    }

    public void deletePosition(Long id) {
        positionRepository.findById(id).ifPresent(position -> {
            position.markDeletedEvent();
            eventPublisher.publishEvent(new PositionDeletedEvent(position.getPositionId()));
            position.clearDomainEvents();
            positionRepository.delete(position);
        });
    }

}
