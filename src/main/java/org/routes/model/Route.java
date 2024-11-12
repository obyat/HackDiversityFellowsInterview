package org.routes.model;

public class Route {

    private String id;
    private String from;
    private String to;
    private boolean accessible;
    private double distance;

    public Route() {
    }

    public Route(String id, String from, String to, boolean accessible, double distance) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.accessible = accessible;
        this.distance = distance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public boolean isAccessible() {
        return accessible;
    }

    public void setAccessible(boolean accessible) {
        this.accessible = accessible;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Route{id='" + id + "', from='" + from + "', to='" + to + "', accessible=" + accessible + ", distance=" + distance + "}";
    }
}