package com.pinde.core.model;


import java.util.List;

/**
 * Auto-generated: 2022-11-23 16:7:35
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class QQMapResultExt {

    private Ad_info ad_info;
    private String address;
    private Address_component address_component;
    private Address_reference address_reference;
    private Formatted_addresses formatted_addresses;
    private Location location;
    private int poi_count;
    private List<Pois> pois;
    public void setAd_info(Ad_info ad_info) {
        this.ad_info = ad_info;
    }
    public Ad_info getAd_info() {
        return ad_info;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress_component(Address_component address_component) {
        this.address_component = address_component;
    }
    public Address_component getAddress_component() {
        return address_component;
    }

    public void setAddress_reference(Address_reference address_reference) {
        this.address_reference = address_reference;
    }
    public Address_reference getAddress_reference() {
        return address_reference;
    }

    public void setFormatted_addresses(Formatted_addresses formatted_addresses) {
        this.formatted_addresses = formatted_addresses;
    }
    public Formatted_addresses getFormatted_addresses() {
        return formatted_addresses;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
    public Location getLocation() {
        return location;
    }

    public void setPoi_count(int poi_count) {
        this.poi_count = poi_count;
    }
    public int getPoi_count() {
        return poi_count;
    }

    public void setPois(List<Pois> pois) {
        this.pois = pois;
    }
    public List<Pois> getPois() {
        return pois;
    }

}