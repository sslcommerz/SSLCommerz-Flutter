// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'SSLCShipmentInfoInitializer.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

SSLCShipmentInfoInitializer _$SSLCShipmentInfoInitializerFromJson(
    Map<String, dynamic> json) {
  return SSLCShipmentInfoInitializer(
    shipmentMethod: json['shipmentMethod'] as String,
    numOfItems: json['numOfItems'] as int,
    shipmentDetails: json['shipmentDetails'] == null
        ? null
        : ShipmentDetails.fromJson(
            json['shipmentDetails'] as Map<String, dynamic>),
  )
    ..shipAddress2 = json['shipAddress2'] as String
    ..shipState = json['shipState'] as String;
}

Map<String, dynamic> _$SSLCShipmentInfoInitializerToJson(
        SSLCShipmentInfoInitializer instance) =>
    <String, dynamic>{
      'shipmentMethod': instance.shipmentMethod,
      'shipAddress2': instance.shipAddress2,
      'shipState': instance.shipState,
      'numOfItems': instance.numOfItems,
      'shipmentDetails': instance.shipmentDetails,
    };

ShipmentDetails _$ShipmentDetailsFromJson(Map<String, dynamic> json) {
  return ShipmentDetails(
    shipName: json['shipName'] as String,
    shipAddress1: json['shipAddress1'] as String,
    shipCity: json['shipCity'] as String,
    shipPostCode: json['shipPostCode'] as String,
    shipCountry: json['shipCountry'] as String,
  );
}

Map<String, dynamic> _$ShipmentDetailsToJson(ShipmentDetails instance) =>
    <String, dynamic>{
      'shipName': instance.shipName,
      'shipAddress1': instance.shipAddress1,
      'shipCity': instance.shipCity,
      'shipPostCode': instance.shipPostCode,
      'shipCountry': instance.shipCountry,
    };
