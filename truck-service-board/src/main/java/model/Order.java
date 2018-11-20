package model;

import java.util.List;
import java.util.Objects;

/**
 * Created by Max Poznyak
 * on 11/19/18  at 18:45
 */
public class Order {

    private Long id;
    private Long customerId;
    private String date;
    private String status;
    private List<Long> driversId;
    private Long truckId;
    private Long tempShiftId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Long> getDriversId() {
        return driversId;
    }

    public void setDriversId(List<Long> driversId) {
        this.driversId = driversId;
    }

    public Long getTruckId() {
        return truckId;
    }

    public void setTruckId(Long truckId) {
        this.truckId = truckId;
    }

    public Long getTempShiftId() {
        return tempShiftId;
    }

    public void setTempShiftId(Long tempShiftId) {
        this.tempShiftId = tempShiftId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
