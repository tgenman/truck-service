package data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.io.IOException;
import java.util.List;
import javax.ws.rs.core.MediaType;
import model.Truck;
import org.apache.log4j.Logger;

/**
 * Created by Max Poznyak
 * on 11/20/18  at 02:40
 */

public class RestClient {

    private static final Logger logger = Logger.getLogger(RestClient.class);

    private ObjectMapper mapper = new ObjectMapper();
    private Client client = new Client();

    public List<Truck> getTrucks() {
        String response = getResponse("http://localhost:8080/rest/truck/list");
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
