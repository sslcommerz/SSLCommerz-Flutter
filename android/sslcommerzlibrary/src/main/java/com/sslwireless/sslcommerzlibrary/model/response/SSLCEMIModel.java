
package com.sslwireless.sslcommerzlibrary.model.response;
import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("unused")
public class SSLCEMIModel implements Serializable {

    @Expose
    private Data data;
    @Expose
    private String message;
    @Expose
    private String status;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public class Data {

        @Expose
        private String actionStatus;
        @Expose
        private Data data;
        @Expose
        private List<Emi> emi;
        @Expose
        private String msgToDisplay;
        @Expose
        private String status;
        @Expose
        private String systemMesg;

        public String getActionStatus() {
            return actionStatus;
        }

        public void setActionStatus(String actionStatus) {
            this.actionStatus = actionStatus;
        }

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }

        public List<Emi> getEmi() {
            return emi;
        }

        public void setEmi(List<Emi> emi) {
            this.emi = emi;
        }

        public String getMsgToDisplay() {
            return msgToDisplay;
        }

        public void setMsgToDisplay(String msgToDisplay) {
            this.msgToDisplay = msgToDisplay;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getSystemMesg() {
            return systemMesg;
        }

        public void setSystemMesg(String systemMesg) {
            this.systemMesg = systemMesg;
        }

    }

    public class Emi {

        @Expose
        private String bankLogo;
        @Expose
        private String bankName;
        @Expose
        private List<String> binList;
        @Expose
        private String emiBankID;
        @Expose
        private List<EmiBankTenureDesc> emiBankTenureDesc;
        @Expose
        private List<EmiBankTenureList> emiBankTenureList;
        @Expose
        private String redirectGWPath;

        public String getBankLogo() {
            return bankLogo;
        }

        public void setBankLogo(String bankLogo) {
            this.bankLogo = bankLogo;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public List<String> getBinList() {
            return binList;
        }

        public void setBinList(List<String> binList) {
            this.binList = binList;
        }

        public String getEmiBankID() {
            return emiBankID;
        }

        public void setEmiBankID(String emiBankID) {
            this.emiBankID = emiBankID;
        }

        public List<EmiBankTenureDesc> getEmiBankTenureDesc() {
            return emiBankTenureDesc;
        }

        public void setEmiBankTenureDesc(List<EmiBankTenureDesc> emiBankTenureDesc) {
            this.emiBankTenureDesc = emiBankTenureDesc;
        }

        public List<EmiBankTenureList> getEmiBankTenureList() {
            return emiBankTenureList;
        }

        public void setEmiBankTenureList(List<EmiBankTenureList> emiBankTenureList) {
            this.emiBankTenureList = emiBankTenureList;
        }

        public String getRedirectGWPath() {
            return redirectGWPath;
        }

        public void setRedirectGWPath(String redirectGWPath) {
            this.redirectGWPath = redirectGWPath;
        }

    }

    public class EmiBankTenureDesc {

        @Expose
        private String desc;
        @Expose
        private Long tenure;

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public Long getTenure() {
            return tenure;
        }

        public void setTenure(Long tenure) {
            this.tenure = tenure;
        }

    }

    public class EmiBankTenureList {

        @Expose
        private Long tenure;

        public Long getTenure() {
            return tenure;
        }

        public void setTenure(Long tenure) {
            this.tenure = tenure;
        }

    }
}
