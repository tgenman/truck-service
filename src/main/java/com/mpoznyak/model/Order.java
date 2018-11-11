package com.mpoznyak.model;

import com.mpoznyak.model.type.OrderStatus;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * Created by Max Poznyak
 * on 18/10/2018  at 20:15
 */


@Entity
@Table(name = "Order_")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "timestamp")
    private LocalDateTime date;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.INCOMPLETED;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private List<Driver> drivers;

    @OneToOne
    @JoinColumn(name = "truck_id")
    private Truck truck;

    @Column(name = "deleted")
    private Boolean deleted = false;

    @OneToOne
    @JoinColumn(name = "tempshift_id")
    private TempShift tempShift;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public TempShift getTempShift() {
        return tempShift;
    }

    public void setTempShift(TempShift tempShift) {
        this.tempShift = tempShift;
    }
}
