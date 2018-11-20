package com.mpoznyak.dto;

/**
 * Created by Max Poznyak
 * on 29/10/2018  at 16:20
 */
public class CargoDTO {

    private Long id;

    private String name;

    private Long weight;

    private String status;

    private Long routePointId;

    private RoutePointDTO droppedOffIn;

    private RoutePointDTO pickedUpIn;

    private Boolean dropLocationSelected;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getRoutePointId() {
        return routePointId;
    }

    public void setRoutePointId(Long routePointId) {
        this.routePointId = routePointId;
    }

    public Boolean getDropLocationSelected() {
        return dropLocationSelected;
    }

    public void setDropLocationSelected(Boolean dropLocationSelected) {
        this.dropLocationSelected = dropLocationSelected;
    }

    public RoutePointDTO getDroppedOffIn() {
        return droppedOffIn;
    }

    public void setDroppedOffIn(RoutePointDTO droppedOffIn) {
        this.droppedOffIn = droppedOffIn;
    }

    public RoutePointDTO getPickedUpIn() {
        return pickedUpIn;
    }

    public void setPickedUpIn(RoutePointDTO pickedUpIn) {
        this.pickedUpIn = pickedUpIn;
    }

}
