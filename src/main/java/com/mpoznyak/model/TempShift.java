package com.mpoznyak.model;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Max Poznyak
 * on 11/11/18  at 03:21
 */
@Entity
public class TempShift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "start_temp_shift")
    private Boolean startTempShift = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public Boolean getStartTempShift() {
        return startTempShift;
    }

    public void setStartTempShift(Boolean startTempShift) {
        this.startTempShift = startTempShift;
    }
}
