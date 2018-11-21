package data;

import java.util.List;
import model.Driver;
import model.Order;
import model.Truck;

/**
 * Created by Max Poznyak
 * on 11/21/18  at 04:13
 */
public class DataAggregator {

    private List<Truck> trucks;
    private List<Driver> drivers;
    private List<Order> orders;

    public List<Truck> getTrucks() {
        return trucks;
    }

    public void setTrucks(List<Truck> trucks) {
        this.trucks = trucks;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }


}
