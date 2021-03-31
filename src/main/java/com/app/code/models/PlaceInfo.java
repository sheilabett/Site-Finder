package com.app.code.models;

import com.google.android.gms.maps.model.LatLng;

public class PlaceInfo {
    private String name;
    private String placeId;
    private String address;
    private LatLng mLatLng;
    private String businessStatus;
    private boolean openNow;
    private String Phone;

    public PlaceInfo(String name, String placeId, String address, LatLng latLng,
                     String businessStatus, boolean openNow) {
        this.name = name;
        this.placeId = placeId;
        this.address = address;
        mLatLng = latLng;
        this.businessStatus = businessStatus;
        this.openNow = openNow;

        //this.Phone = phone;

    }

    public String getphone() {
        return Phone;
    }

    public void setphone(String phone) {
        Phone = phone;
    }





    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LatLng getLatLng() {
        return mLatLng;
    }

    public void setLatLng(LatLng latLng) {
        mLatLng = latLng;
    }

    public String getBusinessStatus() {
        return businessStatus;
    }

    public void setBusinessStatus(String businessStatus) {
        this.businessStatus = businessStatus;
    }

    public boolean isOpenNow() {
        return openNow;
    }

    public void setOpenNow(boolean openNow) {
        this.openNow = openNow;
    }
}
