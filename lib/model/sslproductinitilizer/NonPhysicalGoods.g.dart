// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'NonPhysicalGoods.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

NonPhysicalGoods _$NonPhysicalGoodsFromJson(Map<String, dynamic> json) {
  return NonPhysicalGoods(
    productProfile:
    json['productProfile'] as String,
    nonPhysicalGoods:
    json['nonPhysicalGoods'] as String,
  );
}

Map<String, dynamic> _$NonPhysicalGoodsToJson(NonPhysicalGoods instance) =>
    <String, dynamic>{
      'nonPhysicalGoods': instance.nonPhysicalGoods,
      'productProfile': instance.productProfile,
    };
