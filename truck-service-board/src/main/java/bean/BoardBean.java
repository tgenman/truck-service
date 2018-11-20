package bean;

import data.RestClient;
import java.util.List;
import javax.faces.bean.ManagedBean;
import model.Truck;

/**
 * Created by Max Poznyak
 * on 11/20/18  at 03:48
 */

@ManagedBean
public class BoardBean {

    RestClient restClient = new RestClient();

    public List<Truck> getTrucks() {
        return restClient.getTrucks();
    }
}
