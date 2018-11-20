package com.mpoznyak.dto.rest;

import java.util.Objects;

/**
 * Created by Max Poznyak
 * on 11/19/18  at 19:15
 */
public class ShiftDTORest {

    private Long id;
    private String monthStartAt;
    private String monthEndAt;
    private Long timeWeeklyElapsed;
    private Long timeMonthlyElapsed;
    private Boolean weeklyRest;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMonthStartAt() {
        return monthStartAt;
    }

    public void setMonthStartAt(String monthStartAt) {
        this.monthStartAt = monthStartAt;
    }

    public String getMonthEndAt() {
        return monthEndAt;
    }

    public void setMonthEndAt(String monthEndAt) {
        this.monthEndAt = monthEndAt;
    }

    public Long getTimeWeeklyElapsed() {
        return timeWeeklyElapsed;
    }

    public void setTimeWeeklyElapsed(Long timeWeeklyElapsed) {
        this.timeWeeklyElapsed = timeWeeklyElapsed;
    }

    public Long getTimeMonthlyElapsed() {
        return timeMonthlyElapsed;
    }

    public void setTimeMonthlyElapsed(Long timeMonthlyElapsed) {
        this.timeMonthlyElapsed = timeMonthlyElapsed;
    }

    public Boolean getWeeklyRest() {
        return weeklyRest;
    }

    public void setWeeklyRest(Boolean weeklyRest) {
        this.weeklyRest = weeklyRest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShiftDTORest shift = (ShiftDTORest) o;
        return Objects.equals(id, shift.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
