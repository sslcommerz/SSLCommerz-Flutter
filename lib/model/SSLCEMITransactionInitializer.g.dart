// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'SSLCEMITransactionInitializer.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

SSLCEMITransactionInitializer _$SSLCEMITransactionInitializerFromJson(
    Map<String, dynamic> json) {
  return SSLCEMITransactionInitializer(
    emi_options: json['emi_options'] as int,
    emi_max_list_options: json['emi_max_list_options'] as int,
    emi_selected_inst: json['emi_selected_inst'] as int,
  );
}

Map<String, dynamic> _$SSLCEMITransactionInitializerToJson(
        SSLCEMITransactionInitializer instance) =>
    <String, dynamic>{
      'emi_options': instance.emi_options,
      'emi_max_list_options': instance.emi_max_list_options,
      'emi_selected_inst': instance.emi_selected_inst,
    };
