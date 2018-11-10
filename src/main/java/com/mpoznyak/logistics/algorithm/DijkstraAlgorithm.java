package com.mpoznyak.logistics.algorithm;

import com.mpoznyak.logistics.model.Graph;
import com.mpoznyak.model.City;
import com.mpoznyak.model.Road;

import java.util.*;

/**
 * Created by Max Poznyak
 * on 30/10/2018  at 22:52
 */

public class DijkstraAlgorithm {

    private final List<City> cities;
    private final List<Road> roads;
    private Set<City> visitedCities;
    private Set<City> unvisitedCities;
    private Map<City, City> predecessors;
    private Map<City, Integer> distance;

    public DijkstraAlgorithm(Graph graph) {
        // create a copy of the array so that we can operate on this array
        this.cities = new ArrayList<com.mpoznyak.model.City>(graph.getVertexes());
        this.roads = new ArrayList<Road>(graph.getRoads());
    }

    public void execute(City source) {
        visitedCities = new HashSet<City>();
        unvisitedCities = new HashSet<City>();
        distance = new HashMap<City, Integer>();
        predecessors = new HashMap<City, City>();
        distance.put(source, 0);
        unvisitedCities.add(source);
        while (unvisitedCities.size() > 0) {
            City node = getMinimum(unvisitedCities);
            visitedCities.add(node);
            unvisitedCities.remove(node);
            findMinimalDistances(node);
        }
    }

    private void findMinimalDistances(City node) {
        List<City> adjacentNodes = getNeighbors(node);
        for (City target : adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node)
                    + getDistance(node, target)) {
                distance.put(target, getShortestDistance(node)
                        + getDistance(node, target));
                predecessors.put(target, node);
                unvisitedCities.add(target);
            }
        }

    }

    private int getDistance(City node, City target) {
        for (Road edge : roads) {
            if (edge.getFrom().equals(node)
                    && edge.getTo().equals(target)) {
                return edge.getDistance();
            }
        }
        throw new RuntimeException("Should not happen");
    }

    private List<City> getNeighbors(City node) {
        List<City> neighbors = new ArrayList<>();
        for (Road edge : roads) {
            if (edge.getFrom().equals(node)
                    && !isSettled(edge.getTo())) {
                neighbors.add(edge.getTo());
            }
        }
        return neighbors;
    }

    private City getMinimum(Set<City> vertexes) {
        City minimum = null;
        for (City vertex : vertexes) {
            if (minimum == null) {
                minimum = vertex;
            } else {
                if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
                    minimum = vertex;
                }
            }
        }
        return minimum;
    }

    private boolean isSettled(City vertex) {
        return visitedCities.contains(vertex);
    }

    private int getShortestDistance(City destination) {
        Integer d = distance.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    /*
     * This method returns the path from the source to the selected target and
     * NULL if no path exists
     */
    public LinkedList<City> getPath(City target) {
        LinkedList<City> path = new LinkedList<>();
        City step = target;
        // check if a path exists
        /*
        if (predecessors.get(step) == null) {
            return null;
        }
        */
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        // Put it into the correct order
        Collections.reverse(path);
        return path;
    }

}
