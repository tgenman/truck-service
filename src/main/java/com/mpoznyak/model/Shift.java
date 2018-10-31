package com.mpoznyak.model;

import javax.persistence.*;
import java.time.LocalDate;

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
    private LocalDate monthStartAt;

    @Column(name = "month_end_at")
    private LocalDate monthEndAt;

    @Column(name = "time_weekly_elapsed")
    private Integer timeWeeklyElapsed = 0;

    @Column(name = "week_counter")
    private Integer weekCounter = 1;

    @Column(name = "time_monthly_elapsed")
    private Integer timeMonthlyElapsed = 0;

    @Column(name = "weekly_rest")
    private Boolean weeklyRest = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getMonthStartAt() {
        return monthStartAt;
    }

    public void setMonthStartAt(LocalDate monthStartAt) {
        this.monthStartAt = monthStartAt;
    }

    public LocalDate getMonthEndAt() {
        return monthEndAt;
    }

    public void setMonthEndAt(LocalDate monthEndAt) {
        this.monthEndAt = monthEndAt;
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

    public Integer getTimeMonthlyElapsed() {
        return timeMonthlyElapsed;
    }

    public void setTimeMonthlyElapsed(Integer monthlyElapsed) {
        this.timeMonthlyElapsed = monthlyElapsed;
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
}
