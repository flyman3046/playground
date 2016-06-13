package com.example.flyman.playground.model;

/**
 * Park bean
 */
public class ParkJson {

    private String id;
    private String lat;
    private String lng;
    private String name;
    private String cost_per_minute;
    private String max_reserve_time_mins;
    private String min_reserve_time_mins;
    private String is_reserved;
    private String reserved_until;

    public String getName() {
        return name;
    }
    public String getCost() {
        return cost_per_minute;
    }
    public String getLat() {
        return lat;
    }
    public String getLng() {
        return lng;
    }
}
