package bean;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.*;
import org.apache.log4j.Logger;

/**
 * Created by Max Poznyak
 * on 11/20/18  at 03:48
 */

@ManagedBean
@ViewScoped
public class BoardBean implements Serializable {

    @EJB
    DataStateBean dataStateBean;
    private static final Logger logger = Logger.getLogger(BoardBean.class);

    public void update() {
        logger.info("[START] update()");
        dataStateBean.update();
        logger.info("[END] update()");
    }

    public List<Truck> getTrucks() {
        logger.info("[START] getTrucks()");
        return dataStateBean.getTrucks();
    }

    public List<Driver> getDrivers() {
        logger.info("[START] getDrivers()");
        return dataStateBean.getDrivers();
    }

    public List<Order> getOrders() {
        logger.info("[START] getOrders()");
        return dataStateBean.getOrders();
    }

    public List<Cargo> getCargoes() {
        logger.info("[START] getCargoes()");
        return dataStateBean.getCargoes();
    }

    public List<Customer> getCustomers() {
        logger.info("[START] getCustomers()");
        return dataStateBean.getCustomers();
    }

    public List<Shift> getShifts() {
        logger.info("[START] getShifts()");
        return dataStateBean.getShifts();
    }

    public List<TempShift> getTempShifts() {
        logger.info("[START] getTempShifts()");
        return dataStateBean.getTempShifts();
    }

    public List<RoutePoint> getRoutePoints() {
        logger.info("[START] getRoutePoints()");
        return dataStateBean.getRoutePoints();
    }

    public Long getTotalDrivers() {
        logger.info("[START] getTotalDrivers()");
        return dataStateBean.getTotalDrivers();
    }

    public Long getFreeDrivers() {
        logger.info("[START] getFreeDrivers()");
        return dataStateBean.getFreeDrivers();
    }

    public Long getBusyDrivers() {
        logger.info("[START] getBusyDrivers()");
        return dataStateBean.getBusyDrivers();
    }

    public Long getBusyTrucks() {
        logger.info("[START] getBusyTrucks()");
        return dataStateBean.getBusyTrucks();
    }

    public Long getFreeTrucks() {
        logger.info("[START] getFreeTrucks()");
        return dataStateBean.getFreeTrucks();
    }

    public Long getTotalTrucks() {
        logger.info("[START] getTotalTrucks()");
        return dataStateBean.getTotalTrucks();
    }

    public Long getInoperableTrucks() {
        logger.info("[START] getInoperableTrucks()");
        return dataStateBean.getInoperableTrucks();
    }


}
