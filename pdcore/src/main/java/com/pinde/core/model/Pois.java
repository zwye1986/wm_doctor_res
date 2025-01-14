package com.pinde.core.model;


/**
 * Auto-generated: 2022-11-23 16:7:35
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Pois {

    private String _dir_desc;
    private int _distance;
    private Ad_info ad_info;
    private String address;
    private String category;
    private String id;
    private Location location;
    private String title;
    public void set_dir_desc(String _dir_desc) {
        this._dir_desc = _dir_desc;
    }
    public String get_dir_desc() {
        return _dir_desc;
    }

    public void set_distance(int _distance) {
        this._distance = _distance;
    }
    public int get_distance() {
        return _distance;
    }

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

    public void setCategory(String category) {
        this.category = category;
    }
    public String getCategory() {
        return category;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
    public Location getLocation() {
        return location;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }
}