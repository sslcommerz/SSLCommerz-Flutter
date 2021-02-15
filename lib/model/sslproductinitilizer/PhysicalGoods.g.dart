// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'PhysicalGoods.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

PhysicalGoods _$PhysicalGoodsFromJson(Map<String, dynamic> json) {
  return PhysicalGoods(
    productProfile: json['productProfile'] as String,
    physicalGoods: json['physicalGoods'] as String,
  );
}

Map<String, dynamic> _$PhysicalGoodsToJson(PhysicalGoods instance) =>
    <String, dynamic>{
      'physicalGoods': instance.physicalGoods,
      'productProfile': instance.productProfile,
    };
