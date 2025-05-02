package com.hrapp.hrapp.domain.event;

public class PositionCreatedEvent {
    private final Long positionId;
    private final String title;

    public PositionCreatedEvent(Long positionId, String title) {
        this.positionId = positionId;
        this.title = title;
    }

    public Long getPositionId() {
        return positionId;
    }

    public String getTitle() {
        return title;
    }
}
