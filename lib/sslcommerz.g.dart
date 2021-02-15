// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'sslcommerz.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

Sslcommerz _$SslcommerzFromJson(Map<String, dynamic> json) {
  return Sslcommerz()
    ..initializer = json['initializer'] == null
        ? null
        : SSLCommerzInitialization.fromJson(
            json['initializer'] as Map<String, dynamic>)
    ..customerInfoInitializer = json['customerInfoInitializer'] == null
        ? null
        : SSLCCustomerInfoInitializer.fromJson(
            json['customerInfoInitializer'] as Map<String, dynamic>)
    ..sslcemiTransactionInitializer =
        json['sslcemiTransactionInitializer'] == null
            ? null
            : SSLCEMITransactionInitializer.fromJson(
                json['sslcemiTransactionInitializer'] as Map<String, dynamic>)
    ..sslcShipmentInfoInitializer = json['sslcShipmentInfoInitializer'] == null
        ? null
        : SSLCShipmentInfoInitializer.fromJson(
            json['sslcShipmentInfoInitializer'] as Map<String, dynamic>)
    ..sslcAdditionalInitializer = json['sslcAdditionalInitializer'] == null
        ? null
        : SSLCAdditionalInitializer.fromJson(
            json['sslcAdditionalInitializer'] as Map<String, dynamic>)
    ..sslcProductInitializer = json['sslcProductInitializer'] == null
        ? null
        : SSLCProductInitializer.fromJson(
            json['sslcProductInitializer'] as Map<String, dynamic>);
}

Map<String, dynamic> _$SslcommerzToJson(Sslcommerz instance) =>
    <String, dynamic>{
      'initializer': instance.initializer,
      'customerInfoInitializer': instance.customerInfoInitializer,
      'sslcemiTransactionInitializer': instance.sslcemiTransactionInitializer,
      'sslcShipmentInfoInitializer': instance.sslcShipmentInfoInitializer,
      'sslcProductInitializer': instance.sslcProductInitializer,
      'sslcAdditionalInitializer': instance.sslcAdditionalInitializer,
    };
