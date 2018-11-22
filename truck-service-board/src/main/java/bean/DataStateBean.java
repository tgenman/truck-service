package bean;

import data.DataLoader;
import data.DataStateListener;
import data.MQConsumer;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import model.Cargo;
import model.Customer;
import model.Driver;
import model.Order;
import model.RoutePoint;
import model.Shift;
import model.TempShift;
import model.Truck;
import org.apache.log4j.Logger;

/**
 * Created by Max Poznyak
 * on 11/21/18  at 21:04
 */

@Singleton
public class DataStateBean {

    private List<Truck> trucks;
    private List<Driver> drivers;
    private List<Order> orders;
    private List<Cargo> cargoes;
    private List<Customer> customers;
    private List<RoutePoint> routePoints;
    private List<Shift> shifts;
    private List<TempShift> tempShifts;
    private Long totalDrivers = 0L;
    private Long freeDrivers = 0L;
    private Long busyDrivers = 0L;
    private Long totalTrucks = 0L;
    private Long busyTrucks = 0L;
    private Long freeTrucks = 0L;
    private Long inoperableTrucks = 0L;
    MQConsumer mqConsumer = new MQConsumer();
    private static final Logger logger = Logger.getLogger(DataStateBean.class);
    private DataStateListener listener = DataStateListener.getInstance();
    private DataLoader dataLoader = DataLoader.getInstance();

    public void update() {

        logger.info("[START] update()");

        if (!listener.isInitialDataLoaded()) {
            logger.info("[isInitialDataLoaded=false] update()");

            trucks = dataLoader.getTrucks();
            drivers = dataLoader.getDrivers();
            orders = dataLoader.getOrders();
            cargoes = dataLoader.getCargoes();
            routePoints = dataLoader.getRoutePoints();
            shifts = dataLoader.getShifts();
            tempShifts = dataLoader.getTempShifts();
            customers = dataLoader.getCustomers();

            resetStatsCount();
            processTrucksData();
            processDriversData();
            listener.setInitialDataLoaded(true);
        }

        if (!listener.getDataState()) {
            logger.info("[getDataState=false] update()");
            trucks = dataLoader.getTrucks();
            drivers = dataLoader.getDrivers();
            orders = dataLoader.getOrders();
            cargoes = dataLoader.getCargoes();
            routePoints = dataLoader.getRoutePoints();
            shifts = dataLoader.getShifts();
            tempShifts = dataLoader.getTempShifts();
            customers = dataLoader.getCustomers();

            resetStatsCount();
            processTrucksData();
            processDriversData();
            listener.resetDataState();
        }
        logger.info("[END] update()");

    }

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

    @PostConstruct
    private void init() throws IOException, TimeoutException {
        logger.info("[START] init()");
        mqConsumer.start();
        update();
        logger.info("[END] init()");
    }

    @PreDestroy
    private void destroy() throws IOException, TimeoutException {
        mqConsumer.stop();
    }

    public List<Cargo> getCargoes() {
        return cargoes;
    }

    public void setCargoes(List<Cargo> cargoes) {
        this.cargoes = cargoes;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public List<RoutePoint> getRoutePoints() {
        return routePoints;
    }

    public void setRoutePoints(List<RoutePoint> routePoints) {
        this.routePoints = routePoints;
    }

    public List<Shift> getShifts() {
        return shifts;
    }

    public void setShifts(List<Shift> shifts) {
        this.shifts = shifts;
    }

    public List<TempShift> getTempShifts() {
        return tempShifts;
    }

    public void setTempShifts(List<TempShift> tempShifts) {
        this.tempShifts = tempShifts;
    }

    private void resetStatsCount() {
        totalDrivers = 0L;
        totalTrucks = 0L;
        freeTrucks = 0L;
        freeDrivers = 0L;
        busyTrucks = 0L;
        busyDrivers = 0L;
        inoperableTrucks = 0L;
    }

    private void processDriversData() {
        for (Driver driver : drivers) {
            if (driver.getStatus().contains("Free")) {
                freeDrivers++;
            } else {
                busyDrivers++;
            }
        }
        totalDrivers = (long) drivers.size();
    }

    private void processTrucksData() {
        for (Truck truck : trucks) {
            if (truck.isFree()) {
                freeTrucks++;
                continue;
            }
            if (truck.getStatus().contains("INOPERABLE")) {
                inoperableTrucks++;
                continue;
            }
            busyTrucks++;
        }
    }

    public Long getTotalDrivers() {
        return totalDrivers;
    }

    public Long getFreeDrivers() {
        return freeDrivers;
    }

    public Long getBusyDrivers() {
        return busyDrivers;
    }

    public Long getTotalTrucks() {
        return totalTrucks;
    }

    public Long getBusyTrucks() {
        return busyTrucks;
    }

    public Long getFreeTrucks() {
        return freeTrucks;
    }

    public Long getInoperableTrucks() {
        return inoperableTrucks;
    }
}
