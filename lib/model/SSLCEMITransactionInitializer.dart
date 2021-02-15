import 'package:flutter/material.dart';
import 'package:json_annotation/json_annotation.dart';

part 'SSLCEMITransactionInitializer.g.dart';
@JsonSerializable()
 class SSLCEMITransactionInitializer {
   int emi_options;
   int emi_max_list_options;
   int emi_selected_inst;

   SSLCEMITransactionInitializer({@required this.emi_options,this.emi_max_list_options, this.emi_selected_inst});


   Map<String, dynamic> toJson() => _$SSLCEMITransactionInitializerToJson(this);
   factory SSLCEMITransactionInitializer.fromJson(Map<String, dynamic> json) => _$SSLCEMITransactionInitializerFromJson(json);

   int getEmi_options() {
    return this.emi_options;
  }

   void setEmi_options(int emi_options) {
    this.emi_options = emi_options;
  }

   int getEmi_max_list_options() {
    return this.emi_max_list_options;
  }

   void setEmi_max_list_options(int emi_max_list_options) {
    this.emi_max_list_options = emi_max_list_options;
  }

   int getEmi_selected_inst() {
    return this.emi_selected_inst;
  }

   void setEmi_selected_inst(int emi_selected_inst) {
    this.emi_selected_inst = emi_selected_inst;
  }
}
