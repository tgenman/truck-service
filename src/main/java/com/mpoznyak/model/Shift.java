package com.mpoznyak.model;

import javax.persistence.*;
import java.util.Date;

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
    private Date monthStartAt;

    @Column(name = "month_end_at")
    private Date monthEndAt;

    @Column(name = "time_weekly_elapsed")
    private Integer timeWeeklyElapsed;

    @Column(name = "week_counter")
    private Integer weekCounter = 1;

    @Column(name = "time_monthly_elapsed")
    private Integer timeMonthlyElapsed;

    @Column(name = "weekly_rest")
    private Boolean weeklyRest = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getMonthStartAt() {
        return monthStartAt;
    }

    public void setMonthStartAt(Date monthStartAt) {
        this.monthStartAt = monthStartAt;
    }

    public Date getMonthEndAt() {
        return monthEndAt;
    }

    public void setMonthEndAt(Date monthEndAt) {
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
