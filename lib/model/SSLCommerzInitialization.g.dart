// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'SSLCommerzInitialization.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

SSLCommerzInitialization _$SSLCommerzInitializationFromJson(
    Map<String, dynamic> json) {
  return SSLCommerzInitialization(
    store_id: json['store_id'] as String,
    store_passwd: json['store_passwd'] as String,
    total_amount: (json['total_amount'] as num)?.toDouble(),
    currency: json['currency'] as String,
    tran_id: json['tran_id'] as String,
    product_category: json['product_category'] as String,
    sdkType: json['sdkType'] as String,
  )
    // ..success_url = json['success_url'] as String
    // ..fail_url = json['fail_url'] as String
    // ..cancel_url = json['cancel_url'] as String
    ..ipn_url = json['ipn_url'] as String
    ..multi_card_name = json['multi_card_name'] as String
    ..allowed_bin = json['allowed_bin'] as String;
}

Map<String, dynamic> _$SSLCommerzInitializationToJson(
        SSLCommerzInitialization instance) =>
    <String, dynamic>{
      'store_id': instance.store_id,
      'store_passwd': instance.store_passwd,
      'total_amount': instance.total_amount,
      'currency': instance.currency,
      'tran_id': instance.tran_id,
      'product_category': instance.product_category,
      // 'success_url': instance.success_url,
      // 'fail_url': instance.fail_url,
      // 'cancel_url': instance.cancel_url,
      'ipn_url': instance.ipn_url,
      'multi_card_name': instance.multi_card_name,
      'allowed_bin': instance.allowed_bin,
      'sdkType': instance.sdkType,
    };
