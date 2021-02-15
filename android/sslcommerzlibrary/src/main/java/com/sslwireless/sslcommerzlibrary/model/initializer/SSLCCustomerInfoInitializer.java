package com.sslwireless.sslcommerzlibrary.model.initializer;

public class SSLCCustomerInfoInitializer {
    private String customerName;
    private String customerEmail;
    private String customerAddress1;
    private String customerAddress2;
    private String customerCity;
    private String customerState;
    private String customerPostCode;
    private String customerCountry;
    private String customerPhone;
    private String customerFax;

    public SSLCCustomerInfoInitializer(String customerName, String customerEmail,
                                       String customerAddress1,
                                       String customerCity,
                                       String customerPostCode, String customerCountry,
                                       String customerPhone) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerAddress1 = customerAddress1;
        this.customerCity = customerCity;
        this.customerPostCode = customerPostCode;
        this.customerCountry = customerCountry;
        this.customerPhone = customerPhone;
    }

    public SSLCCustomerInfoInitializer addAddress2(String customerAddress2) {
        this.customerAddress2 = customerAddress2;
        return this;
    }

    public SSLCCustomerInfoInitializer addState(String customerState) {
        this.customerState = customerState;
        return this;
    }

    public SSLCCustomerInfoInitializer addFax(String customerFax) {
        this.customerFax = customerFax;
        return this;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerAddress1() {
        return customerAddress1;
    }

    public void setCustomerAddress1(String customerAddress1) {
        this.customerAddress1 = customerAddress1;
    }

    public String getCustomerAddress2() {
        return customerAddress2;
    }

    public void setCustomerAddress2(String customerAddress2) {
        this.customerAddress2 = customerAddress2;
    }

    public String getCustomerCity() {
        return customerCity;
    }

    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
    }

    public String getCustomerState() {
        return customerState;
    }

    public void setCustomerState(String customerState) {
        this.customerState = customerState;
    }

    public String getCustomerPostCode() {
        return customerPostCode;
    }

    public void setCustomerPostCode(String customerPostCode) {
        this.customerPostCode = customerPostCode;
    }

    public String getCustomerCountry() {
        return customerCountry;
    }

    public void setCustomerCountry(String customerCountry) {
        this.customerCountry = customerCountry;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerFax() {
        return customerFax;
    }

    public void setCustomerFax(String customerFax) {
        this.customerFax = customerFax;
    }

}
