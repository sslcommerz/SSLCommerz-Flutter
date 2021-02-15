package com.sslwireless.sslcommerz.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SslcShipmentInfoInitializer {

    @SerializedName("shipmentMethod")
    @Expose
    private String shipmentMethod;
    @SerializedName("shipAddress2")
    @Expose
    private Object shipAddress2;
    @SerializedName("shipState")
    @Expose
    private Object shipState;
    @SerializedName("numOfItems")
    @Expose
    private Integer numOfItems;
    @SerializedName("shipmentDetails")
    @Expose
    private ShipmentDetails shipmentDetails;

    public String getShipmentMethod() {
        return shipmentMethod;
    }

    public void setShipmentMethod(String shipmentMethod) {
        this.shipmentMethod = shipmentMethod;
    }

    public Object getShipAddress2() {
        return shipAddress2;
    }

    public void setShipAddress2(Object shipAddress2) {
        this.shipAddress2 = shipAddress2;
    }

    public Object getShipState() {
        return shipState;
    }

    public void setShipState(Object shipState) {
        this.shipState = shipState;
    }

    public Integer getNumOfItems() {
        return numOfItems;
    }

    public void setNumOfItems(Integer numOfItems) {
        this.numOfItems = numOfItems;
    }

    public ShipmentDetails getShipmentDetails() {
        return shipmentDetails;
    }

    public void setShipmentDetails(ShipmentDetails shipmentDetails) {
        this.shipmentDetails = shipmentDetails;
    }

    public static class ShipmentDetails {

        @SerializedName("shipName")
        @Expose
        private String shipName;
        @SerializedName("shipAddress1")
        @Expose
        private String shipAddress1;
        @SerializedName("shipCity")
        @Expose
        private String shipCity;
        @SerializedName("shipPostCode")
        @Expose
        private String shipPostCode;
        @SerializedName("shipCountry")
        @Expose
        private String shipCountry;

        public String getShipName() {
            return shipName;
        }

        public void setShipName(String shipName) {
            this.shipName = shipName;
        }

        public String getShipAddress1() {
            return shipAddress1;
        }

        public void setShipAddress1(String shipAddress1) {
            this.shipAddress1 = shipAddress1;
        }

        public String getShipCity() {
            return shipCity;
        }

        public void setShipCity(String shipCity) {
            this.shipCity = shipCity;
        }

        public String getShipPostCode() {
            return shipPostCode;
        }

        public void setShipPostCode(String shipPostCode) {
            this.shipPostCode = shipPostCode;
        }

        public String getShipCountry() {
            return shipCountry;
        }

        public void setShipCountry(String shipCountry) {
            this.shipCountry = shipCountry;
        }

    }
}
