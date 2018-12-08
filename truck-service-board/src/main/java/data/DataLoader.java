package data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.MediaType;
import model.*;
import org.apache.log4j.Logger;

/**
 * Created by Max Poznyak
 * on 11/20/18  at 02:40
 */

public class DataLoader {

    private static final Logger logger = Logger.getLogger(DataLoader.class);
    private ObjectMapper mapper = new ObjectMapper();
    private Client client;
    private static volatile DataLoader loader;

    private DataLoader() {
        client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter("admin", "admin"));
    }

    public static DataLoader getInstance() {
        DataLoader localInstance = loader;
        if (localInstance == null) {
            synchronized (DataLoader.class) {
                localInstance = loader;
                if (localInstance == loader) {
                    localInstance = loader = new DataLoader();
                }
            }
        }
        return localInstance;
    }

    public List<TempShift> getTempShifts() {
        String response = getResponse("http://truckservice:8080/truckservice/api/temp-shift/list");
        List<TempShift> tempShifts = null;
        try {
            tempShifts = mapper.readValue(response, new TypeReference<List<TempShift>>() {
            });
        } catch (IOException e) {
            logger.error("Error during loading temp shifts data! " + e.getMessage());
        }

        return tempShifts;
    }

    public List<RoutePoint> getRoutePoints() {
        String response = getResponse("http://truckservice:8080/truckservice/api/route-point/list");
        List<RoutePoint> routePoints = null;
        try {
            routePoints = mapper.readValue(response, new TypeReference<List<RoutePoint>>() {
            });
        } catch (IOException e) {
            logger.error("Error during loading route points data! " + e.getMessage());
        }

        return routePoints;
    }

    public List<Customer> getCustomers() {
        String response = getResponse("http://truckservice:8080/truckservice/api/customer/list");
        List<Customer> customers = null;
        try {
            customers = mapper.readValue(response, new TypeReference<List<Customer>>() {
            });
        } catch (IOException e) {
            logger.error("Error during loading customers data! " + e.getMessage());
        }

        return customers;
    }

    public List<Shift> getShifts() {
        String response = getResponse("http://truckservice:8080/truckservice/api/shift/list");
        List<Shift> shifts = null;
        try {
            shifts = mapper.readValue(response, new TypeReference<List<Shift>>() {
            });
        } catch (IOException e) {
            logger.error("Error during loading shifts data! " + e.getMessage());
        }

        return shifts;
    }

    public List<Order> getOrders() {
        String response = getResponse("http://truckservice:8080/truckservice/api/order/list");
        List<Order> orders = null;
        try {
            orders = mapper.readValue(response, new TypeReference<List<Order>>() {
            });
        } catch (IOException e) {
            logger.error("Error during loading orders data! " + e.getMessage());
        }

        if (orders != null) {
            if (orders.size() == 0) {
                return new ArrayList<>();
            }
            List<Order> finalList = new ArrayList<>();
            for (int i = 1; i < 11; i++) {
                finalList.add(orders.get(orders.size() - i));
            }
            return finalList;
        } else {
            return new ArrayList<>();
        }
    }

    public List<Cargo> getCargoes() {
        String response = getResponse("http://truckservice:8080/truckservice/api/cargo/list");
        List<Cargo> cargoes = null;
        try {
            cargoes = mapper.readValue(response, new TypeReference<List<Cargo>>() {
            });
        } catch (IOException e) {
            logger.error("Error during loading cargoes data! " + e.getMessage());
        }

        return cargoes;
    }

    public List<Driver> getDrivers() {
        String response = getResponse("http://truckservice:8080/truckservice/api/driver/list");
        List<Driver> drivers = null;
        try {
            drivers = mapper.readValue(response, new TypeReference<List<Driver>>() {
            });
        } catch (IOException e) {
            logger.error("Error during loading drivers data! " + e.getMessage());
        }

        return drivers;
    }

    public List<Truck> getTrucks() {
        String response = getResponse("http://truckservice:8080/truckservice/api/truck/list");
        List<Truck> trucks = null;
        try {
            trucks = mapper.readValue(response, new TypeReference<List<Truck>>() {
            });
        } catch (IOException e) {
            logger.error("Error during loading trucks data! " + e.getMessage());
        }

        return trucks;
    }

    private String getResponse(String url) {
        WebResource webResource = client.resource(url);
        String response = webResource.accept(MediaType.APPLICATION_JSON_TYPE)
                .get(ClientResponse.class).getEntity(String.class);
        return response;
    }
}
