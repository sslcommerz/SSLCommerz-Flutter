package com.sslwireless.sslcommerzlibrary.model.initializer;

public class SSLCProductInitializer {
    private String productName;
    private String productCategory;
    private String cart;
    private double productAmount;
    private double vat;
    private double discountAmount;
    private double convenienceFee;
    private ProductProfile productProfile;
    private ProductProfile.AirlinesTicket airlinesTicket;
    private ProductProfile.General general;
    private ProductProfile.PhysicalGoods physicalGoods;
    private ProductProfile.NonPhysicalGoods nonPhysicalGoods;
    private ProductProfile.TelecomVertical telecomVertical;
    private ProductProfile.TravelVertical travelVertical;

    public SSLCProductInitializer(String productName, String productCategory,
                                  ProductProfile.AirlinesTicket productProfile) {
        this.productName = productName;
        this.productCategory = productCategory;
        this.airlinesTicket = productProfile;
    }

    public SSLCProductInitializer(String productName, String productCategory,
                                  ProductProfile.General productProfile) {
        this.productName = productName;
        this.productCategory = productCategory;
        this.general = productProfile;
    }

    public SSLCProductInitializer(String productName, String productCategory,
                                  ProductProfile.PhysicalGoods productProfile) {
        this.productName = productName;
        this.productCategory = productCategory;
        this.physicalGoods = productProfile;
    }

    public SSLCProductInitializer(String productName, String productCategory,
                                  ProductProfile.NonPhysicalGoods productProfile) {
        this.productName = productName;
        this.productCategory = productCategory;
        this.nonPhysicalGoods = productProfile;
    }

    public SSLCProductInitializer(String productName, String productCategory,
                                  ProductProfile.TravelVertical productProfile) {
        this.productName = productName;
        this.productCategory = productCategory;
        this.travelVertical = productProfile;
    }

    public SSLCProductInitializer(String productName, String productCategory,
                                  ProductProfile.TelecomVertical productProfile) {
        this.productName = productName;
        this.productCategory = productCategory;
        this.telecomVertical = productProfile;
    }

    public ProductProfile getProductProfile() {
        return productProfile;
    }

    public void setProductProfile(ProductProfile productProfile) {
        this.productProfile = productProfile;
    }

    public ProductProfile.PhysicalGoods getPhysicalGoods() {
        return physicalGoods;
    }

    public void setPhysicalGoods(ProductProfile.PhysicalGoods physicalGoods) {
        this.physicalGoods = physicalGoods;
    }

    public ProductProfile.NonPhysicalGoods getNonPhysicalGoods() {
        return nonPhysicalGoods;
    }

    public void setNonPhysicalGoods(ProductProfile.NonPhysicalGoods nonPhysicalGoods) {
        this.nonPhysicalGoods = nonPhysicalGoods;
    }

    public ProductProfile.TelecomVertical getTelecomVertical() {
        return telecomVertical;
    }

    public void setTelecomVertical(ProductProfile.TelecomVertical telecomVertical) {
        this.telecomVertical = telecomVertical;
    }

    public ProductProfile.TravelVertical getTravelVertical() {
        return travelVertical;
    }

    public void setTravelVertical(ProductProfile.TravelVertical travelVertical) {
        this.travelVertical = travelVertical;
    }

    public ProductProfile.AirlinesTicket getAirlinesTicket() {
        return airlinesTicket;
    }

    public void setAirlinesTicket(ProductProfile.AirlinesTicket airlinesTicket) {
        this.airlinesTicket = airlinesTicket;
    }

    public ProductProfile.General getGeneral() {
        return general;
    }

    public void setGeneral(ProductProfile.General general) {
        this.general = general;
    }

    public SSLCProductInitializer addCart(String cart) {
        this.cart = cart;
        return this;
    }

    public SSLCProductInitializer addProductAmount(double productAmount) {
        this.productAmount = productAmount;
        return this;
    }

    public SSLCProductInitializer addVat(double vat) {
        this.vat = vat;
        return this;
    }

