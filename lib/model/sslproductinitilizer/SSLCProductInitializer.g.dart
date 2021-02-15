// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'SSLCProductInitializer.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

SSLCProductInitializer _$SSLCProductInitializerFromJson(
    Map<String, dynamic> json) {
  return SSLCProductInitializer(
    productName:
    json['productName'] as String,
    productCategory:
    json['productCategory'] as String,
    general:
    json['general'] == null
        ? null
        : General.fromJson(json['general'] as Map<String, dynamic>),
  )
    ..cart = json['cart'] as String
    ..productAmount = (json['productAmount'] as num)?.toDouble()
    ..vat = (json['vat'] as num)?.toDouble()
    ..discountAmount = (json['discountAmount'] as num)?.toDouble()
    ..convenienceFee = (json['convenienceFee'] as num)?.toDouble()
    ..airlinesTicket = json['airlinesTicket'] == null
        ? null
        : AirlinesTicket.fromJson(
            json['airlinesTicket'] as Map<String, dynamic>)
    ..physicalGoods = json['physicalGoods'] == null
        ? null
        : PhysicalGoods.fromJson(json['physicalGoods'] as Map<String, dynamic>)
    ..nonPhysicalGoods = json['nonPhysicalGoods'] == null
        ? null
        : NonPhysicalGoods.fromJson(
            json['nonPhysicalGoods'] as Map<String, dynamic>)
    ..telecomVertical = json['telecomVertical'] == null
        ? null
        : TelecomVertical.fromJson(
            json['telecomVertical'] as Map<String, dynamic>)
    ..travelVertical = json['travelVertical'] == null
        ? null
        : TravelVertical.fromJson(
            json['travelVertical'] as Map<String, dynamic>);
}

Map<String, dynamic> _$SSLCProductInitializerToJson(
        SSLCProductInitializer instance) =>
    <String, dynamic>{
      'productName': instance.productName,
      'productCategory': instance.productCategory,
      'cart': instance.cart,
      'productAmount': instance.productAmount,
      'vat': instance.vat,
      'discountAmount': instance.discountAmount,
      'convenienceFee': instance.convenienceFee,
      'airlinesTicket': instance.airlinesTicket,
      'general': instance.general,
      'physicalGoods': instance.physicalGoods,
      'nonPhysicalGoods': instance.nonPhysicalGoods,
      'telecomVertical': instance.telecomVertical,
      'travelVertical': instance.travelVertical,
    };
