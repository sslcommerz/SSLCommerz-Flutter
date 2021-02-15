package com.sslwireless.sslcommerzlibrary.model.initializer;

public class SSLCEMITransactionInitializer {
    public int emi_options;
    public int emi_max_list_options;
    public int emi_selected_inst;

    public SSLCEMITransactionInitializer(int emi_options) {
        this.emi_options = emi_options;
    }

    public SSLCEMITransactionInitializer addEmiMaxListOptions(int emi_max_list_options) {
        this.emi_max_list_options = emi_max_list_options;
        return this;
    }

    public SSLCEMITransactionInitializer addEmiSelectedInst(int emi_selected_inst) {
        this.emi_selected_inst = emi_selected_inst;
        return this;
    }

    public int getEmi_options() {
        return emi_options;
    }

    public void setEmi_options(int emi_options) {
        this.emi_options = emi_options;
    }

    public int getEmi_max_list_options() {
        return emi_max_list_options;
    }

    public void setEmi_max_list_options(int emi_max_list_options) {
        this.emi_max_list_options = emi_max_list_options;
    }

    public int getEmi_selected_inst() {
        return emi_selected_inst;
    }

    public void setEmi_selected_inst(int emi_selected_inst) {
        this.emi_selected_inst = emi_selected_inst;
    }
}
