package com.mpoznyak.service.api;

import com.mpoznyak.dto.OrderDTO;
import com.mpoznyak.dto.RouteDTO;
import com.mpoznyak.logistics.model.Graph;
import com.mpoznyak.model.City;
import com.mpoznyak.model.Road;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Max Poznyak
 * on 2018-12-07  at 17:15
 */

public interface GraphService {

    LinkedList<City> getPath(Graph graph, int from, int to);

    List<City> getGraphVertices();

    List<Road> getGraphEdges();

    Long getRouteTime(RouteDTO routeDTO, OrderDTO orderDTO);

}
