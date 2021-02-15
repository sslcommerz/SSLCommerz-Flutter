// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'TelecomVertical.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

TelecomVertical _$TelecomVerticalFromJson(Map<String, dynamic> json) {
  return TelecomVertical(
    productProfile: json['productProfile'] as String,
    productType: json['productType'] as String,
    topUpNumber: json['topUpNumber'] as String,
    countryTopUp: json['countryTopUp'] as String,
  );
}

Map<String, dynamic> _$TelecomVerticalToJson(TelecomVertical instance) =>
    <String, dynamic>{
      'productProfile': instance.productProfile,
      'productType': instance.productType,
      'topUpNumber': instance.topUpNumber,
      'countryTopUp': instance.countryTopUp,
    };
