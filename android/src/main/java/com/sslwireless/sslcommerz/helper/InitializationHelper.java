package com.sslwireless.sslcommerz.helper;

import com.sslwireless.sslcommerz.model.SDKDataModel;
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCAdditionalInitializer;
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCCustomerInfoInitializer;
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCProductInitializer;
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCShipmentInfoInitializer;
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCommerzInitialization;

public class InitializationHelper {
    public static SSLCProductInitializer initiateProductInitializer(SDKDataModel sdkDataModel) {
        if (sdkDataModel.getSslcProductInitializer().getAirlinesTicket() != null) {
            return new SSLCProductInitializer(
                    sdkDataModel.getSslcProductInitializer().getProductName(),
                    sdkDataModel.getSslcProductInitializer().getProductCategory(),
                    new SSLCProductInitializer.ProductProfile.AirlinesTicket(
                            sdkDataModel.getSslcProductInitializer().getAirlinesTicket().getProductProfile(),
                            sdkDataModel.getSslcProductInitializer().getAirlinesTicket().getHoursTillDeparture(),
                            sdkDataModel.getSslcProductInitializer().getAirlinesTicket().getFlightType(),
                            sdkDataModel.getSslcProductInitializer().getAirlinesTicket().getPnr(),
                            sdkDataModel.getSslcProductInitializer().getAirlinesTicket().getJourneyFromTo(),
                            sdkDataModel.getSslcProductInitializer().getAirlinesTicket().getThirdPartyBooking()
                    )
            );
        } else if (sdkDataModel.getSslcProductInitializer().getPhysicalGoods() != null) {
            return new SSLCProductInitializer(
                    sdkDataModel.getSslcProductInitializer().getProductName(),
                    sdkDataModel.getSslcProductInitializer().getProductCategory(),
                    new SSLCProductInitializer.ProductProfile.PhysicalGoods(
                            sdkDataModel.getSslcProductInitializer().getPhysicalGoods().getProductProfile(),
                            sdkDataModel.getSslcProductInitializer().getPhysicalGoods().getPhysicalGoods()
                    )
            );
        } else if (sdkDataModel.getSslcProductInitializer().getNonPhysicalGoods() != null) {
            return new SSLCProductInitializer(
                    sdkDataModel.getSslcProductInitializer().getProductName(),
                    sdkDataModel.getSslcProductInitializer().getProductCategory(),
                    new SSLCProductInitializer.ProductProfile.NonPhysicalGoods(
                            sdkDataModel.getSslcProductInitializer().getNonPhysicalGoods().getProductProfile(),
                            sdkDataModel.getSslcProductInitializer().getNonPhysicalGoods().getNonPhysicalGoods()
                    )
            );
        } else if (sdkDataModel.getSslcProductInitializer().getNonPhysicalGoods() != null) {
            return new SSLCProductInitializer(
                    sdkDataModel.getSslcProductInitializer().getProductName(),
                    sdkDataModel.getSslcProductInitializer().getProductCategory(),
                    new SSLCProductInitializer.ProductProfile.TravelVertical(
                            sdkDataModel.getSslcProductInitializer().getTravelVertical().getProductProfile(),
                            sdkDataModel.getSslcProductInitializer().getTravelVertical().getHotelName(),
                            sdkDataModel.getSslcProductInitializer().getTravelVertical().getLengthOfStay(),
                            sdkDataModel.getSslcProductInitializer().getTravelVertical().getCheckInTime(),
                            sdkDataModel.getSslcProductInitializer().getTravelVertical().getHotelCity()
                    )
            );
        } else if (sdkDataModel.getSslcProductInitializer().getTelecomVertical() != null) {
            return new SSLCProductInitializer(
                    sdkDataModel.getSslcProductInitializer().getProductName(),
                    sdkDataModel.getSslcProductInitializer().getProductCategory(),
                    new SSLCProductInitializer.ProductProfile.TelecomVertical(
                            sdkDataModel.getSslcProductInitializer().getTelecomVertical().getProductProfile(),
                            sdkDataModel.getSslcProductInitializer().getTelecomVertical().getProductType(),
                            sdkDataModel.getSslcProductInitializer().getTelecomVertical().getTopUpNumber(),
                            sdkDataModel.getSslcProductInitializer().getTelecomVertical().getCountryTopUp()
                    )
            );
        } else {
            return new SSLCProductInitializer(
                    sdkDataModel.getSslcProductInitializer().getProductName(),
                    sdkDataModel.getSslcProductInitializer().getProductCategory(),
                    new SSLCProductInitializer.ProductProfile.General(
                            sdkDataModel.getSslcProductInitializer().getGeneral().getGeneral(),
                            sdkDataModel.getSslcProductInitializer().getGeneral().getProductProfile()
                    )
            );
        }
    }