    public SSLCProductInitializer addDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
        return this;
    }

    public SSLCProductInitializer addConvenienceFee(double convenienceFee) {
        this.convenienceFee = convenienceFee;
        return this;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getCart() {
        return cart;
    }

    public void setCart(String cart) {
        this.cart = cart;
    }

    public double getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(double productAmount) {
        this.productAmount = productAmount;
    }

    public double getVat() {
        return vat;
    }

    public void setVat(double vat) {
        this.vat = vat;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public double getConvenienceFee() {
        return convenienceFee;
    }

    public void setConvenienceFee(double convenienceFee) {
        this.convenienceFee = convenienceFee;
    }

    public static class ProductProfile {
        public static class General {
            private String general;
            private String productProfile;

            public General(String productProfile,String general) {
                this.productProfile = productProfile;
                this.general = general;
            }

            public String getProductProfile() {
                return productProfile;
            }

            public void setProductProfile(String productProfile) {
                this.productProfile = productProfile;
            }

            public String getGeneral() {
                return general;
            }

            public void setGeneral(String general) {
                this.general = general;
            }
        }

        public static class PhysicalGoods {
            private String physicalGoods;
            private String productProfile;

            public PhysicalGoods(String productProfile,String physicalGoods) {
                this.productProfile = productProfile;
                this.physicalGoods = physicalGoods;
            }

            public String getProductProfile() {
                return productProfile;
            }

            public void setProductProfile(String productProfile) {
                this.productProfile = productProfile;
            }

            public String getPhysicalGoods() {
                return physicalGoods;
            }

            public void setPhysicalGoods(String physicalGoods) {
                this.physicalGoods = physicalGoods;
            }
        }

        public static class NonPhysicalGoods {
            private String nonPhysicalGoods;
            private String productProfile;

            public NonPhysicalGoods(String productProfile,String nonPhysicalGoods) {
                this.productProfile = productProfile;
                this.nonPhysicalGoods = nonPhysicalGoods;
            }

            public String getProductProfile() {
                return productProfile;
            }

            public void setProductProfile(String productProfile) {
                this.productProfile = productProfile;
            }

            public String getNonPhysicalGoods() {
                return nonPhysicalGoods;
            }

            public void setNonPhysicalGoods(String nonPhysicalGoods) {
                this.nonPhysicalGoods = nonPhysicalGoods;
            }
        }

        public static class TelecomVertical {
            private String productProfile;
            private String productType;
            private String topUpNumber;
            private String countryTopUp;

            public TelecomVertical(String productProfile,
                                   String productType,String topUpNumber,String countryTopUp) {
                this.productProfile = productProfile;
                this.productType = productType;
                this.topUpNumber = topUpNumber;
                this.countryTopUp = countryTopUp;
            }

            public String getProductProfile() {
                return productProfile;
            }

            public void setProductProfile(String productProfile) {
                this.productProfile = productProfile;
            }

            public String getProductType() {
                return productType;
            }

            public void setProductType(String productType) {
                this.productType = productType;
            }

            public String getTopUpNumber() {
                return topUpNumber;
            }

            public void setTopUpNumber(String topUpNumber) {
                this.topUpNumber = topUpNumber;
            }

            public String getCountryTopUp() {
                return countryTopUp;
            }

            public void setCountryTopUp(String countryTopUp) {
                this.countryTopUp = countryTopUp;
            }
        }

        public static class TravelVertical {
            private String productProfile;
            private String hotelName;
            private String lengthOfStay;
            private String checkInTime;
            private String hotelCity;

            public TravelVertical(String productProfile, String hotelName, String lengthOfStay, String checkInTime,
                                  String hotelCity) {
                this.productProfile = productProfile;
                this.hotelName = hotelName;
                this.lengthOfStay = lengthOfStay;
                this.checkInTime = checkInTime;
                this.hotelCity = hotelCity;
            }

            public String getProductProfile() {
                return productProfile;
            }

            public void setProductProfile(String productProfile) {
                this.productProfile = productProfile;
            }

            public String getHotelName() {
                return hotelName;
            }

            public void setHotelName(String hotelName) {
                this.hotelName = hotelName;
            }

            public String getLengthOfStay() {
                return lengthOfStay;
            }

            public void setLengthOfStay(String lengthOfStay) {
                this.lengthOfStay = lengthOfStay;
            }

            public String getCheckInTime() {
                return checkInTime;
            }

            public void setCheckInTime(String checkInTime) {
                this.checkInTime = checkInTime;
            }

            public String getHotelCity() {
                return hotelCity;
            }

            public void setHotelCity(String hotelCity) {
                this.hotelCity = hotelCity;
            }
        }

        public static class AirlinesTicket {
            private String productProfile;
            private String hoursTillDeparture;
            private String flightType;
            private String pnr;
            private String journeyFromTo;
            private String thirdPartyBooking;

            public AirlinesTicket(String productProfile,String hoursTillDeparture, String flightType, String pnr,
                                  String journeyFromTo, String thirdPartyBooking) {
                this.productProfile = productProfile;
                this.hoursTillDeparture = hoursTillDeparture;
                this.flightType = flightType;
                this.pnr = pnr;
                this.journeyFromTo = journeyFromTo;
                this.thirdPartyBooking = thirdPartyBooking;
            }

            public String getProductProfile() {
                return productProfile;
            }

            public void setProductProfile(String productProfile) {
                this.productProfile = productProfile;
            }

            public String getHoursTillDeparture() {
                return hoursTillDeparture;
            }

            public void setHoursTillDeparture(String hoursTillDeparture) {
                this.hoursTillDeparture = hoursTillDeparture;
            }

            public String getFlightType() {
                return flightType;
            }

            public void setFlightType(String flightType) {
                this.flightType = flightType;
            }

            public String getPnr() {
                return pnr;
            }

            public void setPnr(String pnr) {
                this.pnr = pnr;
            }

            public String getJourneyFromTo() {
                return journeyFromTo;
            }

            public void setJourneyFromTo(String journeyFromTo) {
                this.journeyFromTo = journeyFromTo;
            }

            public String getThirdPartyBooking() {
                return thirdPartyBooking;
            }

            public void setThirdPartyBooking(String thirdPartyBooking) {
                this.thirdPartyBooking = thirdPartyBooking;
            }
        }
    }
}
