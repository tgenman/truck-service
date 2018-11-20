package model;

import java.util.Objects;

/**
 * Created by Max Poznyak
 * on 11/19/18  at 19:18
 */
public class TempShift {

    private Long id;
    private String startDate;
    private Boolean startTempShift;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Boolean getStartTempShift() {
        return startTempShift;
    }

    public void setStartTempShift(Boolean startTempShift) {
        this.startTempShift = startTempShift;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TempShift tempShift = (TempShift) o;
        return Objects.equals(id, tempShift.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
