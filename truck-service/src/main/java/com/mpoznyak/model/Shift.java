package com.mpoznyak.model;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Max Poznyak
 * on 27/10/2018  at 13:47
 */

@Entity
public class Shift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "month_start_at")
    private LocalDateTime monthStartAt;

    @Column(name = "month_end_at")
    private LocalDateTime monthEndAt;

    @Column(name = "time_weekly_elapsed")
    private Integer timeWeeklyElapsed = 0;

    @Column(name = "week_counter")
    private Integer weekCounter = 1;

    @Column(name = "time_monthly_elapsed")
    private Long timeMonthlyElapsed = 0L;

    @Column(name = "weekly_rest")
    private Boolean weeklyRest = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getWeekCounter() {
        return weekCounter;
    }

    public void setWeekCounter(Integer weekCounter) {
        this.weekCounter = weekCounter;
    }

    public Integer getTimeWeeklyElapsed() {
        return timeWeeklyElapsed;
    }

    public void setTimeWeeklyElapsed(Integer weeklyElapsed) {
        this.timeWeeklyElapsed = weeklyElapsed;
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

    public Boolean hasWeeklyRest() {
        return weeklyRest;
    }

    public LocalDateTime getMonthStartAt() {
        return monthStartAt;
    }

    public void setMonthStartAt(LocalDateTime monthStartAt) {
        this.monthStartAt = monthStartAt;
    }

    public LocalDateTime getMonthEndAt() {
        return monthEndAt;
    }

    public void setMonthEndAt(LocalDateTime monthEndAt) {
        this.monthEndAt = monthEndAt;
    }
}
