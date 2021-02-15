package com.sslwireless.sslcommerzlibrary.model.initializer;

public class SSLCShipmentInfoInitializer {
    private String shipmentMethod;
    private String shipAddress2;
    private String shipState;
    private int numOfItems;

    private ShipmentDetails shipmentDetails;

    public SSLCShipmentInfoInitializer(String shipmentMethod,
                                       int numOfItems, ShipmentDetails shipmentDetails) {
        this.shipmentMethod = shipmentMethod;
        this.numOfItems = numOfItems;

        if (shipmentMethod.equalsIgnoreCase("yes"))
            this.shipmentDetails = shipmentDetails;
        else
            this.shipmentDetails = null;
    }

    public SSLCShipmentInfoInitializer addShipAddress2(String shipAddress2) {
        this.shipAddress2 = shipAddress2;
        return this;
    }

    public SSLCShipmentInfoInitializer addShipState(String shipState) {
        this.shipState = shipState;
        return this;
    }

    public String getShipmentMethod() {
        return shipmentMethod;
    }

    public void setShipmentMethod(String shipmentMethod) {
        this.shipmentMethod = shipmentMethod;
    }

    public int getNumOfItems() {
        return numOfItems;
    }

    public void setNumOfItems(int numOfItems) {
        this.numOfItems = numOfItems;
    }

    public ShipmentDetails getShipmentDetails() {
        return shipmentDetails;
    }

    public void setShipmentDetails(ShipmentDetails shipmentDetails) {
        this.shipmentDetails = shipmentDetails;
    }

    public String getShipAddress2() {
        return shipAddress2;
    }

    public void setShipAddress2(String shipAddress2) {
        this.shipAddress2 = shipAddress2;
    }

    public String getShipState() {
        return shipState;
    }

    public void setShipState(String shipState) {
        this.shipState = shipState;
    }

    public static class ShipmentDetails {
        private String shipName;
        private String shipAddress1;
        private String shipCity;
        private String shipPostCode;
        private String shipCountry;

        public ShipmentDetails(String shipName, String shipAddress1,
                               String shipCity, String shipPostCode, String shipCountry) {
            this.shipName = shipName;
            this.shipAddress1 = shipAddress1;
            this.shipCity = shipCity;
            this.shipPostCode = shipPostCode;
            this.shipCountry = shipCountry;
        }

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
