package com.hrapp.hrapp.domain.event;

public class PositionDeletedEvent {
    private final Long positionId;

    public PositionDeletedEvent(Long positionId) {
        this.positionId = positionId;
    }

    public Long getPositionId() {
        return positionId;
    }
}
