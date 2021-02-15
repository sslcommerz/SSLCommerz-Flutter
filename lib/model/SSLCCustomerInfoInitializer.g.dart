// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'SSLCCustomerInfoInitializer.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

SSLCCustomerInfoInitializer _$SSLCCustomerInfoInitializerFromJson(
    Map<String, dynamic> json) {
  return SSLCCustomerInfoInitializer(
    customerName: json['customerName'] as String,
    customerEmail: json['customerEmail'] as String,
    customerAddress1: json['customerAddress1'] as String,
    customerCity: json['customerCity'] as String,
    customerPostCode: json['customerPostCode'] as String,
    customerCountry: json['customerCountry'] as String,
    customerPhone: json['customerPhone'] as String,
  )
    ..customerAddress2 = json['customerAddress2'] as String
    ..customerState = json['customerState'] as String
    ..customerFax = json['customerFax'] as String;
}

Map<String, dynamic> _$SSLCCustomerInfoInitializerToJson(
        SSLCCustomerInfoInitializer instance) =>
    <String, dynamic>{
      'customerName': instance.customerName,
      'customerEmail': instance.customerEmail,
      'customerAddress1': instance.customerAddress1,
      'customerAddress2': instance.customerAddress2,
      'customerCity': instance.customerCity,
      'customerState': instance.customerState,
      'customerPostCode': instance.customerPostCode,
      'customerCountry': instance.customerCountry,
      'customerPhone': instance.customerPhone,
      'customerFax': instance.customerFax,
    };