    public static SSLCShipmentInfoInitializer initiateShipmentInfo(SDKDataModel sdkDataModel) {
        return new SSLCShipmentInfoInitializer(
                sdkDataModel.getSslcShipmentInfoInitializer().getShipmentMethod(),
                sdkDataModel.getSslcShipmentInfoInitializer().getNumOfItems(),
                new SSLCShipmentInfoInitializer.ShipmentDetails(
                        sdkDataModel.getSslcShipmentInfoInitializer().getShipmentDetails().getShipName(),
                        sdkDataModel.getSslcShipmentInfoInitializer().getShipmentDetails().getShipAddress1(),
                        sdkDataModel.getSslcShipmentInfoInitializer().getShipmentDetails().getShipCity(),
                        sdkDataModel.getSslcShipmentInfoInitializer().getShipmentDetails().getShipPostCode(),
                        sdkDataModel.getSslcShipmentInfoInitializer().getShipmentDetails().getShipCountry()));
    }

    public static SSLCCustomerInfoInitializer initiateCustomerInfo(SDKDataModel sdkDataModel) {
        return new SSLCCustomerInfoInitializer(
                sdkDataModel.getCustomerInfoInitializer().getCustomerName(),
                sdkDataModel.getCustomerInfoInitializer().getCustomerEmail(),
                sdkDataModel.getCustomerInfoInitializer().getCustomerAddress1(),
                sdkDataModel.getCustomerInfoInitializer().getCustomerAddress2(),
                sdkDataModel.getCustomerInfoInitializer().getCustomerPostCode(),
                sdkDataModel.getCustomerInfoInitializer().getCustomerCountry(),
                sdkDataModel.getCustomerInfoInitializer().getCustomerPhone()
        );
    }

    public static SSLCommerzInitialization sslCommerzInitializatoin(SDKDataModel sdkDataModel) {
        SSLCommerzInitialization sslCommerzInitialization = new SSLCommerzInitialization(
                sdkDataModel.getInitializer().getStoreId(),
                sdkDataModel.getInitializer().getStorePasswd(),
                sdkDataModel.getInitializer().getTotalAmount(),
                sdkDataModel.getInitializer().getCurrency(),
                sdkDataModel.getInitializer().getTranId(),
                sdkDataModel.getInitializer().getProductCategory(),
                sdkDataModel.getInitializer().getSdkType());
        if (sdkDataModel.getInitializer().getSuccessUrl() != null)
            sslCommerzInitialization.setSuccess_url(sdkDataModel.getInitializer().getSuccessUrl());
        if (sdkDataModel.getInitializer().getFailUrl() != null)
            sslCommerzInitialization.setFail_url(sdkDataModel.getInitializer().getFailUrl());
        if (sdkDataModel.getInitializer().getCancelUrl() != null)
            sslCommerzInitialization.setCancel_url(sdkDataModel.getInitializer().getCancelUrl());
        if (sdkDataModel.getInitializer().getIpnUrl() != null)
            sslCommerzInitialization.setIpn_url(sdkDataModel.getInitializer().getIpnUrl());
        if (sdkDataModel.getInitializer().getMultiCardName() != null)
            sslCommerzInitialization.setMulti_card_name(sdkDataModel.getInitializer().getMultiCardName());
        if (sdkDataModel.getInitializer().getAllowedBin() != null)
            sslCommerzInitialization.setAllowed_bin(sdkDataModel.getInitializer().getAllowedBin());
        return sslCommerzInitialization;
    }

    public static SSLCAdditionalInitializer getAdditionalInitializer(SDKDataModel sdkDataModel) {
        SSLCAdditionalInitializer sslcAdditionalInitializer = new SSLCAdditionalInitializer();
        if(sdkDataModel.getSslcAdditionalInitializer().getValueA()!=null)
        sslcAdditionalInitializer.setValueA(sdkDataModel.getSslcAdditionalInitializer().getValueA());
        if(sdkDataModel.getSslcAdditionalInitializer().getValueB()!=null)
        sslcAdditionalInitializer.setValueB(sdkDataModel.getSslcAdditionalInitializer().getValueB());
        if(sdkDataModel.getSslcAdditionalInitializer().getValueC()!=null)
        sslcAdditionalInitializer.setValueC(sdkDataModel.getSslcAdditionalInitializer().getValueC());
        if(sdkDataModel.getSslcAdditionalInitializer().getValueD()!=null)
        sslcAdditionalInitializer.setValueD(sdkDataModel.getSslcAdditionalInitializer().getValueD());
        return sslcAdditionalInitializer;
    }
}
