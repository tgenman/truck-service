package bean;

import data.DataAggregator;
import data.DataLoader;
import data.MQConsumer;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import logging.annotation.Loggable;
import model.*;

/**
 * Created by Max Poznyak
 * on 11/20/18  at 03:48
 */

@ManagedBean
public class BoardBean {

    private DataAggregator dataAggregator = new DataAggregator();
    private MQConsumer mqConsumer = new MQConsumer();
    private List<Truck> trucks;
    private List<Driver> drivers;
    private List<Order> orders;

    @Loggable
    public void update() {
        trucks = dataAggregator.getTrucks();
        drivers = dataAggregator.getDrivers();
        orders = dataAggregator.getOrders();

        try {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    private void init() throws IOException, TimeoutException {
        mqConsumer.start();
        update();
    }

    @PreDestroy
    private void destroy() throws IOException, TimeoutException {
        mqConsumer.stop();
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
}
